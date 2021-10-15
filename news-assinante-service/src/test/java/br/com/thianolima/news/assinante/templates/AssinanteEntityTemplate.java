package br.com.thianolima.news.assinante.templates;

import br.com.thianolima.news.assinante.entities.AssinanteEntity;
import lombok.Getter;

public class AssinanteEntityTemplate extends BaseTemplate{

    @Getter
    private static final AssinanteEntityTemplate instance = new AssinanteEntityTemplate();

    public AssinanteEntity getObjectValid(){
        return AssinanteEntity.builder()
                .id(faker.number().randomNumber())
                .nome(faker.superhero().name())
                .email(faker.bothify("????##@gmail.com"))
                .build();
    }
}
