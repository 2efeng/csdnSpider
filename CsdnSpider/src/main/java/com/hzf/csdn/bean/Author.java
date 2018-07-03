package com.hzf.csdn.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_author")
public class Author implements Serializable {

    private static final long serialVersionUID = 4909288265449530652L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "bloggerUrl")
    private String bloggerUrl;

    @Column(name = "avatarUrl")
    private String avatarUrl;

    @Column(name = "address")
    private String address;

    @Column(name = "job")
    private String job;

    @Column(name = "readCount")
    private int readCount;

    @Column(name = "blogCount")
    private int blogCount;

    @Column(name = "CreateDate")
    private Date CreateDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBloggerUrl() {
        return bloggerUrl;
    }

    public void setBloggerUrl(String bloggerUrl) {
        this.bloggerUrl = bloggerUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", bloggerUrl='" + bloggerUrl + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", address='" + address + '\'' +
                ", job='" + job + '\'' +
                ", readCount=" + readCount +
                ", blogCount=" + blogCount +
                ", CreateDate=" + CreateDate +
                '}';
    }
}
