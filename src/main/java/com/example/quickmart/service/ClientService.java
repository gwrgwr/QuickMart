package com.example.quickmart.service;

import com.example.quickmart.domain.client.Client;
import com.example.quickmart.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(String id) {
        return this.clientRepository.findById(id).orElseThrow();
    }

    public Client getClientByEmail(String email) {
        return this.clientRepository.findByEmail(email).orElseThrow();
    }


}
