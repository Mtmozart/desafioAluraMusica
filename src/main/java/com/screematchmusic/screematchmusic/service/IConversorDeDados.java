package com.screematchmusic.screematchmusic.service;

import com.screematchmusic.screematchmusic.model.DadosArtista;
import com.screematchmusic.screematchmusic.model.Musica;

import java.util.List;

public interface IConversorDeDados {
    <T> T  obterDados(String json, Class<T> classe);

   <T> List<Musica> obterLista(String json, Class<T> classe);

}
