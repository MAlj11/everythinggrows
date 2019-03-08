package cn.everythinggrows.portal.controller.account;


import cn.everythinggrows.model.Banner;
import cn.everythinggrows.model.EgTypeArticle;
import cn.everythinggrows.model.egArticle;
import cn.everythinggrows.portal.Utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.util.List;
import java.util.Map;

/**
 * @author MA
 */
@Controller
public class indexController {
  private Logger logger = LoggerFactory.getLogger(indexController.class);

    @Value("${BASE_URL_BLOG}")
    String BASE_URL_BLOG;

    @RequestMapping("/sindex.html")
    public String getIndex(){
        logger.info("__________________________________________进入sindex");
        return "sindex";
    }

    @RequestMapping("/index.html")
    public String getInsexx(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        logger.info("--------------------------------------------------creating");
        String url = BASE_URL_BLOG + "/index/article";
        String httpRet = HttpClientUtil.doGet(url);
        JSONObject json = JSON.parseObject(httpRet);
        Map<String,Object> articleMap = JSONObject.toJavaObject(json, Map.class);
        List<egArticle> articleList = null;
        if(articleMap.get("errorCode").equals("0")){
         articleList = (List<egArticle>) articleMap.get("articleList");
        }
        session.setAttribute("articleList",articleList);

        String url2 = BASE_URL_BLOG + "/index/recommend";
        String httpRet2 = HttpClientUtil.doGet(url2);
        JSONObject json2 = JSON.parseObject(httpRet2);
        Map<String,Object> articleMap2 = JSONObject.toJavaObject(json2, Map.class);
        List<egArticle> recommendList = null;
        if(articleMap.get("errorCode").equals("0")){
            recommendList = (List<egArticle>) articleMap2.get("recommendList");
        }
        session.setAttribute("recommendList",recommendList);

        String url3 = BASE_URL_BLOG + "/index/banner";
        String httpRet3 = HttpClientUtil.doGet(url3);
        JSONObject json3 = JSON.parseObject(httpRet3);
        Map<String,Object> bannerMap = JSONObject.toJavaObject(json3, Map.class);
        List<Banner> bannerList = null;
        if(articleMap.get("errorCode").equals("0")){
            bannerList = (List<Banner>) bannerMap.get("bannerList");
        }
        session.setAttribute("bannerList",bannerList);
        return "lw-index";
    }


    @RequestMapping(value = "/registerPage.html")
    public String getRegisterPage(){
        return "lw-re";
    }

    @RequestMapping(value = "/loginPage.html")
    public String getLoginPage(){
        return "lw-log";
    }

    @RequestMapping(value = "/type/Photography.html")
    public String getPhotography(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        String typeUrl = BASE_URL_BLOG + "/type/1";
        String typeRet = HttpClientUtil.doGet(typeUrl);
        JSONObject json = JSON.parseObject(typeRet);
        Map<String,Object> typeArticleMap = JSONObject.toJavaObject(json, Map.class);
        List<EgTypeArticle> PhotographyList = null;
        if(typeArticleMap.get("errorCode").equals("0")){
            PhotographyList = (List<EgTypeArticle>)typeArticleMap.get("articleWithTypeList");
        }
        session.setAttribute("PhotographyList",PhotographyList);
        return "lw-Photography";
    }

    @RequestMapping(value = "/type/Internet.html")
    public String getInternet(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        String typeUrl = BASE_URL_BLOG + "/type/2";
        String typeRet = HttpClientUtil.doGet(typeUrl);
        JSONObject json = JSON.parseObject(typeRet);
        Map<String,Object> typeArticleMap = JSONObject.toJavaObject(json, Map.class);
        List<EgTypeArticle> InternetList = null;
        if(typeArticleMap.get("errorCode").equals("0")){
            InternetList = (List<EgTypeArticle>)typeArticleMap.get("articleWithTypeList");
        }
        session.setAttribute("InternetList",InternetList);
        return "lw-Internet";
    }

    @RequestMapping(value = "/type/media.html")
    public String getMedia(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        String typeUrl = BASE_URL_BLOG + "/type/3";
        String typeRet = HttpClientUtil.doGet(typeUrl);
        JSONObject json = JSON.parseObject(typeRet);
        Map<String,Object> typeArticleMap = JSONObject.toJavaObject(json, Map.class);
        List<EgTypeArticle> mediaList = null;
        if(typeArticleMap.get("errorCode").equals("0")){
            mediaList = (List<EgTypeArticle>)typeArticleMap.get("articleWithTypeList");
        }
        session.setAttribute("mediaList",mediaList);
        return "lw-media";
    }

    @RequestMapping(value = "/type/feeling")
    public String getFeeling(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        String typeUrl = BASE_URL_BLOG + "/type/4";
        String typeRet = HttpClientUtil.doGet(typeUrl);
        JSONObject json = JSON.parseObject(typeRet);
        Map<String,Object> typeArticleMap = JSONObject.toJavaObject(json, Map.class);
        List<EgTypeArticle> feelingList = null;
        if(typeArticleMap.get("errorCode").equals("0")){
            feelingList = (List<EgTypeArticle>)typeArticleMap.get("articleWithTypeList");
        }
        session.setAttribute("feelingList",feelingList);
        return "lw-feeling";

    }

}
