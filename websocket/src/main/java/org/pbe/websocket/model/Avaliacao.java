
package org.pbe.websocket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private int estrelas;

    @Column(length = 1000)
    private String comentario;

    protected Avaliacao() {} // exigido pelo JPA

    public Avaliacao(String nome, int estrelas, String comentario) {
        this.nome = nome;
        this.estrelas = estrelas;
        this.comentario = comentario;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public int getEstrelas() { return estrelas; }
    public String getComentario() { return comentario; }
}