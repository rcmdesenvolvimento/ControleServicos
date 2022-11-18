package com.rcm.sistemas.clientes.api.controller;

import com.rcm.sistemas.clientes.domain.model.entity.Cliente;
import com.rcm.sistemas.clientes.domain.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping()
    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente buscaClientePorId(@PathVariable("id") Integer id) {
        return clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@Valid @PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
        clienteRepository
                .findById(id)
                .map(cliente -> {
                   clienteAtualizado.setId(cliente.getId());
                   return clienteRepository.save(clienteAtualizado);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable("id") Integer id) {
        clienteRepository
                .findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
