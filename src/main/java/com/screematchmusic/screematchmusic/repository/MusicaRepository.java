package com.screematchmusic.screematchmusic.repository;

import com.screematchmusic.screematchmusic.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicaRepository extends JpaRepository <Musica, Long> {


}
