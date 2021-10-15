package br.com.thianolima.news.noticia.vos;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticiaVO {

    @ApiModelProperty(example = "Boas vindas", required = true)
    @NotBlank
    @Size(max = 50)
    String titulo;

    @ApiModelProperty(example = "seja bem vindo", required = true)
    @NotBlank
    String texto;
}
