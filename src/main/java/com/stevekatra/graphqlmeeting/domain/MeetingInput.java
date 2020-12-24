package com.stevekatra.graphqlmeeting.domain;

import lombok.Data;

import java.util.List;

@Data
public class MeetingInput {
    private Long id;
    private String title;
    private String description;
    private PersonInput organizer;
    private List<PersonInput> attendees;
}
