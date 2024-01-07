package com.screematchmusic.screematchmusic.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;

    @OneToMany(mappedBy = "artista")
    private List<Musica> musicas = new ArrayList<>();
        @Column(name = "informacoes", length = 1000)
        private String informacoes;

    public Artista() {
    }

    public Artista(String name, String tipo, String informacoes) {
        this.name = name;
        this.tipo = TipoArtista.fromString(tipo);
        this.musicas = new ArrayList<>();
        this.informacoes = informacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TipoArtista getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtista tipo) {
        this.tipo = tipo;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public void addMusica(Musica musica) {
        musicas.add(musica);
    }

    @Override
    public String toString() {
        return "Artista{" +
                "name='" + name + '\'' +
                ", tipo=" + tipo +
                ", musicas=" + musicas +
                ", informacoes='" + informacoes + '\'' +
                '}';
    }
}
