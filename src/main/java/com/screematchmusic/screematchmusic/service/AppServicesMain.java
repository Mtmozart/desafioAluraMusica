package com.screematchmusic.screematchmusic.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.screematchmusic.screematchmusic.model.*;
import com.screematchmusic.screematchmusic.repository.ArtistaRepository;
import com.screematchmusic.screematchmusic.repository.MusicaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppServicesMain {
    private Artista artista = new Artista();
    private List<Artista> artistas = new ArrayList<>();
    private Musica musica = new Musica();
    private List<Musica> musicas = new ArrayList<>();
    private ConsumoAPI api = new ConsumoAPI();
    private ConversorDeDados conveter = new ConversorDeDados();
    private String URLApi = "https://api.deezer.com/artist/";
    private String URLApiFim = "/top?limit=15";
    private ArtistaRepository repositorio;
    private MusicaRepository musicaRepository;

    public AppServicesMain() {
    }

    public AppServicesMain(ArtistaRepository repository, MusicaRepository musicaRepository) {

        this.repositorio = repository;
        this.musicaRepository = musicaRepository;
    }

    Scanner sc = new Scanner(System.in);


    public void cadastrarArtista() {
        System.out.println("Digite o nome do artista");
        var nome = sc.nextLine();
        DadosArtista artistaNome = getArtitas(nome);
        System.out.println("Digite o tipo: Solo/Dupla/Banda");
        var tipo = sc.nextLine();
        String informacoes = ConsultaChatGPT.obterTraducao(nome);
        System.out.println(informacoes);
        artista = new Artista(artistaNome.name(), tipo, artistaNome.id_denzer(), informacoes);
        System.out.println(artista);
        repositorio.save(artista);
    }

    public DadosArtista getArtitas(String artista) {
        artista = artista.replace(" ", "-");
        artista = artista.replace("'", "");
        String url = URLApi + artista;
        var json = api.obterDados(url);
        DadosArtista dados = conveter.obterDados(json, DadosArtista.class);
        System.out.println(dados);
        return dados;
    }

    public void listarArtistas() {
        artistas = repositorio.findAll();
        artistas.stream()
                .sorted(Comparator.comparing(Artista::getName)).forEach(System.out::println);
    }

    public void cadastrarTop15Musicas() {
        //para não esquecer https://api.deezer.com/search?q=mockbird
        listarArtistas();
        System.out.println("Digite o nome do artista que deseja baixar as músicas: ");
        String nome = sc.nextLine();
        Optional<Artista> artistaEncontrado = repositorio.findByNameContainingIgnoreCase(nome);
        if (artistaEncontrado.isPresent()) {
            Integer idDeezer = artistaEncontrado.get().getId_denzer();
            var json = api.obterDados(URLApi + idDeezer + URLApiFim);
            List<DadosMusica> musicasAPI = conveter.obterlista(json, DadosMusica.class);

            musicasAPI.forEach(m -> {
                m.musicas().forEach(n -> {
                    Artista artista = artistaEncontrado.get();
                    Musica musica = new Musica(n.nome(), n.id_denzer());
                    musica.setArtista(artista);
                    artista.getMusicas().add(musica);
                });
            });

            repositorio.save(artistaEncontrado.get());
        } else {
            System.out.println("Artista não encotrado");
        }

    }
//        Optional<Artista> artistaBusca = artistasParaMusica.stream()
//                .filter(e -> e.getName().toUpperCase().equalsIgnoreCase(nomeDoArtista.toUpperCase()))
//                .findFirst();
//        if (artistaBusca.isPresent()) {
//            System.out.println("Digite o nome da música");
//            var nomeMusica = sc.nextLine();
//            musica = new Musica(nomeMusica, artistaBusca.get());
//            artista.addMusica(musica);
//            addMusicasNaLista(musica);
//            System.out.println(musica);
//        } else {
//            System.out.println("Artista não encontrado");
//        }
//   }

//    public void addMusicasNaLista(Musica musica) {
//        //musicas.add(musica);
//    }

    public void listarMusica() {
        musicas = musicaRepository.findAll();
        musicas.stream()
                .sorted(Comparator.comparing(Musica::getTitulo)).forEach(System.out::println);

    }

    public void buscarMusicaPorArtista() {
        System.out.println("Digite o nome do artista");
        String nomeArtista = sc.nextLine();
        Optional<Artista> artistaEncontrado = repositorio.findByNameContainingIgnoreCase(nomeArtista);
        if (artistaEncontrado.isPresent()) {
            Artista artista = artistaEncontrado.get();
            List<Musica> musicas = artista.getMusicas();
            if (musicas.isEmpty()) {
                System.out.println("Sem músicas cadastradas para o artista " + artista.getName());
            } else {
                System.out.println("Músicas do artista " + artista.getName() + ":");
                var musicasListada = musicas.stream()
                        .sorted(Comparator.comparing(Musica::getTitulo))
                        .collect(Collectors.toList());
                musicasListada.forEach(m -> System.out.println(m.getTitulo())
                );

            }
        } else {
            System.out.println("Artista não encontrado");
        }


    }

    public void pesquisarDadosSobreArtista() {
        listarArtistas();
        System.out.println("Digite o nome do artista que deseja procurar o nome");
        String nome = sc.nextLine();
        var artistaInformacoes = repositorio.findByNameContainingIgnoreCase(nome);
        if (artistaInformacoes.isPresent()){
            System.out.println(artistaInformacoes.get().getInformacoes());
        }
        else {
            System.out.println("Artista não encontrado");
        }

    }

}


