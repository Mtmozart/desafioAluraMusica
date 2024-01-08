package com.screematchmusic.screematchmusic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.screematchmusic.screematchmusic.model.Musica;

import java.util.List;

public class ConversorDeDados implements IConversorDeDados{

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<Musica> obterLista(String json, Class<T> classe) {
        try {
            return (List<Musica>) mapper.readValue(json, new TypeReference<List<T>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao desserializar o JSON para lista.", e);
        }
    }


}
