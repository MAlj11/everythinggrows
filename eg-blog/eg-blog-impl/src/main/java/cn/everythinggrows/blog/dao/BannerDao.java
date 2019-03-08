package cn.everythinggrows.blog.dao;

import cn.everythinggrows.blog.model.Banner;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BannerDao {

    @Autowired
    private SqlSessionTemplate blogSqlSession;

    public List<Banner> getBanner(){
      List<Banner> bannerList = blogSqlSession.selectList("BannerDao.selectBanner");
      return bannerList;
    }
}
