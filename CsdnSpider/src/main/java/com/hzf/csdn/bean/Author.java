package com.hzf.csdn.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
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

    @Column(name = "createDate")
    private Date createDate;



}
