package com.guilhermeLapa.cadastro_produtos.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.guilhermeLapa.cadastro_produtos.entities.Produto;
import com.guilhermeLapa.cadastro_produtos.repositories.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	private static final int tamanhoPagina= 30;
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Operation(summary = "Consultar Produtos", description = "Consulta uma lista de Produto(s) existente(s).")
	@Tag(name = "get", description = "Métodos GET para Produto.")
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos(@RequestParam(defaultValue = "") String order, @RequestParam(defaultValue = "") String nome) {
		List<Produto> resultado= null;
		Pageable pageable= null;
		
		//Filtro por nome usando parâmetro do request
		if(!nome.equals("")) {
			return this.filtrarProdutoPorNome(nome);
		}
		else {
				//Listar padrão retornando toda a lista conforme ordenação
				pageable= this.configurarOrdenacao(order);
				resultado= produtoRepo.findAll(pageable).toList();
			}
			
			return new ResponseEntity<List<Produto>>(resultado, HttpStatus.OK);
	}
	
	private ResponseEntity<List<Produto>> filtrarProdutoPorNome(String nome) {
		List<Produto> resultado= null;
		
		if(!(nome instanceof String)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
				//TO DO tratar injection
				resultado= produtoRepo.findByNome(nome);
				if(resultado.isEmpty()) {
					return new ResponseEntity<List<Produto>>(HttpStatus.NOT_FOUND);
				}
				else {
					return new ResponseEntity<List<Produto>>(resultado, HttpStatus.OK);
				}
			}
	}
	
	private Pageable configurarOrdenacao(String order) {
		Pageable pageable= null;
		Sort sort= null;
		
		switch (order.toLowerCase()) {
			case "asc": {
				sort= Sort.by("preco").ascending();
				pageable= PageRequest.of(0, tamanhoPagina, sort);
				break;
			}
			case "desc": {
				sort= Sort.by("preco").descending();
				pageable= PageRequest.of(0, tamanhoPagina, sort);
				break;
			}
			default: {
				pageable= PageRequest.of(0, tamanhoPagina);
			}
		}
		
		return pageable;
	}
	
	@Operation(summary = "Consultar Produto", description = "Consulta um Produto existente a partir de seu ID.")
	@Tag(name = "GET", description = "Métodos GET para Produto.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> consultarProduto(@PathVariable Long id) {
		Optional<Produto> produto= null;
		
		if(!(id instanceof Long)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
				produto= produtoRepo.findById(id);
				
				if(!produto.isPresent()) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				else {
					return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
				}
			}
	}
	
	@Operation(summary = "Cadastrar Produto", description = "Cria um novo Produto.")
	@Tag(name = "POST", description = "Métodos POST para Produto.")
	@PostMapping
	public Produto cadastrarProduto(@RequestBody Produto produto) {
		Produto resultado= produtoRepo.save(produto);
		return resultado;
	}
	
	@Operation(summary = "Deletar Produto", description = "Exclui um Produto existente a partir de seu ID.")
	@Tag(name = "DELETE", description = "Métodos DELETE para Produto.")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Produto> deletarProduto(@PathVariable Long id) {
		Optional<Produto> produtoExistente= produtoRepo.findById(id);
		
		if(!(id instanceof Long)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else if(!produtoExistente.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else {
				produtoRepo.deleteById(id);
				return new ResponseEntity<Produto>(HttpStatus.OK);
			}
	}
	
	@Operation(summary = "Atualizar Produto", description = "Atualiza um Produto existente a partir de seu ID.")
	@Tag(name = "PUT", description = "Métodos PUT para Produto.")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto novoProduto) {
		Optional<Produto> produtoExistente= produtoRepo.findById(id);
		Produto produto;
		
		if(!(id instanceof Long)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else if(!produtoExistente.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else {
				produto= produtoExistente.get();
				produto.setNome(novoProduto.getNome());
				produto.setDescricao(novoProduto.getDescricao());
				produto.setPreco(novoProduto.getPreco());
				produto.setQuantidadeEstoque(novoProduto.getQuantidadeEstoque());
				produto.setDataCriacao(novoProduto.getDataCriacao());
				
				produtoRepo.save(produto);
				return new ResponseEntity<Produto>(produto, HttpStatus.OK);
			}
	}
}
