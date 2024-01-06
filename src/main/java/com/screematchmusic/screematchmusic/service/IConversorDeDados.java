package com.screematchmusic.screematchmusic.service;

public interface IConversorDeDados {
    <T> T obterDados(String json, Class<T> classe);
}
