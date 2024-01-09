package com.screematchmusic.screematchmusic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.screematchmusic.screematchmusic.model.DadosMusica;
import com.screematchmusic.screematchmusic.model.Musica;

import java.util.Collections;
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


    public <T> List<T> obterlista(String json, Class<T> classe) {
        try {
           // return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, classe));
            return Collections.singletonList(mapper.readValue(json, classe));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
