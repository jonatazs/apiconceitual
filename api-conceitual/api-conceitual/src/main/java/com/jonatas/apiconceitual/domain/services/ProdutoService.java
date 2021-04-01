package com.jonatas.apiconceitual.domain.services;

import com.jonatas.apiconceitual.domain.model.Categoria;
import com.jonatas.apiconceitual.domain.model.Produto;
import com.jonatas.apiconceitual.domain.repositories.ProdutoRepository;
import com.jonatas.apiconceitual.domain.services.exceptions.DataIntegrityException;
import com.jonatas.apiconceitual.domain.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(Long id){
        Optional<Produto> obj = (Optional<Produto>) produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }


    public Produto insert(@RequestBody Produto obj){
        obj.setId(null);
        return produtoRepository.save(obj);
    }


    public Produto update(Produto obj) {
        findById(obj.getId());
        return produtoRepository.save(obj);
    }

    public void deleteById(Long id) {
        findById(id);
        try {
            produtoRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma produto que possui itens de pedido");
        }
    }
}
