package br.com.thianolima.news.assinante.vos;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssinanteVO {

    @ApiModelProperty(example = "francisco jose pereira lima", required = true)
    @NotBlank
    String nome;

    @ApiModelProperty(example = "thianolima@hotmail.com", required = true)
    @NotBlank
    @Email
    String email;
}
