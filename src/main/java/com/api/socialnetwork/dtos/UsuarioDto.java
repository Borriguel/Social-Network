package com.api.socialnetwork.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class UsuarioDto {
    @NotBlank(message = "Campo nome obrigatório")
    @Size(max = 100, message = "Campo nome deve conter no máximo 100 caracteres")
    @Schema(description = "Nome completo do usuário", example = "Cristiano Ronaldo dos Santos Aveiro")
    private String nome;
    @NotBlank(message = "Campo email obrigatório")
    @Size(max = 256)
    @Schema(description = "Email de cadastro", example = "cristiano.ronaldo@gmail.com")
    private String email;
    private final Date data = new Date();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
