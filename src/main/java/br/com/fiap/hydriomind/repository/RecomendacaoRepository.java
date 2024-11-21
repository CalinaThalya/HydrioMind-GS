package br.com.fiap.hydriomind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.hydriomind.entity.Recomendacao;


@Repository
public interface RecomendacaoRepository extends JpaRepository<Recomendacao, Long> {
}
