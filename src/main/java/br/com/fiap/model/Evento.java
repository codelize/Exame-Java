package br.com.fiap.model;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Evento {

    private int id; // Identificador único do evento
    private String titulo; // Título do evento
    private String descricao; // Descrição do evento
    private LocalDate dataEvento; // Data em que o evento ocorrerá

}
