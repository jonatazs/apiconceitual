package com.jonatas.apiconceitual.domain.services;

import com.jonatas.apiconceitual.domain.model.Cidade;
import com.jonatas.apiconceitual.domain.model.Produto;
import com.jonatas.apiconceitual.domain.repositories.CidadeRepository;
import com.jonatas.apiconceitual.domain.services.exceptions.DataIntegrityException;
import com.jonatas.apiconceitual.domain.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findAll(){
        return cidadeRepository.findAll();
    }

    public Cidade findById(Long id){
        Optional<Cidade> obj = (Optional<Cidade>) cidadeRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada! Id: " + id + ", Tipo: " + Cidade.class.getName()));
    }

    public Cidade insert(@RequestBody Cidade obj){
        obj.setId(null);
        return cidadeRepository.save(obj);
    }

    public Cidade update(Cidade obj) {
        findById(obj.getId());
        return cidadeRepository.save(obj);
    }

    public void deleteById(Long id) {
        findById(id);
        try{
            cidadeRepository.deleteById(id);
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Não é possível excluir uma cidade que possui varios endereços");

        }
    }
}
