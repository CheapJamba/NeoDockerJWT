package com.example.Neo4jDemo.controller.event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventDTO {

    public Long eventId;
    public Set<Long> participantsToRemove;
    public Set<Long> participantsToAdd;
    public String tag;
    public Date openDate;
    public Date closeDate;
}
