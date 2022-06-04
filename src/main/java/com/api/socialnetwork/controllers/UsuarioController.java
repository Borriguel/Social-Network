package com.api.socialnetwork.controllers;

import com.api.socialnetwork.dtos.UsuarioDto;
import com.api.socialnetwork.exception.BadRequestException;
import com.api.socialnetwork.models.Usuario;
import com.api.socialnetwork.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Realiza a inserção de um novo usuário no banco de dados.")
    @Tag(name = "Usuário")
    public ResponseEntity<Usuario> salvarUsuario(@Valid @RequestBody UsuarioDto usuarioDto){
        if(usuarioService.existsByEmail(usuarioDto.getEmail())){
            throw new BadRequestException("Erro: Este email já existe.");
        }
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuario));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista usuários passando seu Id(UUID) como parâmetro.")
    @Tag(name = "Usuário")
    public ResponseEntity<Usuario> listarUsuarioPorId(@PathVariable(value = "id") UUID id){
        Optional<Usuario> usuarioOptional = usuarioService.acharUsuarioPorId(id);
        if(usuarioOptional.isEmpty()){
            throw new BadRequestException("Erro: Id inválido.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
    }

    @GetMapping
    @Operation(summary = "Lista todos os usuários de forma paginada com tamanho de 8 elementos por página ordenados pelo nome.")
    @Tag(name = "Usuário")
    public ResponseEntity<Page<Usuario>> listarTodosUsuarios(@PageableDefault(size = 8, sort = "nome")Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios(pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um usuário do bando de dados passando seu id(UUID) como parâmetro.")
    @Tag(name = "Usuário")
    public ResponseEntity<Void> deletarUsuario(@PathVariable(value = "id") UUID id){
        Optional<Usuario> usuarioOptional = usuarioService.acharUsuarioPorId(id);
        if(usuarioOptional.isEmpty()){
            throw new BadRequestException("Erro: Id inválido.");
        }
        usuarioService.deletar(usuarioOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita os atributos de um usuário passado o id(UUID) como parâmetro.")
    @Tag(name = "Usuário")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable(value = "id") UUID id, @RequestBody UsuarioDto usuarioDto){
        Optional<Usuario> usuarioOptional = usuarioService.acharUsuarioPorId(id);
        if(usuarioOptional.isEmpty()){
            throw new BadRequestException("Erro: Id inválido.");
        }
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        usuario.setId(usuarioOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.salvar(usuario));
    }
}
