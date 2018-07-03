package com.hzf.csdn.service;

import com.hzf.csdn.message.AuthorUrlMsg;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Spider;

public class ArticleThread implements Runnable {

    private static Logger logger = Logger.getLogger(ArticleThread.class.getName());

    @Override
    public void run() {
        while (true) {
            String url = AuthorUrlMsg.getAuthorUrl();
            if (url != null) {
                Spider spider = Spider.create(new ArticleIndexPageService())
                        .addUrl(url);
                spider.isExitWhenComplete();
                spider.run();
            } else {
                logger.error("author url is null!");
            }
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

}
