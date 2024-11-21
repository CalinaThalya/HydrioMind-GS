package br.com.fiap.hydriomind.service;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fiap.hydriomind.dto.AnaliseDesperdicioDTO;
import br.com.fiap.hydriomind.entity.AnaliseDesperdicio;
import br.com.fiap.hydriomind.repository.AnaliseDesperdicioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnaliseDesperdicioService {

    @Autowired
    private AnaliseDesperdicioRepository analiseDesperdicioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        // Configurações do ModelMapper
        modelMapper.typeMap(AnaliseDesperdicioDTO.class, AnaliseDesperdicio.class).addMappings(mapper -> {
            mapper.map(AnaliseDesperdicioDTO::getNomeProduto, AnaliseDesperdicio::setNomeProduto);
            mapper.map(AnaliseDesperdicioDTO::getQuantidadeDesperdiciada, AnaliseDesperdicio::setQuantidadeDesperdiciada);
            mapper.map(AnaliseDesperdicioDTO::getUnidadeMedida, AnaliseDesperdicio::setUnidadeMedida);
            mapper.map(AnaliseDesperdicioDTO::getMotivoDesperdicio, AnaliseDesperdicio::setMotivoDesperdicio);
        });
        modelMapper.typeMap(AnaliseDesperdicio.class, AnaliseDesperdicioDTO.class).addMappings(mapper -> {
            mapper.map(AnaliseDesperdicio::getNomeProduto, AnaliseDesperdicioDTO::setNomeProduto);
            mapper.map(AnaliseDesperdicio::getQuantidadeDesperdiciada, AnaliseDesperdicioDTO::setQuantidadeDesperdiciada);
            mapper.map(AnaliseDesperdicio::getUnidadeMedida, AnaliseDesperdicioDTO::setUnidadeMedida);
            mapper.map(AnaliseDesperdicio::getMotivoDesperdicio, AnaliseDesperdicioDTO::setMotivoDesperdicio);
        });
    }

    // Método para listar todas as análises de desperdício
    public List<AnaliseDesperdicioDTO> listarTodasAnalisesDesperdicio() {
        return analiseDesperdicioRepository.findAll().stream()
                .map(analiseDesperdicio -> modelMapper.map(analiseDesperdicio, AnaliseDesperdicioDTO.class))
                .collect(Collectors.toList());
    }

    // Método para encontrar uma análise de desperdício por ID
    public Optional<AnaliseDesperdicioDTO> encontrarAnaliseDesperdicioPorId(Long id) {
        return analiseDesperdicioRepository.findById(id)
                .map(analiseDesperdicio -> modelMapper.map(analiseDesperdicio, AnaliseDesperdicioDTO.class));
    }

    // Método para salvar ou atualizar uma análise de desperdício
    public AnaliseDesperdicioDTO salvarAnaliseDesperdicio(AnaliseDesperdicioDTO analiseDesperdicioDTO) {
        // Mapeia o DTO para a entidade
        AnaliseDesperdicio analiseDesperdicio = modelMapper.map(analiseDesperdicioDTO, AnaliseDesperdicio.class);
        // Salva a análise no repositório e mapeia de volta para DTO
        return modelMapper.map(analiseDesperdicioRepository.save(analiseDesperdicio), AnaliseDesperdicioDTO.class);
    }

    // Método para atualizar uma análise de desperdício
    public AnaliseDesperdicioDTO atualizarAnaliseDesperdicio(Long id, AnaliseDesperdicioDTO analiseDesperdicioDTO) {
        // Verifica se a análise já existe no banco
        if (analiseDesperdicioRepository.existsById(id)) {
            analiseDesperdicioDTO.setId(id);  // Garante que o ID não seja perdido
            return salvarAnaliseDesperdicio(analiseDesperdicioDTO);  // Chama o método para salvar (que também serve para atualizar)
        } else {
            return null; // Retorna null se a análise não existir
        }
    }

    // Método para deletar uma análise de desperdício
    public void deletarAnaliseDesperdicio(Long id) {
        analiseDesperdicioRepository.deleteById(id);
    }
}
