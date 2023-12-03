package com.api.travelsisters.controller;

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
    void listarUsuarios_quandoNaoExistemUsuarios() {
        when(repository.findAll()).thenReturn(List.of());

        ResponseEntity<List<UsuarioModel>> response = controller.listar();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Listar deve retornar o usuario cadastrado, OK")
    void listarUsuarios_quandoExistemUsuarios() {
        UsuarioModel usuario = new UsuarioModel();
        when(repository.findAll()).thenReturn(Arrays.asList(usuario));

        ResponseEntity<List<UsuarioModel>> response = controller.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    @DisplayName("Buscar ID deve retornar usuario existe com o mesmo ID, OK")
    void buscarPorId_quandoExisteUsuario() {
        UsuarioModel u = new UsuarioModel();
        u.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(u));

        ResponseEntity<UsuarioModel> response = controller.findByID(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    @DisplayName("Buscar ID deve retornar execao por nao encontrar usuario, NOT_FOUND")
    void buscarPorId_quandoNaoExisteUsuario() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<UsuarioModel> response = controller.findByID(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Deletar deve excluir o usuario com base no id, OK")
    void deletarUsuarioPorIdOk() {
        UsuarioModel u = new UsuarioModel();
        when(repository.findById(1)).thenReturn(Optional.of(u));

        ResponseEntity<?> response = controller.deletar(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Deletar deve retornar excecao por nao encontrar usuario, NOT_FOUND")
    void deletarUsuarioPorIdNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<?> response = controller.deletar(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}