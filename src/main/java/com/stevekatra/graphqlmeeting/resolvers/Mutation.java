package com.stevekatra.graphqlmeeting.resolvers;

import com.stevekatra.graphqlmeeting.domain.Meeting;
import com.stevekatra.graphqlmeeting.domain.MeetingInput;
import com.stevekatra.graphqlmeeting.repository.MeetingRepository;
import com.stevekatra.graphqlmeeting.repository.PersonRepository;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private MeetingRepository meetingRepository;

    public Meeting createMeeting(MeetingInput meetingInput) {
        Meeting meeting = getMeeting(meetingInput);
        return meetingRepository.save(meeting);
    }

    private Meeting getMeeting(MeetingInput meetingInput) {
        Meeting meeting = Meeting.builder()
                .id(meetingInput.getId())
                .title(meetingInput.getTitle())
                .description(meetingInput.getDescription())
                .organizer(meetingInput.getOrganizer())
                .attendees(meetingInput.getAttendees())
                .build();
        return meeting;
    }

    public Meeting updateMeeting(MeetingInput meetingInput) {
        Meeting meeting = getMeeting(meetingInput);
        Optional<Meeting> targetMeeting = meetingRepository.findById(meeting.getId());
        if(targetMeeting.isPresent()) {
            return meetingRepository.save(meeting);
        }
        throw new GraphQLException("Meeeting id " + meeting.getId() + " does not exist.");
    }
}
