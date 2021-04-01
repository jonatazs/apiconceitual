package com.jonatas.apiconceitual.controllers;


import com.jonatas.apiconceitual.domain.model.Estado;
import com.jonatas.apiconceitual.domain.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> findAll(){
        List<Estado> obj = estadoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Long id){
        Estado obj = estadoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Estado obj){
        obj = estadoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping("/{id}")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Estado obj, @PathVariable Long id){
        obj.setId(id);
        obj = estadoService.update(obj);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        estadoService.deleteById(id);
        return ResponseEntity.noContent().build();

    }




}
