package br.com.fiap.hydriomind.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.hydriomind.dto.RecomendacaoDTO;
import br.com.fiap.hydriomind.entity.Recomendacao;
import br.com.fiap.hydriomind.repository.RecomendacaoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecomendacaoService {

    @Autowired
    private RecomendacaoRepository recomendacaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<RecomendacaoDTO> listarTodasRecomendacoes() {
        return recomendacaoRepository.findAll().stream()
                .map(recomendacao -> modelMapper.map(recomendacao, RecomendacaoDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<RecomendacaoDTO> encontrarRecomendacaoPorId(Long id) {
        return recomendacaoRepository.findById(id).map(recomendacao -> modelMapper.map(recomendacao, RecomendacaoDTO.class));
    }

    public RecomendacaoDTO salvarRecomendacao(RecomendacaoDTO recomendacaoDTO) {
        // Mapeia o DTO para a entidade
        Recomendacao recomendacao = modelMapper.map(recomendacaoDTO, Recomendacao.class);
    
        // Salva a entidade no banco de dados
        Recomendacao recomendacaoSalva = recomendacaoRepository.save(recomendacao);
    
        // Retorna o DTO da recomendação salva
        return modelMapper.map(recomendacaoSalva, RecomendacaoDTO.class);
    }

    
    

    public void deletarRecomendacao(Long id) {
        recomendacaoRepository.deleteById(id);
    }
}
