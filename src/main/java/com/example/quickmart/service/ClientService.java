package com.example.quickmart.service;

import com.example.quickmart.domain.client.Client;
import com.example.quickmart.domain.client.dto.request.ClientRequestDTO;
import com.example.quickmart.domain.client.dto.response.ClientResponseDTO;
import com.example.quickmart.exceptions.client.ClientNotFoundException;
import com.example.quickmart.mapper.ClientMapper;
import com.example.quickmart.repositories.ClientRepository;
import com.example.quickmart.shared.utils.ReflectionUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(String id) {
        return this.clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    public Client getClientByEmail(String email) {
        return this.clientRepository.findByEmail(email).orElse(null);
    }

    public ClientResponseDTO saveClient(ClientRequestDTO client) {
        return ClientMapper.mapToClientResponseDTO(this.clientRepository.save(ClientMapper.mapToClient(client)));
    }

    public ClientResponseDTO updateClient(String clientId, ClientRequestDTO data) {
        Client client = this.getClientById(clientId);
        ReflectionUtils.updateEntitiesFields(client, ClientMapper.mapToClient(data));
        return ClientMapper.mapToClientResponseDTO(this.clientRepository.save(client));
    }

    public void deleteClient(String clientId) {
        this.clientRepository.delete(this.getClientById(clientId));
    }
}
