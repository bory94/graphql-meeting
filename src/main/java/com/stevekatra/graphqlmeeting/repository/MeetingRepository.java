package com.stevekatra.graphqlmeeting.repository;

import com.stevekatra.graphqlmeeting.domain.Meeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting,Long> {
}
