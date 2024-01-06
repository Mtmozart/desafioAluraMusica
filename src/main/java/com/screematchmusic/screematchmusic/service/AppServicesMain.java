package com.screematchmusic.screematchmusic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.screematchmusic.screematchmusic.model.Artista;
import com.screematchmusic.screematchmusic.model.DadosArtista;
import com.screematchmusic.screematchmusic.model.Musica;

import java.util.*;
import java.util.stream.Collectors;

public class AppServicesMain {
    private Artista artista = new Artista();
    private ArrayList<Artista> artistas = new ArrayList<>();
    private Musica musica = new Musica();
    private List<Musica> musicas = new ArrayList<>();
    private ConsumoAPI api = new ConsumoAPI();
    private ConversorDeDados conveter = new ConversorDeDados();
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

    public String getArtitas(){
        try {
            var json = api.obterDados("https://www.vagalume.com.br/u2/index.js");

            var convertido = conveter.obterDados(json,DadosArtista.class);
            System.out.println(convertido);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao obter dados da API";
        }
    }

    public List<Artista> listarArtistas() {
        List<Artista> artistasParaBusca = artistas.stream()
                .sorted(Comparator.comparing(Artista::getName))
                .peek(a -> System.out.println("nome: " + a.getName()))
                .collect(Collectors.toList());
        return artistasParaBusca;
    }

    public void cadastrarMusica() {
        System.out.println("Artistas disponivéis: ");
        List<Artista> artistasParaMusica = listarArtistas();
        System.out.println("--------------------");
        System.out.println("Escolha um artista para adiciona a música");
        String nomeDoArtista = sc.nextLine();
        Optional<Artista> artistaBusca = artistasParaMusica.stream()
                .filter(e -> e.getName().toUpperCase().equalsIgnoreCase(nomeDoArtista.toUpperCase()))
                .findFirst();
        if (artistaBusca.isPresent()) {
            System.out.println("Digite o nome da música");
            var nomeMusica = sc.nextLine();
            musica = new Musica(nomeMusica, artistaBusca.get());
            artista.addMusica(musica);
            addMusicasNaLista(musica);
            System.out.println(musica);
        } else {
            System.out.println("Artista não encontrado");
        }
    }

    public void addMusicasNaLista(Musica musica) {
        musicas.add(musica);
    }

    public void listarMusica() {
        List<Musica> musicasListadas = musicas.stream()
                .sorted(Comparator.comparing(Musica::getTitulo))
                .collect(Collectors.toList());

        musicasListadas.forEach(System.out::println);
    }

    public void buscarMusicaPorArtista() {
        var artistaBuscado = listarArtistas();
        System.out.println("Digite o nome de um artista");
        String artistaNome = sc.nextLine();

        Optional<Artista> artistaEncontrado = artistaBuscado.stream()
                .filter(a -> a.getName().equalsIgnoreCase(artistaNome))
                .findFirst();

        if (artistaEncontrado.isPresent()) {
            artistaEncontrado.get().getMusicas().forEach(System.out::println);
        } else {
            System.out.println("Artista não encontrado");
        }


    }

    public void pesquisarDadosSobreArtista() {
        var artistaBuscaInformacoes = listarArtistas();
        System.out.println("Digite o nome de um artista");
        var buscaInformacoes = sc.nextLine();
        var artistaEncontrado = artistas.stream()
                .filter(a -> a.getName().toUpperCase().contains(buscaInformacoes.toUpperCase()))
                .findFirst();

        if (artistaEncontrado.isEmpty()) {
            System.out.println(artistaEncontrado);
        } else {
            System.out.println(artistaEncontrado.get());
        }
    }

}
