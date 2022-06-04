package com.api.socialnetwork.controllers;

import com.api.socialnetwork.models.Usuario;
import com.api.socialnetwork.services.UsuarioService;
import com.api.socialnetwork.util.CriadorUsuarios;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class UsuarioControllerTest {
    @InjectMocks
    private UsuarioController usuarioController;
    @Mock
    private UsuarioService usuarioServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Usuario> usuariosPage = new PageImpl<>(List.of(CriadorUsuarios.criarUsuarioValido()));
        BDDMockito.when(usuarioServiceMock.listarUsuarios(ArgumentMatchers.any())).thenReturn(usuariosPage);

        BDDMockito.when(usuarioServiceMock.acharUsuarioPorId(ArgumentMatchers.any()))
                .thenReturn(Optional.of(CriadorUsuarios.criarUsuarioValido()));

        BDDMockito.when(usuarioServiceMock.salvar(ArgumentMatchers.any(Usuario.class)))
                .thenReturn(CriadorUsuarios.criarUsuarioValido());

        BDDMockito.doNothing().when(usuarioServiceMock).deletar(ArgumentMatchers.any(Usuario.class));
    }
    @Test
    @DisplayName("Lista todos os usuários se OK")
    void listarTodosUsuarios(){
        String resultadoEsperado = CriadorUsuarios.criarUsuarioValido().getNome();
        Page<Usuario> usuarioPage = usuarioController.listarTodosUsuarios(null).getBody();
        Assertions.assertThat(usuarioPage).isNotNull();
        Assertions.assertThat(usuarioPage.toList()).isNotEmpty();
        Assertions.assertThat(usuarioPage.toList().get(0).getNome()).isEqualTo(resultadoEsperado);
    }
    
    @Test
    @DisplayName("Lista um usuário se OK")
    void listarUsuarioPorId(){
        UUID resultadoEsperado = CriadorUsuarios.criarUsuarioValido().getId();
        Usuario usuarioTeste = usuarioController.listarUsuarioPorId(UUID.fromString("4c6184de-9c14-42f1-856e-d696a594c93e")).getBody();
        Assertions.assertThat(usuarioTeste).isNotNull();
        Assertions.assertThat(usuarioTeste.getId()).isNotNull().isEqualTo(resultadoEsperado);
    }

    @Test
    @DisplayName("Salva um usuário se OK")
    void salvarUsuario(){
        Usuario usuarioTeste = usuarioController.salvarUsuario(CriadorUsuarios.criarUsuarioDto()).getBody();
        Assertions.assertThat(usuarioTeste).isEqualTo(CriadorUsuarios.criarUsuarioValido());
    }

    @Test
    @DisplayName("Deleta usuário se OK")
    void deletarUsuario(){
        ResponseEntity<Void> deleteTest = usuarioController.deletarUsuario(null);
        Assertions.assertThat(deleteTest.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}