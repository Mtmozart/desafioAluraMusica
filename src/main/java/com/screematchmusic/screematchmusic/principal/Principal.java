package com.screematchmusic.screematchmusic.principal;

import com.screematchmusic.screematchmusic.repository.ArtistaRepository;
import com.screematchmusic.screematchmusic.service.AppServicesMain;

import java.util.Scanner;

public class Principal {

    private AppServicesMain appServicesMain = new AppServicesMain();



    Scanner sc = new Scanner(System.in);

    public Principal(AppServicesMain app) {
        this.appServicesMain = app;
    }

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            String menu = """
                        1 - Cadastrar artistas
                        2 - Cadastrar músicas
                        3 - Listar artistas
                        4 - Listar músicas
                        5 - Buscar músicas por artistas
                        6 - Pesquisar dados sobre um artista                                                              
                        0 - Sair  
                    """;

            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    appServicesMain.cadastrarArtista();
                    break;
                case 2:
                    appServicesMain.cadastrarMusica();
                    break;
                case 3:
                    appServicesMain.listarArtistas();
                    break;
                case 4:
                    appServicesMain.listarMusica();
                    break;

                case 5:
                    appServicesMain.buscarMusicaPorArtista();
                    break;
                case 6:
                    appServicesMain.pesquisarDadosSobreArtista();
                    break;
                case 7:
                    appServicesMain.pesquisarDadosSobreArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }


        }
        sc.close();
    }

}
