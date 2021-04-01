package com.jonatas.apiconceitual.domain.services;


import com.jonatas.apiconceitual.domain.model.Estado;
import com.jonatas.apiconceitual.domain.model.Produto;
import com.jonatas.apiconceitual.domain.repositories.EstadoRepository;
import com.jonatas.apiconceitual.domain.services.exceptions.DataIntegrityException;
import com.jonatas.apiconceitual.domain.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }


    public Estado findById(Long id){
        Optional<Estado> obj = (Optional<Estado>) estadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Estado não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
    }

    public Estado insert(@RequestBody Estado obj){
        obj.setId(null);
        return estadoRepository.save(obj);
    }


    public Estado update(Estado obj) {
        findById(obj.getId());
        return estadoRepository.save(obj);
    }

    public void deleteById(Long id) {
        findById(id);
        try{
            estadoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir um Estado que possui cidades");
        }
    }
}
