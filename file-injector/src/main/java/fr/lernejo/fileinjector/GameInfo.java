package fr.lernejo.fileinjector;

public record GameInfo (
    int id,
    String title,
    String thumbnail,
    String short_description,
    String genre,
    String platform,
    String publisher,
    String developer,
    String release_date
){}
