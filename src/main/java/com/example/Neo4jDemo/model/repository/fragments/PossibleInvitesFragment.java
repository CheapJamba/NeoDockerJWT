package com.example.Neo4jDemo.model.repository.fragments;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

public interface PossibleInvitesFragment {

    @Data
    @AllArgsConstructor
    class PossibleInvite {
        String hostName;
        String linkName;
        String eventTag;
    }

    /**
     * Finds events hosted by users that have never invited requester, to which one of requester's friends has been
     * invited. In this context users invited to events hosted by requester are considered requester's friends.
     * @param requesterName Requesting user's name
     * @return Collection of objects, containing event's host as hostName, name of a friend that has been invited to
     * the event as linkName, and event's tag as eventTag.
     */
    Collection<PossibleInvite> findPossibleInvites(String requesterName);


}
