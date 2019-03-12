package cn.everythinggrows.search.dao;


import cn.everythinggrows.search.entity.Entity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


@Component
public interface ArticleDao extends ElasticsearchRepository<Entity,String> {

    public Entity getEntiryById(long id);
}
