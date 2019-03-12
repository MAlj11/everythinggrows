package cn.everythinggrows.search.controller;


import cn.everythinggrows.search.dao.ArticleDao;
import cn.everythinggrows.search.entity.Entity;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
@RequestMapping("/search")
public class EntityController {
    private Logger logger = LoggerFactory.getLogger(EntityController.class);

    @Autowired
    private ArticleDao articleDao;

    /**
     * 添加
     * @return
     */
    @RequestMapping("/add")
    public String add(@RequestParam(value = "id") long id,
                      @RequestParam(value = "title") String title,
                      @RequestParam(value = "auther") String auther) {
        Entity entity = new Entity();
        entity.setId(id);
        entity.setTitle(title);
        entity.setAuther(auther);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH ) + 1;
        //获取到0-11，与我们正常的月份差1
        int day = cal.get(Calendar.HOUR_OF_DAY);
        String createAtStr = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
        entity.setCreateAt(createAtStr);
        logger.info("{} data is add",id);
        return "success";
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "id") long id) {
        Entity entity = articleDao.getEntiryById(id);
        articleDao.delete(entity);
        logger.info("{} data is delete",id);
        return "success";
    }

    /**
     * 局部更新
     * @return
     */
    @RequestMapping("/partUpdate")
    public String update(@RequestParam(value = "id") long id,
                         @RequestParam(value = "title",defaultValue = "") String title,
                         @RequestParam(value = "auther",defaultValue = "") String auther) {
        Entity entity = articleDao.getEntiryById(id);
        if(!title.equals("")){
            entity.setTitle(title);
        }
        if(!auther.equals("")){
            entity.setAuther(auther);
        }
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH ) + 1;
        //获取到0-11，与我们正常的月份差1
        int day = cal.get(Calendar.HOUR_OF_DAY);
        String createAtStr = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
        entity.setCreateAt(createAtStr);
        articleDao.save(entity);
        logger.info("{} data is update",id);
        return "success";
    }
    /**
     * 查询
     * @return
     */
    @RequestMapping("/query")
    public Entity query(@RequestParam(value = "id") long id) {
        Entity accountInfo = articleDao.getEntiryById(id);
        System.err.println(new Gson().toJson(accountInfo));
        return accountInfo;
    }

}
