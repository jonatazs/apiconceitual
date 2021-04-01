package com.jonatas.apiconceitual.domain.services;


import com.jonatas.apiconceitual.domain.model.Categoria;
import com.jonatas.apiconceitual.domain.repositories.CategoriaRepository;
import com.jonatas.apiconceitual.domain.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;



    public List<Categoria> buscarTodos(){
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id){
            Optional<Categoria> obj = (Optional<Categoria>) categoriaRepository.findById(id);
            return obj.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

}
