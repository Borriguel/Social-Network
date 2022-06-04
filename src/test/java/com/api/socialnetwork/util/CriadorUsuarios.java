package com.api.socialnetwork.util;

import com.api.socialnetwork.dtos.UsuarioDto;
import com.api.socialnetwork.models.Usuario;

import java.util.UUID;

public class CriadorUsuarios {

    public static Usuario criarUsuario(){
        return new Usuario("Messi","messi@gmail.com");
    }
    public static Usuario criarUsuarioValido(){
        Usuario usuario = new Usuario();
        usuario.setNome("Messi");
        usuario.setEmail("messi@gmail.com");
        usuario.setId(UUID.fromString("4c6184de-9c14-42f1-856e-d696a594c93e"));
        return usuario;
    }
    public static Usuario criarUsuarioValidoEditado(){
        Usuario usuario = new Usuario();
        usuario.setNome("Messi 2");
        usuario.setEmail("messi@gmail.com");
        usuario.setId(UUID.fromString("4c6184de-9c14-42f1-856e-d696a594c93e"));
        return usuario;
    }
    public static UsuarioDto criarUsuarioDto(){
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("Messi");
        usuarioDto.setEmail("messi@gmail.com");
        return usuarioDto;
    }
}
