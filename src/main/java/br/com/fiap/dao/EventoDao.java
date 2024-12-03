package br.com.fiap.dao;

import br.com.fiap.exception.IdNaoEncontradoException;
import br.com.fiap.model.Evento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDao {

    // SQL Statements ajustados
    private static final String INSERT_SQL =
            "INSERT INTO TB_EVENTO (ID, DS_TITULO, DS_EVENTO, DT_EVENTO) VALUES (SEQ_TB_EVENTO.NEXTVAL, ?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM TB_EVENTO";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM TB_EVENTO WHERE ID = ?";
    private static final String UPDATE_SQL = "UPDATE TB_EVENTO SET DS_TITULO = ?, DS_EVENTO = ?, DT_EVENTO = ? WHERE ID = ?";
    private static final String DELETE_SQL = "DELETE FROM TB_EVENTO WHERE ID = ?";

    private Connection conexao;

    public EventoDao(Connection conexao) {
        this.conexao = conexao;
    }

    // Cadastrar evento
    public void cadastrar(Evento evento) throws SQLException {
        // Atualizado para usar RETURN_GENERATED_KEYS
        try (PreparedStatement stmt = conexao.prepareStatement(INSERT_SQL, new String[]{"ID"})) {
            preencherStatementComEvento(stmt, evento);

            // Executa a inserção
            stmt.executeUpdate();

            // Recupera o ID gerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    evento.setId(rs.getInt(1));
                }
            }
        }
    }

    // Pesquisar evento por ID
    public Evento pesquisarPorId(int id) throws IdNaoEncontradoException, SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return parseEvento(rs);
                } else {
                    throw new IdNaoEncontradoException("Evento com ID " + id + " não encontrado.");
                }
            }
        }
    }

    // Listar todos os eventos
    public List<Evento> listar() throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                eventos.add(parseEvento(rs));
            }
        }
        return eventos;
    }

    // Atualizar evento
    public void atualizar(Evento evento) throws IdNaoEncontradoException, SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement(UPDATE_SQL)) {
            preencherStatementComEvento(stmt, evento);
            stmt.setInt(4, evento.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IdNaoEncontradoException("Evento com ID " + evento.getId() + " não encontrado para atualização.");
            }
        }
    }

    // Remover evento por ID
    public void remover(int id) throws IdNaoEncontradoException, SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IdNaoEncontradoException("Evento com ID " + id + " não encontrado para remoção.");
            }
        }
    }

    // Preencher PreparedStatement com os dados do Evento
    private void preencherStatementComEvento(PreparedStatement stmt, Evento evento) throws SQLException {
        stmt.setString(1, evento.getTitulo());
        stmt.setString(2, evento.getDescricao());
        stmt.setDate(3, Date.valueOf(evento.getDataEvento()));
    }

    // Parse do ResultSet para a classe Evento
    private Evento parseEvento(ResultSet rs) throws SQLException {
        Evento evento = new Evento();
        evento.setId(rs.getInt("ID"));
        evento.setTitulo(rs.getString("DS_TITULO"));
        evento.setDescricao(rs.getString("DS_EVENTO"));
        evento.setDataEvento(rs.getDate("DT_EVENTO").toLocalDate());
        return evento;
    }
}
