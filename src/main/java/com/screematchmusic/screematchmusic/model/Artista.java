package com.screematchmusic.screematchmusic.model;

import java.util.ArrayList;
import java.util.List;

public class Artista {
    private String name;
    private TipoArtista tipo;
    private ArrayList<Musica> musicas;
    private String informacoes;

    public Artista() {
    }

    public Artista(String name, String tipo, String informacoes) {
        this.name = name;
        this.tipo = TipoArtista.fromString(tipo);
        this.musicas = new ArrayList<>();
        this.informacoes = informacoes;
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

    public ArrayList<Musica> getMusicas() {
        return musicas;
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
