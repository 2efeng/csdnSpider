package com.hzf.csdn.dao;

import com.hzf.csdn.bean.Article;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, String>, JpaSpecificationExecutor<Article> {


}

