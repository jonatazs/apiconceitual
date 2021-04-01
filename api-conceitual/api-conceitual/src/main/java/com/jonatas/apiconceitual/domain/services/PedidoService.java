package com.jonatas.apiconceitual.domain.services;

import com.jonatas.apiconceitual.domain.model.Categoria;
import com.jonatas.apiconceitual.domain.model.Pedido;
import com.jonatas.apiconceitual.domain.repositories.CategoriaRepository;
import com.jonatas.apiconceitual.domain.repositories.PedidoRepository;
import com.jonatas.apiconceitual.domain.services.exceptions.DataIntegrityException;
import com.jonatas.apiconceitual.domain.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;



    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id){
        Optional<Pedido> obj = (Optional<Pedido>) pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido obj){
        obj.setId(null);
        return pedidoRepository.save(obj);
    }


    public Pedido update(Pedido obj) {
        findById(obj.getId());
        return pedidoRepository.save(obj);
    }

    public void deleteById(Long id) {
        findById(id);
        try{
            pedidoRepository.deleteById(id);
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Não é possível excluir um pedido que tem estado de pagamentos já efetuados");
        }
    }
}
