package br.com.fiap.dto;

import java.time.LocalDate;

public class EventoResponseDto {

    private int id;
    private String titulo;
    private String descricao;
    private LocalDate dataEvento;

    // Construtores
    public EventoResponseDto() {}

    public EventoResponseDto(int id, String titulo, String descricao, LocalDate dataEvento) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEvento = dataEvento;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }
}
