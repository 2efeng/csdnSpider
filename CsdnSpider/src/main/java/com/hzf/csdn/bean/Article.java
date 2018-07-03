package com.hzf.csdn.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 2545003670604554630L;

    @Id
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "authorNickname")
    private String authorNickname;

    @Column(name = "url")
    private String url;

    @Column(name = "isOriginal")
    private boolean isOriginal;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private String date;

    @Column(name = "readCount")
    private String readCount;

    @Column(name = "discussCount")
    private String discussCount;

    @Column(name = "CreateDate")
    private Date CreateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isOriginal() {
        return isOriginal;
    }

    public void setOriginal(boolean original) {
        isOriginal = original;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getDiscussCount() {
        return discussCount;
    }

    public void setDiscussCount(String discussCount) {
        this.discussCount = discussCount;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", authorNickname='" + authorNickname + '\'' +
                ", url='" + url + '\'' +
                ", isOriginal=" + isOriginal +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", readCount='" + readCount + '\'' +
                ", discussCount='" + discussCount + '\'' +
                ", CreateDate=" + CreateDate +
                '}';
    }
}
