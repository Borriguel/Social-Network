package com.api.socialnetwork.exception;

import java.time.LocalDateTime;

public class ExceptionDetails {
    protected String erro;
    protected int status;
    protected String detalhes;
    protected String mensagemDev;
    protected LocalDateTime tempo;

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getMensagemDev() {
        return mensagemDev;
    }

    public void setMensagemDev(String mensagemDev) {
        this.mensagemDev = mensagemDev;
    }

    public LocalDateTime getTempo() {
        return tempo;
    }

    public void setTempo(LocalDateTime tempo) {
        this.tempo = tempo;
    }

}
