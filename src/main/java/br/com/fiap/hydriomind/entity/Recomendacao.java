package br.com.fiap.hydriomind.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "T_HM_RECOMENDACAO")
public class Recomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RECOMENDACAO")
    private Long id;

    @Column(name = "NM_RECOMENDACAO", nullable = false)
    private String nomeRecomendacao;

    @Column(name = "DS_DESCRICAO", nullable = true)
    private String descricaoRecomendacao;

    // Construtor padrão
    public Recomendacao() {
    }

    // Construtor completo
    public Recomendacao(Long id, String nomeRecomendacao, String descricaoRecomendacao) {
        this.id = id;
        this.nomeRecomendacao = nomeRecomendacao;
        this.descricaoRecomendacao = descricaoRecomendacao;
    }

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


