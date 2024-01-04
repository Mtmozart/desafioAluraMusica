package com.screematchmusic.screematchmusic.service;

import com.screematchmusic.screematchmusic.model.Artista;
import com.screematchmusic.screematchmusic.model.Musica;

import java.util.*;
import java.util.stream.Collectors;

public class AppServicesMain {
    private Artista artista = new Artista();
    private ArrayList<Artista> artistas = new ArrayList<>();
    private Musica musica = new Musica();
    Scanner sc = new Scanner(System.in);


    public void cadastrarArtista() {
       System.out.println("Digite o nome do artista");
       var nome = sc.nextLine();
       System.out.println("Digite o tipo");
       var text = sc.nextLine();
       String informacoes = ConsultaChatGPT.obterTraducao(nome);
       artista = new Artista(nome, text, informacoes);
       artistas.add(artista);
       System.out.println(artista);
    }

    public List<Artista> listarArtistas(){
        List<Artista> artistasParaBusca = artistas.stream()
                .sorted(Comparator.comparing(Artista::getName))
                .peek(a -> System.out.println("nome: " + a.getName()))
                .collect(Collectors.toList());
        return artistasParaBusca;
    }

    public void cadastrarMusica() {
        System.out.println("Veja se seu artista está na lista, caso não o cadastre: ");
        List<Artista> artistasParaMusica = listarArtistas();
        System.out.println("--------------------");
        System.out.println("Escolha um artista para adiciona a música");
        String nomeDoArtista = sc.nextLine();
        Optional<Artista> artistaBusca = artistasParaMusica.stream()
                        .filter(e -> e.getName().toUpperCase().equalsIgnoreCase(nomeDoArtista.toUpperCase()))
                        .findFirst();


        if(artistaBusca.isPresent()) {
            System.out.println("Digite o nome da música");
            var nomeMusica = sc.nextLine();
            musica = new Musica(nomeMusica, artistaBusca.get());
            artista.addMusica(musica);
            System.out.println(musica);
        }
        else {
            System.out.println("Artista não encontrado");
        }
    }

    public void listarMusicas() {


    }

    public void buscarMusicaPorArtista() {
        System.out.println("método de buscar música por artista");
    }

    public void pesquisarDadosSobreArtista() {
        System.out.println("pesquisar dados sobre o artitas");
    }

}
