package br.com.fiap.hydriomind.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AnaliseDesperdicioDTO {

    private Long id;

    @NotBlank
    private String nomeProduto;

    @NotNull
    @Positive
    private Double quantidadeDesperdiciada;

    @NotBlank
    private String unidadeMedida;

    @NotBlank
    private String motivoDesperdicio;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getQuantidadeDesperdiciada() {
        return quantidadeDesperdiciada;
    }

    public void setQuantidadeDesperdiciada(Double quantidadeDesperdiciada) {
        this.quantidadeDesperdiciada = quantidadeDesperdiciada;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getMotivoDesperdicio() {
        return motivoDesperdicio;
    }

    public void setMotivoDesperdicio(String motivoDesperdicio) {
        this.motivoDesperdicio = motivoDesperdicio;
    }
}
