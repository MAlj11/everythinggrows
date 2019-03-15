package cn.everythinggrows.cn.eg.search.contorller;


import cn.everythinggrows.cn.eg.search.entity.Article;
import cn.everythinggrows.cn.eg.search.service.JestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
public class JestApiController {
    @Autowired
    private JestService jestService;

    @RequestMapping(value = "/search/jest/save")
    public String saveArticle(@RequestParam(value = "id") long id,
                              @RequestParam(value = "title") String title,
                              @RequestParam(value = "auther") String auther){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH ) + 1;
        //获取到0-11，与我们正常的月份差1
        int day = cal.get(Calendar.HOUR_OF_DAY);
        String createAtStr = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
         Article article = new Article(id,title,auther,createAtStr);
         String ret = jestService.saveEntity(article);
        return ret;
    }

    @RequestMapping(value = "/search/jest/get")
    public List<Article> getArticleList(@RequestParam(value = "content") String content){
        List<Article> articleList = jestService.searchEntity(content);
        return articleList;
    }


    @RequestMapping(value = "/search/jest/all/fields")
    public List<Article> getAllFields(@RequestParam(value = "query") String query){
        List<Article> ret = null;
       try{
           ret = jestService.search(query);
       }catch (Exception e){
           e.printStackTrace();
       }
       return ret;
    }
}
