package br.com.thianolima.news.noticia.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticiaDTO {

    Long id;
    String titulo;
    String texto;
}
