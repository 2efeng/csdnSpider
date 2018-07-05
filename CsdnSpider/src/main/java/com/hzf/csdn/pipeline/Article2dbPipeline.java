package com.hzf.csdn.pipeline;

import com.hzf.csdn.App;
import com.hzf.csdn.bean.Article;
import com.hzf.csdn.dao.ArticleRepository;
import com.hzf.csdn.utils.ConfigUtils;
import com.hzf.csdn.utils.ThrowUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Html;

import java.util.Date;
import java.util.regex.Pattern;

public class Article2dbPipeline implements Pipeline {

    private static Logger logger = Logger.getLogger(Article2dbPipeline.class.getName());
    private ArticleRepository repository = App.getArticleRepository();

    private String authorNickname;

    public Article2dbPipeline(String authorNickname) {
        this.authorNickname = authorNickname;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String html = resultItems.get("htmlContent");
        Elements articles = Html.create(html).getDocument().getElementsByClass("article-item-box csdn-tracking-statistics");
        for (Element article : articles) {
            if (repository.exists(article.attr("data-articleid"))) {
                logger.info("article already existed! id: " + article.attr("data-articleid"));
                continue;
            }
            Article model = new Article();
            try {
                model.setId(article.attr("data-articleid"));
                model.setArticleUrl(article.getElementsByTag("a").attr("href"));
                Elements spans = article.getElementsByTag("span");
                model.setAuthorNickname(authorNickname);
                model.setIsOriginal(spans.get(0).text().trim());
                model.setDate(spans.get(1).text());
                Pattern p = Pattern.compile(ConfigUtils.getProperty("numberRegEx"));
                model.setReadCount(p.matcher(spans.get(2).text()).replaceAll("").trim());
                model.setDiscussCount(p.matcher(spans.get(3).text()).replaceAll("").trim());
                model.setTitle(article.getElementsByTag("a").get(0).childNode(2).toString().trim());
                model.setCreateDate(new Date());
                model = repository.save(model);
                logger.error("save article succ! authorNickname:" + model.getAuthorNickname() + " id:" + model.getId());
            } catch (Exception e) {
                logger.error(ThrowUtils.printStackTraceToString(e));
            }
        }

    }
}
