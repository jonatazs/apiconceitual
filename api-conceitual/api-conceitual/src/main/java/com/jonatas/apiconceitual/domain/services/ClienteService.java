package com.jonatas.apiconceitual.domain.services;


import com.jonatas.apiconceitual.domain.model.Cliente;
import com.jonatas.apiconceitual.domain.repositories.ClienteRepository;
import com.jonatas.apiconceitual.domain.services.exceptions.DataIntegrityException;
import com.jonatas.apiconceitual.domain.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){
        Optional<Cliente> obj = (Optional<Cliente>) clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado" + id + "Tipo, " + Cliente.class.getName()));
    }

    public Cliente insert(Cliente obj){
        obj.setId(null);
        return clienteRepository.save(obj);
    }

    public Cliente update(Cliente obj) {
        findById(obj.getId());
        return clienteRepository.save(obj);
    }

    public void deleteById(Long id) {
        findById(id);
        try{
            clienteRepository.deleteById(id);
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
        }
    }
}
