package br.com.thianolima.news.assinante.templates;

import br.com.thianolima.news.assinante.vos.AssinanteVO;
import lombok.Getter;

public class AssinanteVOTemplate extends BaseTemplate{

    @Getter
    private static final AssinanteVOTemplate instance = new AssinanteVOTemplate();

    public AssinanteVO getObjectValid(){
        return AssinanteVO.builder()
                .nome(faker.superhero().name())
                .email(faker.bothify("????##@gmail.com"))
                .build();
    }
}
