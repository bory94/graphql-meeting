package com.stevekatra.graphqlmeeting.repository;

import com.stevekatra.graphqlmeeting.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
