package com.jonatas.apiconceitual.controllers;

import com.jonatas.apiconceitual.domain.model.Cidade;
import com.jonatas.apiconceitual.domain.model.Estado;
import com.jonatas.apiconceitual.domain.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll(){
        List<Cidade> obj = cidadeService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id){
        Cidade obj = cidadeService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }


    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Cidade obj){
        obj = cidadeService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }




}
