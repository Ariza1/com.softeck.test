package com.softeck.test.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softeck.test.model.Client;
import com.softeck.test.model.dtos.ClientDto;
import com.softeck.test.respository.IClientRepository;


@Service
public class ClientService implements IClientService {

    @Autowired
    private IClientRepository clientRepository;


    @Override
    public List<ClientDto> findAll() {
        List<ClientDto> clients = new ArrayList<ClientDto>();

        try {
            clientRepository.findAll().forEach((c) -> {
                //convert  this to auto mapped
                ClientDto cl = new ClientDto(c.getId(), c.getFirstName(), c.getLastName(), c.getSurname(), c.getPhone());
                clients.add(cl);
            });

        } catch (Exception ex) {
            throw ex;
        }

        return clients;
    }


    @Override
    public Client save(ClientDto clientdto) {
        Client saved = null;
        try {

            Client client = new Client();

            //convert  this to auto mapped

            client.setFirstName(clientdto.getFirstName());
            client.setLastName(clientdto.getLastName());
            client.setSurname(clientdto.getSurname());
            client.setPhone(clientdto.getPhone());

            saved = clientRepository.save(client);

        } catch (Exception ex) {
            throw ex;
        }

        return saved;
    }

    @Override
    public int delete(Long id) {
        Client client = clientRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Client not found with id " + id));
        if (client.getId() != null) {
            clientRepository.delete(client);
        }
        return 1;
    }

}
