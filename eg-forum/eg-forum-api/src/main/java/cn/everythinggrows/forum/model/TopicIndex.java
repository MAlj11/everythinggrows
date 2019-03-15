package cn.everythinggrows.forum.model;

import java.io.Serializable;

public class TopicIndex implements Serializable {
    private static final long serialVersionUID = -7056644395811669467L;
    private Long tid;
    private String content;
    private Integer createAt;

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Integer createAt) {
        this.createAt = createAt;
    }
}
