package com.example.Neo4jDemo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Node("User")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"hostList", "invites", "id"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String name;

    @Relationship(type = "HOSTS", direction = Relationship.Direction.OUTGOING)
    @JsonManagedReference
    private Set<Event> hostList;

    @Relationship(type = "INVITED_TO", direction = Relationship.Direction.INCOMING)
    private Set<Event> invites;

    public User(String name, String email) {
        this.id = null;
        this.email = email;
        this.name = name;
        this.hostList = new HashSet<>();
        this.invites = new HashSet<>();
    }

    public User(String name) {
        this(name, name.toLowerCase() + "@domain.com");
    }
}
