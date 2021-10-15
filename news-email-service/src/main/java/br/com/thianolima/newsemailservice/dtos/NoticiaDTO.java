package br.com.thianolima.newsemailservice.dtos;

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
