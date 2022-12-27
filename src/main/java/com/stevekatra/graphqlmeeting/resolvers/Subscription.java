package com.stevekatra.graphqlmeeting.resolvers;

import com.stevekatra.graphqlmeeting.domain.Person;
import com.stevekatra.graphqlmeeting.repository.PersonRepository;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Subscription implements GraphQLSubscriptionResolver {

  private PersonRepository personRepository;

  public Publisher<List<Person>> people() {
    return subscriber -> Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
      List<Person> people = (List<Person>) personRepository.findAll();
      subscriber.onNext(people);
    }, 0, 1, TimeUnit.SECONDS);
  }
}
