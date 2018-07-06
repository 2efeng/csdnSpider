package com.hzf.csdn;

import com.hzf.csdn.service.ArticleThread;
import com.hzf.csdn.service.AuthorIndexPageService;
import com.hzf.csdn.utils.ConfigUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Spider;

public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        //加载日志和配置文件
        ConfigUtils.load();

        start();
    }

    private static void start() {
        logger.info("=================================== begin run ===================================");
        ArticleThread thread = new ArticleThread();
        thread.start();
        Spider spider = Spider.create(new AuthorIndexPageService()).addUrl(ConfigUtils.getProperty("csdn.url"));
        spider.isExitWhenComplete();
        spider.runAsync();
    }
}
