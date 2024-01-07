package com.screematchmusic.screematchmusic.repository;

import com.screematchmusic.screematchmusic.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNameContainingIgnoreCase(String nomeArtista);
}
