package com.example.Neo4jDemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Node("Event")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    private String tag;

    @Relationship(type = "HOSTS", direction = Relationship.Direction.INCOMING)
    @JsonBackReference
    private User host;

    @Relationship(type = "INVITED_TO", direction = Relationship.Direction.OUTGOING)
    private List<User> participants;

    private Date openDate;

    private Date closeDate;

    public Event(User host, String tag, Date openDate, Date closeDate) {
        this.id = null;
        this.host = host;
        this.tag = tag;
        this.participants = new ArrayList<>();
        this.openDate = openDate;
        this.closeDate = closeDate;
    }
}
