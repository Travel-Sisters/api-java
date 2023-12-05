package com.api.travelsisters.controller;

import com.api.travelsisters.dto.AlterarPerfilPassageiraDTO;
import com.api.travelsisters.model.MotoristaModel;
import com.api.travelsisters.model.UsuarioModel;
import com.api.travelsisters.repository.MotoristaRepository;
import com.api.travelsisters.repository.UsuarioRepository;
import com.api.travelsisters.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UsuarioController.class)
class UsuarioControllerTest {
    @MockBean
    private UsuarioRepository repository;

    @MockBean
    private MotoristaRepository repositoryMotorista;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private PasswordEncoder encoder;

    @Autowired
    @InjectMocks
    private UsuarioController controller;

    @Test
    @DisplayName("Listar deve retornar que nao ha usuarios cadastrados, NO_CONTENT")
    void listarUsuarios_quandoNaoExistemUsuariosNO_CONTENT() {
        when(repository.findAll()).thenReturn(List.of());

        ResponseEntity<List<UsuarioModel>> response = controller.listar();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Listar deve retornar o usuario cadastrado, OK")
    void listarUsuarios_quandoExistemUsuariosOK() {
        UsuarioModel usuario = new UsuarioModel();
        when(repository.findAll()).thenReturn(Arrays.asList(usuario));

        ResponseEntity<List<UsuarioModel>> response = controller.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    @DisplayName("Buscar ID deve retornar usuario existe com o mesmo ID, OK")
    void buscarPorId_quandoExisteUsuarioOK() {
        UsuarioModel u = new UsuarioModel();
        u.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(u));

        ResponseEntity<UsuarioModel> response = controller.findByID(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    @DisplayName("Buscar ID deve retornar execao por nao encontrar usuario, NOT_FOUND")
    void buscarPorId_quandoNaoExisteUsuarioNOT_FOUND() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<UsuarioModel> response = controller.findByID(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Deletar deve excluir o usuario com base no id, OK")
    void deletarUsuarioPorIdOK() {
        UsuarioModel u = new UsuarioModel();
        when(repository.findById(1)).thenReturn(Optional.of(u));

        ResponseEntity response = controller.deletar(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Deletar deve retornar excecao por nao encontrar usuario, NOT_FOUND")
    void deletarUsuarioPorIdNotFoundNOT_FOUND() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<?> response = controller.deletar(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    @DisplayName("Verificar perfil do motorista existente, OK")
    void verificarPerfilMotoristaExistenteOK() {
        MotoristaModel motoristaModel = new MotoristaModel(); // Mock ou dados para o motorista
        when(repositoryMotorista.encontrarPorUsuarioId(1)).thenReturn(motoristaModel);

        ResponseEntity<MotoristaModel> response = controller.verificarPerfil(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(motoristaModel, response.getBody());
    }

    @Test
    @DisplayName("Verificar perfil do motorista inexistente, NO_CONTENT")
    void verificarPerfilMotoristaInexistenteNO_CONTENT() {
        when(repositoryMotorista.encontrarPorUsuarioId(1)).thenReturn(null);

        ResponseEntity<MotoristaModel> response = controller.verificarPerfil(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    @DisplayName("Alterar perfil do usuário com informações válidas, OK")
    void alterarPerfilUsuarioComInfosValidasOK() {
        AlterarPerfilPassageiraDTO dto = new AlterarPerfilPassageiraDTO();
        dto.setNome("Novo Nome");
        dto.setEmail("novo@email.com");
        dto.setSenha("novaSenha");

        when(repository.atualizarPerfilUsuario(
                eq(1), eq(dto.getNome()), eq(dto.getEmail()), eq(dto.getSenha())))
                .thenReturn(1);

        ResponseEntity<?> response = controller.alterar(dto, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Alterar perfil do usuário com informações vazias, OK")
    void alterarPerfilUsuarioComInfosVaziasOK() {
        AlterarPerfilPassageiraDTO dto = new AlterarPerfilPassageiraDTO();

        when(repository.atualizarPerfilUsuario(
                eq(1), eq(null), eq(null), eq(null)))
                .thenReturn(1);

        ResponseEntity<?> response = controller.alterar(dto, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Alterar perfil do usuário com informações nulas, OK")
    void alterarPerfilUsuarioComInfosNulasOK() {
        AlterarPerfilPassageiraDTO dto = new AlterarPerfilPassageiraDTO();
        dto.setNome(null);
        dto.setEmail(null);
        dto.setSenha(null);

        when(repository.atualizarPerfilUsuario(eq(1), eq(null), eq(null), eq(null)))
                .thenReturn(1);

        ResponseEntity<?> response = controller.alterar(dto, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}