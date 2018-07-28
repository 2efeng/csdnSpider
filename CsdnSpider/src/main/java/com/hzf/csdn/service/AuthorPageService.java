package com.hzf.csdn.service;

import com.hzf.csdn.utils.ConfigUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class AuthorPageService implements PageProcessor {

    private static Logger logger = Logger.getLogger(AuthorIndexPageService.class.getName());

    @Override
    public void process(Page page) {
        logger.info("====== begin get authors data ===== url:" + page.getUrl().toString() + " =====");
        page.putField("htmlContent", page.getRawText());
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
