package br.com.thianolima.news.noticia.templates;

import br.com.thianolima.news.noticia.vos.NoticiaVO;
import lombok.Getter;

public class NoticiaVOTemplate extends BaseTemplate{

    @Getter
    private static final NoticiaVOTemplate instance = new NoticiaVOTemplate();

    public NoticiaVO getObjectValid(){
        return NoticiaVO.builder()
                .titulo(faker.company().name())
                .texto(faker.lorem().characters())
                .build();
    }
}
