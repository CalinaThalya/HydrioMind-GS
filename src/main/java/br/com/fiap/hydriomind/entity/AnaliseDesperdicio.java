package br.com.fiap.hydriomind.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "T_HM_ANALISE_DESPERDICIO")
public class AnaliseDesperdicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANALISE_DESPERDICIO")
    private Long id;

    @Column(name = "NM_PRODUTO", nullable = false)
    private String nomeProduto;

    @Column(name = "QT_DESPERDICIO", nullable = false)
    private Double quantidadeDesperdiciada;

    @Column(name = "UNIDADE_MEDIDA", nullable = false)
    private String unidadeMedida;

    @Column(name = "MOTIVO_DESPERDICIO", nullable = true)
    private String motivoDesperdicio;

    // Construtor padrão
    public AnaliseDesperdicio() {
    }

    // Construtor completo
    public AnaliseDesperdicio(Long id, String nomeProduto, Double quantidadeDesperdiciada, String unidadeMedida, String motivoDesperdicio) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.quantidadeDesperdiciada = quantidadeDesperdiciada;
        this.unidadeMedida = unidadeMedida;
        this.motivoDesperdicio = motivoDesperdicio;
    }

    // Getters e Setters
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
