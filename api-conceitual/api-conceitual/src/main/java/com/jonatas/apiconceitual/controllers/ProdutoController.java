package com.jonatas.apiconceitual.controllers;


import com.jonatas.apiconceitual.domain.model.Produto;
import com.jonatas.apiconceitual.domain.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        List<Produto> obj = produtoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> find(@PathVariable Long id){
        Produto obj = produtoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody Produto obj){
        obj = produtoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @RequestMapping("/{id}")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Produto obj, @PathVariable Long id){
        obj.setId(id);
        obj = produtoService.update(obj);
        return ResponseEntity.noContent().build();
    }


}
