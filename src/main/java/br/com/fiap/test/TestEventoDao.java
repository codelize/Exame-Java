package br.com.fiap.test;

import br.com.fiap.dao.EventoDao;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Evento;

import java.sql.Connection;
import java.time.LocalDate;

public class TestEventoDao {

    public static void main(String[] args) {
        try (Connection conexao = ConnectionFactory.getConnection()) {
            EventoDao dao = new EventoDao(conexao);

            // Testando cadastro de evento
            Evento evento = new Evento(0, "Evento Atualizado", "Descrição Atualizada", LocalDate.now().plusDays(5));
            dao.cadastrar(evento);
            System.out.println("Evento cadastrado com sucesso!");

            // Testando listagem
            dao.listar().forEach(e -> System.out.println("Evento: " + e.getTitulo()));

            // Testando busca por ID
            Evento eventoEncontrado = dao.pesquisarPorId(1);
            System.out.println("Evento encontrado: " + eventoEncontrado.getTitulo());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
