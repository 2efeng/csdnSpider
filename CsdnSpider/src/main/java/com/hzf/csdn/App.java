package com.hzf.csdn;

import com.hzf.csdn.dao.ArticleRepository;
import com.hzf.csdn.dao.AuthorRepository;
import com.hzf.csdn.service.ArticleThread;
import com.hzf.csdn.service.AuthorIndexPageService;
import com.hzf.csdn.utils.ConfigUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import us.codecraft.webmagic.Spider;

import java.io.File;

public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());
    private static AuthorRepository authorRepository = null;
    private static ArticleRepository articleRepository = null;

    public static void main(String[] args) {
        String ctxFile = System.getProperty("user.dir")
                + File.separator + "config"
                + File.separator + "applicationContext.xml";
        System.out.println(ctxFile);
        ApplicationContext context = new FileSystemXmlApplicationContext(ctxFile);
        authorRepository = context.getBean(AuthorRepository.class);
        articleRepository = context.getBean(ArticleRepository.class);
        start();
        loadLog4j();
    }

    public static AuthorRepository getAuthorRepository() {
        return authorRepository;
    }

    public static ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    private static void start() {
        logger.info("=================================== begin run ===================================");
        ArticleThread thread = new ArticleThread();
        thread.start();
        Spider spider = Spider.create(new AuthorIndexPageService()).addUrl(ConfigUtils.getProperty("csdn.url"));
        spider.isExitWhenComplete();
        spider.runAsync();
    }

    private static void loadLog4j() {
        String logFilePath = System.getProperty("user.dir")
                + File.separator + "config"
                + File.separator + "log4j.properties";
        System.out.println(logFilePath);
        PropertyConfigurator.configure(logFilePath);
    }
}
