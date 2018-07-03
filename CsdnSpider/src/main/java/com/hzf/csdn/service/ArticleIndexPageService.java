package com.hzf.csdn.service;

import com.hzf.csdn.App;
import com.hzf.csdn.bean.Author;
import com.hzf.csdn.dao.AuthorRepository;
import com.hzf.csdn.pipeline.Article2dbPipeline;
import com.hzf.csdn.utils.ConfigUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class ArticleIndexPageService implements PageProcessor {

    private AuthorRepository repository = App.getAuthorRepository();

    @Override
    public void process(Page page) {
        Elements allScript = page.getHtml().getDocument().getElementsByTag("script");
        for (Element script : allScript) {
            if (script.data().contains("baseUrl")) {
                int beginIdx, endIdx;
                beginIdx = script.data().indexOf("https");
                endIdx = script.data().indexOf("list");
                String baseUrl = script.data().substring(beginIdx, endIdx + 4);
                beginIdx = script.data().indexOf("pageSize") + 11;
                endIdx = script.data().indexOf(";", beginIdx);
                int pageSize = Integer.parseInt(script.data().substring(beginIdx, endIdx).trim());
                beginIdx = script.data().indexOf("listTotal") + 12;
                endIdx = script.data().indexOf(";", beginIdx);
                int listTotal = Integer.parseInt(script.data().substring(beginIdx, endIdx).trim());
                int pageList = listTotal % pageSize == 0 ? listTotal / pageSize : listTotal / pageSize + 1;
                String nickname = page.getUrl().toString().split("/")[3];
                Author author = repository.findAuthorsByNickname(nickname).get(0);
                if (author == null) break;
                Spider spider = Spider.create(new ArticlePageService())
                        .addPipeline(new Article2dbPipeline(author.getNickname()));
                for (int i = 1; i <= pageList; i++) {
                    spider.addUrl(baseUrl + "/" + i);
                }
                spider.isExitWhenComplete();
                spider.run();
                break;
            }
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain(ConfigUtils.getProperty("csdn.root"))
                .setSleepTime(1000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")
                .setUseGzip(true);
    }
}