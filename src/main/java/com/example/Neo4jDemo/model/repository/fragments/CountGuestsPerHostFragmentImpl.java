package com.example.Neo4jDemo.model.repository.fragments;

import org.springframework.data.neo4j.core.Neo4jClient;

import java.util.Collection;
import java.util.List;

public class CountGuestsPerHostFragmentImpl implements CountGuestsPerHostFragment{

    private final Neo4jClient neo4jClient;

    CountGuestsPerHostFragmentImpl(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Override
    public Collection<TotalGuestsForHost> countGuestsPerHost() {
        return neo4jClient.query("" +
                "MATCH (host:User)-[:HOSTS]->(e:Event)-[:INVITED_TO]->(guest:User)\n" +
                "RETURN host.name AS name, ID(host) AS hostId, count(guest) AS guestCount"
        )
                .fetchAs(TotalGuestsForHost.class)
                .mappedBy((typeSystem, record) -> new TotalGuestsForHost(record.get("name").asString(),
                        record.get("hostId").asLong(),
                        record.get("guestCount").asInt()
                ))
                .all();
    }
}
