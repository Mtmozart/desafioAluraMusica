package com.screematchmusic.screematchmusic.model;

public class Musica {

    private String nome;
    private Artista artista;

    public Musica() {

    }

    public Musica(String nome, Artista artista) {
        this.nome = nome;
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
                "nome='" + nome + '\'' +
                ", artista=" + artista.getName() +
                '}';
    }
}
