package com.hzf.csdn.bean;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 2545003670604554630L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "authorNickname")
    private String authorNickname;

    @Column(name = "articleUrl")
    private String articleUrl;

    @Column(name = "isOriginal")
    private String isOriginal;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private String date;

    @Column(name = "readCount")
    private String readCount;

    @Column(name = "discussCount")
    private String discussCount;

    @Column(name = "createDate")
    private Date createDate;


}
