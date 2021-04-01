package com.jonatas.apiconceitual.controllers;

import com.jonatas.apiconceitual.domain.model.Cliente;
import com.jonatas.apiconceitual.domain.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> obj = clienteService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Long id){
        Cliente obj = clienteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Cliente obj){
        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping("/{id}")
    @PutMapping
    public  ResponseEntity<Void> update(@RequestBody Cliente obj, @PathVariable Long id){
        obj.setId(id);
        obj = clienteService.update(obj);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
