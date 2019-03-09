package cn.everythinggrows.blog.controller;

import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.blog.service.IndexService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Auther MA
 */

@RestController
public class indexController {
    @Autowired
    private IndexService indexService;


    /**首页文章
     */
    @RequestMapping(value = "/blog/index/article", method = RequestMethod.GET)
    public egResponse getArticleList(){
        egResponse ret = indexService.getArticleList();
        return ret;
    }


    /**首页右边推荐
     *
     * @return
     */
    @RequestMapping(value = "blog/index/recommend", method = RequestMethod.GET)
    public egResponse getRecommend(){
        egResponse ret = indexService.getRecommend();
        return ret;
    }

    /**
     * 首页banner
     */
    @RequestMapping(value = "blog/index/banner", method = RequestMethod.GET)
    public egResponse getBanner(){
      egResponse ret = indexService.getIndexBanner();
      return ret;
    }

    /**
     * 分类文章
     */
    @RequestMapping(value = "/blog/type/{typeId}")
    public egResponse getTypeArticle(@PathVariable(value = "0") String typeId){
      if(typeId.equals("0") || typeId == null){
          egResponse retIndex = indexService.getArticleList();
          return retIndex;
      }
     egResponse ret = indexService.getArticleWithType(Integer.parseInt(typeId));
      return ret;
    }

}
