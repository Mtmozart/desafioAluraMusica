package com.screematchmusic.screematchmusic.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMusica(@JsonAlias("id") Integer id_denzer,
                          @JsonAlias("title") String titulo

) {
}
