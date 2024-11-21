package br.com.fiap.hydriomind.dto;

import jakarta.validation.constraints.NotBlank;

public class RecomendacaoDTO {

    private Long id;

    @NotBlank(message = "O nome da recomendação é obrigatório")
    private String nomeRecomendacao;

    private String descricaoRecomendacao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRecomendacao() {
        return nomeRecomendacao;
    }

    public void setNomeRecomendacao(String nomeRecomendacao) {
        this.nomeRecomendacao = nomeRecomendacao;
    }

    public String getDescricaoRecomendacao() {
        return descricaoRecomendacao;
    }

    public void setDescricaoRecomendacao(String descricaoRecomendacao) {
        this.descricaoRecomendacao = descricaoRecomendacao;
    }
}
