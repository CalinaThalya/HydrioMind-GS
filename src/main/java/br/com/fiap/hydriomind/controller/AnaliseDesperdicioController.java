package br.com.fiap.hydriomind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.hydriomind.dto.AnaliseDesperdicioDTO;
import br.com.fiap.hydriomind.service.AnaliseDesperdicioService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/analisesDesperdicio")
public class AnaliseDesperdicioController {

    @Autowired
    private AnaliseDesperdicioService analiseDesperdicioService;

    @GetMapping("/listar")
    public String listarAnalises(Model model) {
        List<AnaliseDesperdicioDTO> analises = analiseDesperdicioService.listarTodasAnalisesDesperdicio();
        model.addAttribute("analises", analises);
        return "analise/listar"; // Retorna para o template analise/listar.html
    }

    @GetMapping("/{id}")
    public String obterAnaliseDesperdicioPorId(@PathVariable Long id, Model model) {
        return analiseDesperdicioService.encontrarAnaliseDesperdicioPorId(id)
                .map(analise -> {
                    model.addAttribute("analise", analise);
                    return "analise/detalhes";
                })
                .orElse("error/404");
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("analiseDesperdicioDTO", new AnaliseDesperdicioDTO());
        return "analise/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarAnalise(@Valid @ModelAttribute AnaliseDesperdicioDTO analiseDesperdicioDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("analiseDesperdicioDTO", analiseDesperdicioDTO);
            return "analise/cadastrar";
        }
        try {
            analiseDesperdicioService.salvarAnaliseDesperdicio(analiseDesperdicioDTO);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao salvar análise de desperdício: " + e.getMessage());
            return "analise/cadastrar";
        }
        return "redirect:/analisesDesperdicio/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarAnalise(@PathVariable Long id, Model model) {
        Optional<AnaliseDesperdicioDTO> analise = analiseDesperdicioService.encontrarAnaliseDesperdicioPorId(id);
        if (analise.isPresent()) {
            model.addAttribute("analiseDesperdicioDTO", analise.get()); // Use o mesmo nome do modelo
            model.addAttribute("titulo", "Editar Análise");
            return "analise/editar";
        } else {
            return "error/404";
        }
    }

    @PostMapping("/editar/{id}")
    public String atualizarAnalise(@PathVariable Long id, @Valid @ModelAttribute AnaliseDesperdicioDTO analiseDesperdicioDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            analiseDesperdicioDTO.setId(id);  // Garante que o ID não seja perdido
            return "analise/editar";  // Retorna para a página de edição se houver erros
        }
        try {
            analiseDesperdicioDTO.setId(id);  // Certifica-se de que o ID do DTO é o correto
            analiseDesperdicioService.salvarAnaliseDesperdicio(analiseDesperdicioDTO);  // Atualiza a análise
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao atualizar análise de desperdício: " + e.getMessage());
            return "analise/editar"; // Caso ocorra erro na atualização
        }
        return "redirect:/analisesDesperdicio/listar";  // Redireciona para a lista de análises após sucesso
    }

    @PostMapping("/deletar/{id}")
    public String deletarAnalise(@PathVariable Long id, Model model) {
        try {
            analiseDesperdicioService.deletarAnaliseDesperdicio(id);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao deletar análise: " + e.getMessage());
            return "analise/deletar";
        }
        return "redirect:/analisesDesperdicio/listar";
    }
    
}    