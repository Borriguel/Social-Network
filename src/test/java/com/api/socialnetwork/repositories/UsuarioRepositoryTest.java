package com.api.socialnetwork.repositories;

import com.api.socialnetwork.models.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Testes do repositório de usuário")
class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Salvar persiste usuário se OK")
    void salvar_PersistirUsuario() {
        Usuario usuario = criarUsuario();
        Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
        Assertions.assertThat(usuarioSalvo).isNotNull();
        Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
        Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo(usuario.getNome());
        Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo(usuario.getEmail());
    }

    @Test
    @DisplayName("Edita usuário se OK")
    void update_EditaUsuario() {
        Usuario usuario = criarUsuario();
        Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
        usuarioSalvo.setEmail("cr7@gmail.com");
        usuarioSalvo.setNome("CR7");
        Usuario usuarioEditado = this.usuarioRepository.save(usuarioSalvo);
        Assertions.assertThat(usuarioEditado).isNotNull();
        Assertions.assertThat(usuarioEditado.getId()).isNotNull();
        Assertions.assertThat(usuarioEditado.getNome()).isEqualTo(usuarioSalvo.getNome());
        Assertions.assertThat(usuarioEditado.getEmail()).isEqualTo(usuarioSalvo.getEmail());
    }

    @Test
    @DisplayName("Deleta usuário se OK")
    void delete_ApagaUsuario(){
        Usuario usuario = criarUsuario();
        Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
        this.usuarioRepository.delete(usuarioSalvo);
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(usuarioSalvo.getId());
        Assertions.assertThat(usuarioOptional).isEmpty();
    }

    @Test
    @DisplayName("Acha usuário se OK")
    void find_ListaUsuarioPorId(){
        Usuario usuario = criarUsuario();
        Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(usuarioSalvo.getId());
        Assertions.assertThat(usuarioOptional).isPresent();
        Assertions.assertThat(usuarioOptional.get().getId()).isEqualTo(usuarioSalvo.getId());
    }

    private Usuario criarUsuario() {
        return new Usuario("Cristiano Ronaldo", "cristiano.ronaldo@gmail.com");
    }
}
