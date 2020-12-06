package com.stevekatra.graphqlmeeting.resolvers;

import com.stevekatra.graphqlmeeting.domain.Meeting;
import com.stevekatra.graphqlmeeting.domain.Person;
import com.stevekatra.graphqlmeeting.repository.MeetingRepository;
import com.stevekatra.graphqlmeeting.repository.PersonRepository;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    public Meeting meeting(Long id) {
        Optional<Meeting> meeting = meetingRepository.findById(id);
        if(meeting.isPresent())
            return meeting.get();
        throw new GraphQLException("Meeting does not exist");
    }

    public List<Meeting> meetings() {
        return (List<Meeting>) meetingRepository.findAll();
    }

    public Person person(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent())
            return person.get();
        throw new GraphQLException("Person does not exist");
    }

    public List<Person> people() {
        return (List<Person>) personRepository.findAll();
    }
}
