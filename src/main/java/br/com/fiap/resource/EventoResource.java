package br.com.fiap.resource;

import br.com.fiap.dao.EventoDao;
import br.com.fiap.dto.EventoCadastroDto;
import br.com.fiap.dto.EventoResponseDto;
import br.com.fiap.exception.IdNaoEncontradoException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Evento;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Path("/eventos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventoResource {

    private EventoDao eventoDao;
    private ModelMapper modelMapper;

    public EventoResource() throws Exception {
        eventoDao = new EventoDao(ConnectionFactory.getConnection());
        modelMapper = new ModelMapper();
    }

    // Listar todos os eventos
    @GET
    public Response listarEventos() {
        try {
            List<Evento> eventos = eventoDao.listar();

            // Converte a lista de entidades para a lista de DTOs usando Collectors.toList()
            List<EventoResponseDto> eventosDto = eventos.stream()
                    .map(evento -> modelMapper.map(evento, EventoResponseDto.class))
                    .collect(Collectors.toList());

            return Response.ok(eventosDto).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar eventos: " + e.getMessage())
                    .build();
        }
    }

    // Buscar evento por ID
    @GET
    @Path("/{id}")
    public Response buscarEventoPorId(@PathParam("id") int id) {
        try {
            Evento evento = eventoDao.pesquisarPorId(id);
            return Response.ok(evento).build(); // Retorna o evento encontrado
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar evento: " + e.getMessage())
                    .build();
        }
    }

    // Cadastrar um novo evento
    @POST
    public Response cadastrarEvento(EventoCadastroDto dto, @Context UriInfo uriInfo) {
        try {
            // Converte DTO para a entidade Evento
            Evento evento = modelMapper.map(dto, Evento.class);
            eventoDao.cadastrar(evento);

            // Configura a URL do recurso criado
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(evento.getId()));

            return Response.created(builder.build()).entity(evento).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar evento: " + e.getMessage())
                    .build();
        }
    }

    // Atualizar um evento existente
    @PUT
    @Path("/{id}")
    public Response atualizarEvento(@PathParam("id") int id, Evento evento) {
        try {
            evento.setId(id); // Define o ID do evento para atualização
            eventoDao.atualizar(evento);
            return Response.ok().build(); // Retorna status 200 em caso de sucesso
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar evento: " + e.getMessage())
                    .build();
        }
    }

    // Remover um evento
    @DELETE
    @Path("/{id}")
    public Response removerEvento(@PathParam("id") int id) {
        try {
            eventoDao.remover(id);
            return Response.noContent().build(); // Retorna status 204 em caso de sucesso
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao remover evento: " + e.getMessage())
                    .build();
        }
    }
}
