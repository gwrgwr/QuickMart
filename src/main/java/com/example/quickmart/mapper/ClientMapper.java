package com.example.quickmart.mapper;

import com.example.quickmart.domain.client.Client;
import com.example.quickmart.domain.client.dto.request.ClientRequestDTO;
import com.example.quickmart.domain.client.dto.response.ClientResponseDTO;

public class ClientMapper {
    public static Client mapToClient(ClientRequestDTO clientRequestDTO) {
        return new Client(clientRequestDTO.name(), clientRequestDTO.username(), clientRequestDTO.email(), clientRequestDTO.password(), clientRequestDTO.nickname());
    }

    public static ClientResponseDTO mapToClientResponseDTO(Client client) {
        return new ClientResponseDTO(client.getId(), client.getFullName(), client.getUsername(), client.getNickname(), client.getEmail(), client.getRole().getRole());
    }
}
