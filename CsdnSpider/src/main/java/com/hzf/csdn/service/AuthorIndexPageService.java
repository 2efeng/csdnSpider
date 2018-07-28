package com.hzf.csdn.service;

import com.hzf.csdn.pipeline.Author2dbPipeline;
import com.hzf.csdn.utils.ConfigUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.processor.PageProcessor;

public class AuthorIndexPageService implements PageProcessor {

    private static Logger logger = Logger.getLogger(AuthorIndexPageService.class.getName());

    @Override
    public void process(Page page) {
        if (page == null) {

            return;
        }
        String baseUrl = ConfigUtils.getProperty("csdn.base");
        String lastUrl = page.getHtml().getDocument().getElementsByClass("page_nav").first().getElementsByTag("a").last().attr("href");
        int count = Integer.parseInt(lastUrl.split("=")[2]);
        Spider spider = Spider.create(new AuthorPageService())
                .addPipeline(new Author2dbPipeline());
        for (int i = 1; i <= count; i++) {
            spider.addUrl(baseUrl + i);
        }
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            logger.error(e);
        }
        logger.info("================ UrlCount:" + count + " ================ begin ================");
        spider.thread(2);
        spider.isExitWhenComplete();
        spider.run();
    }

    @Override
    public Site getSite() {
        return Site.me()
                .setDomain(ConfigUtils.getProperty("csdn.root"))
                .setSleepTime(200)
                .setCycleRetryTimes(3)
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }
}
