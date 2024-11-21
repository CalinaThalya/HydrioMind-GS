package br.com.fiap.hydriomind.repository;

import br.com.fiap.hydriomind.entity.AnaliseDesperdicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseDesperdicioRepository extends JpaRepository<AnaliseDesperdicio, Long> {
}
