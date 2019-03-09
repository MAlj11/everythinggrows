package cn.everythinggrows.blog.service;


import cn.everythinggrows.blog.Utils.ArticleUtils;
import cn.everythinggrows.blog.dao.CommentDao;
import cn.everythinggrows.blog.model.Comment;
import cn.everythinggrows.user.model.egUser;
import cn.everythinggrows.user.service.IUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IUserAccount iUserAccount;


    public List<Comment> getCommentWithAid(long aid){
        List<Comment> comments = commentDao.getCommentList(aid);
        return comments;
    }

    public int insertComment(long aid, long uid,String content){
        egUser user = iUserAccount.getUser(uid);
        Comment comment = new Comment();
        long cid = ArticleUtils.getCommentId();
        comment.setCid(cid);
        comment.setAid(aid);
        comment.setContent(content);
        comment.setUid(uid);
        comment.setPortrait(user.getPortrait());
        comment.setUsername(user.getUsername());
        int i = commentDao.insertComment(comment);
        return i;
    }

}
