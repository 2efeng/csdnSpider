package com.hzf.csdn.dao;

import com.hzf.csdn.bean.Author;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer>, JpaSpecificationExecutor<Author> {

    @Query(value = "from Author user where user.bloggerUrl = ?1")
    List<Author> findAuthorsByBloggerUrl(String url);


}

