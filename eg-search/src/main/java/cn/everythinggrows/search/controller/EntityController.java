package cn.everythinggrows.search.controller;


import cn.everythinggrows.search.dao.ArticleDao;
import cn.everythinggrows.search.entity.Entity;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@RestController
@RequestMapping("/search")
@EnableSwagger2
public class EntityController {
    private Logger logger = LoggerFactory.getLogger(EntityController.class);

    @Autowired
    private ArticleDao articleDao;


    /**
     * 1、查  id
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Entity getEntityById(@PathVariable String id) {
        Optional<Entity> entityOptional =  articleDao.findById(id);
        if(entityOptional.isPresent()){
            return entityOptional.get();
        }
        return null;
    }

    /**
     * 2、查  ++:全文检索（根据整个实体的所有属性，可能结果为0个）
     * @param q
     * @return
     */
    @GetMapping("/select/{q}")
    public List<Entity> testSearch(@PathVariable String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<Entity> searchResult = articleDao.search(builder);
        Iterator<Entity> iterator = searchResult.iterator();
        List<Entity> list = new ArrayList<Entity>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }


 //此方法暂时不用

//    /**
//     * 3、查   +++：分页、分数、分域（结果一个也不少）
//     * @param page
//     * @param size
//     * @param q
//     * @return
//     * @return
//     */
//    @GetMapping("/{page}/{size}/{q}")
//    public List<Entity> searchCity(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String q) {
//
//        // 分页参数
//        Pageable pageable = new PageRequest(page, size);
//
//        // 分数，并自动按分排序
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", q)),
//                        ScoreFunctionBuilders.weightFactorFunction(1000))// 权重：name 1000分
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("message", q)),
//                        ScoreFunctionBuilders.weightFactorFunction(100));
//        // 权重：message 100分
//
//        // 分数、分页
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
//                .withQuery(functionScoreQueryBuilder).build();
//
//        Page<Entity> searchPageResults = articleDao.search(searchQuery);
//        return searchPageResults.getContent();
//
//    }


    /**
     * 4、增
     * @param entity
     * @return
     */
    @PostMapping("/insert")
    public Entity insertEntity(Entity entity) {
        articleDao.save(entity);
        return entity;
    }

    /**
     * 5、删 id
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Entity insertEntity(@PathVariable String id) {
        Optional<Entity> entityOptional = articleDao.findById(id);
        articleDao.deleteById(id);
        return entityOptional.get();
    }

    /**
     * 6、改
     * @param entity
     * @return
     */
    @PutMapping("/update")
    public Entity updateEntity(Entity entity) {
        articleDao.save(entity);
        return entity;
    }


}
