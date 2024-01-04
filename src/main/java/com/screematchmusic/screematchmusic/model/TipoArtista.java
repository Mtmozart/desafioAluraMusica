package com.screematchmusic.screematchmusic.model;

public enum TipoArtista {

    SOLO("solo"),
    DUPLA("dupla"),
    BANDA("banda");
    private String tipoArtistaString;

    TipoArtista(String tipoArtistaString) {
        this.tipoArtistaString = tipoArtistaString;
    }

    public static TipoArtista fromString(String text){
        for(TipoArtista tipoArtista : TipoArtista.values()){
            if(tipoArtista.tipoArtistaString.equalsIgnoreCase(text)){
                return  tipoArtista;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
