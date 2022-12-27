package com.stevekatra.graphqlmeeting.resolvers;

import com.stevekatra.graphqlmeeting.domain.Meeting;
import com.stevekatra.graphqlmeeting.domain.MeetingInput;
import com.stevekatra.graphqlmeeting.domain.Person;
import com.stevekatra.graphqlmeeting.domain.PersonInput;
import com.stevekatra.graphqlmeeting.repository.MeetingRepository;
import com.stevekatra.graphqlmeeting.repository.PersonRepository;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {

  private final PersonRepository personRepository;
  private final MeetingRepository meetingRepository;

  public Meeting createMeeting(MeetingInput input) {
    return meetingRepository.save(getMeeting(input));
  }

  private Meeting getMeeting(MeetingInput meetingInput) {
    return Meeting.builder()
        .id(meetingInput.getId())
        .title(meetingInput.getTitle())
        .description(meetingInput.getDescription())
        .organizer(getPerson(meetingInput.getOrganizer()))
        .attendees(getPeople(meetingInput.getAttendees()))
        .build();
  }


  private List<Person> getPeople(@NotNull List<PersonInput> attendees) {
    List<Person> people = new ArrayList<>();
    for (PersonInput input : attendees) {
      people.add(getPerson(input));
    }
    return people;
  }

  public Meeting updateMeeting(MeetingInput meetingInput) {
    Meeting meeting = getMeeting(meetingInput);
    Optional<Meeting> targetMeeting = meetingRepository.findById(meeting.getId());
    if (targetMeeting.isPresent()) {
      return meetingRepository.save(meeting);
    }
    throw new GraphQLException("Meeting id " + meeting.getId() + " does not exist.");
  }

  public Person createPerson(PersonInput input) {
    return personRepository.save(getPerson(input));
  }

  private Person getPerson(PersonInput personInput) {
    return Person.builder()
        .firstName(personInput.getFirstName())
        .lastName(personInput.getLastName())
        .emailAddress(personInput.getEmailAddress())
        .mobilePhoneNumber(personInput.getMobilePhoneNumber())
        .officePhoneNumber(personInput.getOfficePhoneNumber())
        .build();
  }

  public Person updatePerson(PersonInput personInput) {
    Person person = getPerson(personInput);
    Optional<Person> targetPerson = personRepository.findById(person.getId());
    if (targetPerson.isPresent()) {
      return personRepository.save(person);
    }
    throw new GraphQLException("Person id " + person.getId() + " does not exist.");
  }
}
