package com.example.Neo4jDemo.model.repository.fragments;

import org.springframework.data.neo4j.core.Neo4jClient;

import java.util.Collection;

public class PossibleInvitesFragmentImpl implements PossibleInvitesFragment {

    private final Neo4jClient neo4jClient;

    PossibleInvitesFragmentImpl(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Override
    public Collection<PossibleInvite> findPossibleInvites(String requesterName) {
        return neo4jClient.query("" +
                "match (host:User {name: $requesterName})-[:HOSTS]->(:Event)-[:INVITED_TO]->(firstFriend:User)<-[:INVITED_TO]-(missed:Event)<-[:HOSTS]-(secondFriend)\n" +
                "where not exists {match (host)<-[:INVITED_TO]-(missed)} and not exists {match (secondFriend)-[:HOSTS]->(:Event)-[:INVITED_TO]->(host)} and host <> secondFriend\n" +
                "return secondFriend.name as hostName, firstFriend.name as linkName, missed.tag as eventTag"
        )
                .bind(requesterName).to("requesterName")
                .fetchAs(PossibleInvite.class)
                .mappedBy((typeSystem, record) -> new PossibleInvite(record.get("hostName").asString(),
                        record.get("linkName").asString(), record.get("eventTag").asString()
                        ))
                .all();
    }
}
