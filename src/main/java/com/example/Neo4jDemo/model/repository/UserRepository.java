package com.example.Neo4jDemo.model.repository;

import com.example.Neo4jDemo.model.User;
import com.example.Neo4jDemo.model.repository.fragments.CountGuestsPerHostFragment;
import com.example.Neo4jDemo.model.repository.fragments.PossibleInvitesFragment;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends Neo4jRepository<User, Long>, CountGuestsPerHostFragment, PossibleInvitesFragment {

    Set<User> findAllByIdIn(Collection<Long> ids);

    Optional<User> findUserByName(String name);
}
