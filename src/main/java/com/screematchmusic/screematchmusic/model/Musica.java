package com.screematchmusic.screematchmusic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long id_denzer;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    public Musica() { }

    public Musica(String titulo, Artista artista) {
        this.titulo = titulo;
        this.artista = artista;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nome) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "nome='" + titulo + '\'' +
                ", artista=" + artista +
                '}';
    }
}
