package br.com.fiap.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class EventoCadastroDto {

    @NotNull(message = "O título é obrigatório")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String titulo;

    @NotNull(message = "A descrição é obrigatória")
    @Size(max = 500, message = "A descrição não pode exceder 500 caracteres")
    private String descricao;

    @NotNull(message = "A data do evento é obrigatória")
    @FutureOrPresent(message = "A data do evento deve ser no futuro ou hoje")
    private LocalDate dataEvento;

    // Getters e Setters
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
