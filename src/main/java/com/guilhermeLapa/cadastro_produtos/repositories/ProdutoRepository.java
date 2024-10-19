package com.guilhermeLapa.cadastro_produtos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.guilhermeLapa.cadastro_produtos.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	public List<Produto> findByNome(String nome);
}
