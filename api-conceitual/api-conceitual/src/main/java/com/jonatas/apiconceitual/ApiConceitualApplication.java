package com.jonatas.apiconceitual;

import com.jonatas.apiconceitual.domain.model.Categoria;
import com.jonatas.apiconceitual.domain.model.Cidade;
import com.jonatas.apiconceitual.domain.model.Estado;
import com.jonatas.apiconceitual.domain.model.Produto;
import com.jonatas.apiconceitual.domain.repositories.CategoriaRepository;
import com.jonatas.apiconceitual.domain.repositories.CidadeRepository;
import com.jonatas.apiconceitual.domain.repositories.EstadoRepository;
import com.jonatas.apiconceitual.domain.repositories.ProdutoRepository;
import com.jonatas.apiconceitual.domain.services.CategoriaService;
import com.jonatas.apiconceitual.domain.services.CidadeService;
import com.jonatas.apiconceitual.domain.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class ApiConceitualApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;



	public static void main(String[] args) {
		SpringApplication.run(ApiConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));



	}
}
