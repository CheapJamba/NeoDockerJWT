package com.example.Neo4jDemo.model.repository.fragments;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

public interface CountGuestsPerHostFragment {

    @Data
    @AllArgsConstructor
    class TotalGuestsForHost {
        String hostName;
        Long hostId;
        Integer guestCount;

    }

    Collection<TotalGuestsForHost> countGuestsPerHost();
}
