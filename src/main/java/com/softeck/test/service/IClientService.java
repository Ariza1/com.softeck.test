package com.softeck.test.service;

import java.util.List;

import com.softeck.test.model.Client;
import com.softeck.test.model.dtos.ClientDto;

public interface IClientService {
    public List<ClientDto> findAll();

    public Client save(ClientDto client);

    public int delete(Long id);
}
