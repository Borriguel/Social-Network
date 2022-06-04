package com.api.socialnetwork.services;

import com.api.socialnetwork.models.Usuario;
import com.api.socialnetwork.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void deletar(Usuario usuario){
        usuarioRepository.delete(usuario);
    }

    public Optional<Usuario> acharUsuarioPorId(UUID id){
        return usuarioRepository.findById(id);
    }

    public Page<Usuario> listarUsuarios(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
