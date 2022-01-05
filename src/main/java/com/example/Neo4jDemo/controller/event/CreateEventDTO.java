package com.example.Neo4jDemo.controller.event;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDTO {

    public Long userId;
    public String tag;
    public Date openDate;
    public Date closeDate;
}
