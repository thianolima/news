package br.com.thianolima.news.noticia.templates;

import br.com.thianolima.news.noticia.entities.NoticiaEntity;
import lombok.Getter;

public class NoticiaEntityTemplate extends BaseTemplate{

    @Getter
    private static final NoticiaEntityTemplate instance = new NoticiaEntityTemplate();

    public NoticiaEntity getObjectValid(){
        return NoticiaEntity.builder()
                .titulo(faker.company().name())
                .texto(faker.lorem().characters())
                .build();
    }
}
