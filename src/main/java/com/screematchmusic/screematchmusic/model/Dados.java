package com.screematchmusic.screematchmusic.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(@JsonAlias("id") Long id_denzer,
                    @JsonAlias("title") String nome
) {
}
