package com.api.socialnetwork.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "SCRAPS")
public class Scrap implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "DATA", nullable = false, updatable = false)
    private LocalDateTime data;

    @Column(name = "MENSAGEM", nullable = false, length = 140)
    private String mensagem;

    @ManyToOne
    private Usuario usuario;

    public Scrap() {
    }

    public Scrap(LocalDateTime data, String mensagem, Usuario usuario) {
        this.data = data;
        this.mensagem = mensagem;
        this.usuario = usuario;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scrap scrap = (Scrap) o;

        return id.equals(scrap.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
