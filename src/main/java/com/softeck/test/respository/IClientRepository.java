package com.softeck.test.respository;

import java.util.List;
import java.util.Optional;

import com.softeck.test.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
}
