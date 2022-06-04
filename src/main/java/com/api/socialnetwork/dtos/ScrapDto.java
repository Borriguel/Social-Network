package com.api.socialnetwork.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ScrapDto {
    @NotBlank(message = "Scrap não pode ser vazio")
    @Size(min = 2, max = 140, message = "Scrap deve conter no mínimo {min} e no máximo {max} caracteres")
    @Schema(description = "Mensagem do usuario.", example = "Meu nome é Cristiano Ronaldo e eu uso Clear Men!")
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
