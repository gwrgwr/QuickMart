package com.example.quickmart.controllers;

import com.example.quickmart.domain.client.dto.request.ClientRequestDTO;
import com.example.quickmart.domain.client.dto.response.ClientResponseDTO;
import com.example.quickmart.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@Tag(name = "Client", description = "Client API")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @PreAuthorize("permitAll()")
    @Operation(description = "Add a new client", summary = "Add a new client")
    public ClientResponseDTO saveClient(@RequestBody ClientRequestDTO client) {
        return this.clientService.saveClient(client);
    }

    @PutMapping("{clientId}")
    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @Operation(description = "Update a client", summary = "Update a client")
    public ClientResponseDTO updateClient(@PathVariable String clientId, @RequestBody ClientRequestDTO data) {
        return this.clientService.updateClient(clientId, data);
    }

    @DeleteMapping("{clientId}")
    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @Operation(description = "Delete a client", summary = "Delete a client")
    public void deleteClient(@PathVariable String clientId) {
        this.clientService.deleteClient(clientId);
    }
}
