package com.screematchmusic.screematchmusic.service;

import com.screematchmusic.screematchmusic.model.Artista;
import com.screematchmusic.screematchmusic.model.DadosArtista;
import com.screematchmusic.screematchmusic.model.Musica;
import com.screematchmusic.screematchmusic.repository.ArtistaRepository;
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
    private String URLApiFim = "top?limit=150";
    private ArtistaRepository repositorio;

    public AppServicesMain() {
    }
    public AppServicesMain(ArtistaRepository repository) {
        this.repositorio = repository;
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

    public void cadastrarMusicas() {
        listarArtistas();
        System.out.println("Digite o nome do artista que deseja baixar as músicas: ");
        String nomeArtista = sc.nextLine();
        Optional<Artista> artistaEcontrado= repositorio.findByNameContainingIgnoreCase(nomeArtista);

        if (artistaEcontrado.isPresent()){
            Integer id_denzer = artistaEcontrado.get().getId_denzer();
           var json = api.obterDados(URLApi + id_denzer + URLApiFim);
            System.out.println(json);

        }
        else{
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

//    public void listarMusica() {
//        List<Musica> musicasListadas = musicas.stream()
//                .sorted(Comparator.comparing(Musica::getTitulo))
//                .collect(Collectors.toList());
//
//        musicasListadas.forEach(System.out::println);
//    }

//    public void buscarMusicaPorArtista() {
//        var artistaBuscado = listarArtistas();
//        System.out.println("Digite o nome de um artista");
//        String artistaNome = sc.nextLine();
//
//        Optional<Artista> artistaEncontrado = artistaBuscado.stream()
//                .filter(a -> a.getName().equalsIgnoreCase(artistaNome))
//                .findFirst();
//
//        if (artistaEncontrado.isPresent()) {
//            artistaEncontrado.get().getMusicas().forEach(System.out::println);
//        } else {
//            System.out.println("Artista não encontrado");
//        }


  //  }

//    public void pesquisarDadosSobreArtista() {
//        var artistaBuscaInformacoes = listarArtistas();
//        System.out.println("Digite o nome de um artista");
//        var buscaInformacoes = sc.nextLine();
//        var artistaEncontrado = artistas.stream()
//                .filter(a -> a.getName().toUpperCase().contains(buscaInformacoes.toUpperCase()))
//                .findFirst();
//
//        if (artistaEncontrado.isEmpty()) {
//            System.out.println(artistaEncontrado);
//        } else {
//            System.out.println(artistaEncontrado.get());
//        }


    }


