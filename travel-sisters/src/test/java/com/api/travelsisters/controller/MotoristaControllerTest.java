package com.api.travelsisters.controller;

import com.api.travelsisters.model.EmpresaModel;
import com.api.travelsisters.model.MotoristaModel;
import com.api.travelsisters.model.UsuarioModel;
import com.api.travelsisters.repository.EmpresaRepository;
import com.api.travelsisters.repository.MotoristaRepository;
import com.api.travelsisters.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.api.travelsisters.dto.AlterarPerfilMotoristaDTO;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MotoristaController.class)
class MotoristaControllerTest {

    @MockBean
    private MotoristaRepository motoristaRepository;

    @MockBean
    private EmpresaRepository empresaRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    @InjectMocks
    private MotoristaController controller;

    @Test
    @DisplayName("Listar deve retornar motorista cadastrado, OK")
    void listarUsuarios_quandoExistemUsuariosOK() {
        MotoristaModel motorista = new MotoristaModel();
        when(motoristaRepository.findAll()).thenReturn(Arrays.asList(motorista));

        ResponseEntity<List<MotoristaModel>> response = controller.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    @DisplayName("Listar nao deve retornar nada, NO_CONTENT")
    void listarMotoristas_quandoNaoExistemMotoristasNO_CONTENT() {
        when(motoristaRepository.findAll()).thenReturn(List.of());

        ResponseEntity<List<MotoristaModel>> response = controller.listar();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Buscar deve retornar motorista cadastrada, OK")
    void buscarMotoristaPorIdExistenteOK() {
        MotoristaModel motoristaMock = new MotoristaModel();
        when(motoristaRepository.findById(1)).thenReturn(Optional.of(motoristaMock));

        ResponseEntity<MotoristaModel> response = controller.findByID(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(motoristaMock, response.getBody());
    }

//    @Test
//    @DisplayName("Cadastrar deve registrar a motorista no banco, CREATED")
//    void cadastrarMotorista_OK() throws Exception {
//        MotoristaModel motorista = new MotoristaModel();
//        EmpresaModel empresa = new EmpresaModel();
//        UsuarioModel usuario = new UsuarioModel();
//
//        when(empresaRepository.encontrarPorId(empresa.getId())).thenReturn(empresa);
//        when(usuarioRepository.encontrarPorId(usuario.getId())).thenReturn(usuario);
//        when(motoristaRepository.save(motorista)).thenReturn(motorista);
//
//        mockMvc.perform(post("/motoristas/cadastrar/{idUsuario}", usuario.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(motorista))) // Converter o objeto para JSON
//                        .andExpect(status().isCreated());
//    }

    @Test
    @DisplayName("Buscar deve retornar excecao, NOT_FOUND")
    void buscarMotoristaPorIdInexistenteNOT_FOUND() {
        when(motoristaRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity response = controller.findByID(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Alterar perfil deve retornar 1, OK")
    void alterarPerfilDeMotoristaOK() {
        AlterarPerfilMotoristaDTO motoristaDTO = new AlterarPerfilMotoristaDTO();
        when(motoristaRepository.atualizarPerfilMotorista(1,
                "teste",
                "teste@email.com",
                "123")).thenReturn(1);

        ResponseEntity response = controller.alterar(motoristaDTO, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Deletar motorista deve excluir do banco, OK")
    void deletarMotoristaPorIdExistenteOK() {
        MotoristaModel motoristaMock = new MotoristaModel();
        when(motoristaRepository.findById(anyInt())).thenReturn(Optional.of(motoristaMock));

        ResponseEntity<String> response = controller.deletar(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Deletar motorista deve retornar excecao, NOT_FOUND")
    void deletarMotoristaPorIdNaoEncontrado_NOT_FOUND() {
        when(motoristaRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity response = controller.deletar(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}