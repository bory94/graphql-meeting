package com.stevekatra.graphqlmeeting.resolvers;

import com.stevekatra.graphqlmeeting.domain.Meeting;
import com.stevekatra.graphqlmeeting.domain.Person;
import com.stevekatra.graphqlmeeting.repository.MeetingRepository;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingResolver implements GraphQLResolver<Meeting> {

  private MeetingRepository meetingRepository;

  public List<Person> attendees(Meeting meeting) {
    Optional<Meeting> fetchMeeting = meetingRepository.findById(meeting.getId());
    if (fetchMeeting.isPresent()) {
      return fetchMeeting.get().getAttendees();
    }
    throw new GraphQLException("Meeting did not have attendees");
  }
}
