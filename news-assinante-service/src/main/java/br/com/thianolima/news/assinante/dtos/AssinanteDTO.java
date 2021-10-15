package br.com.thianolima.news.assinante.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssinanteDTO {

    Long id;
    String nome;
    String email;
}
