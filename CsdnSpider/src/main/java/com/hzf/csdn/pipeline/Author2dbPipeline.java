package com.hzf.csdn.pipeline;

import com.hzf.csdn.App;
import com.hzf.csdn.bean.Author;
import com.hzf.csdn.dao.AuthorRepository;
import com.hzf.csdn.message.AuthorUrlMsg;
import com.hzf.csdn.utils.ThrowUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Html;

import java.util.Date;

public class Author2dbPipeline implements Pipeline {

    private static Logger logger = Logger.getLogger(Author2dbPipeline.class.getName());
    private AuthorRepository repository = App.getAuthorRepository();

    @Override
    public void process(ResultItems resultItems, Task task) {
        String content = resultItems.get("htmlContent");
        Elements authors = Html.create(content).getDocument().getElementsByClass("experts_list");
        for (Element info : authors) {
            Element dtTag = info.child(0);
            if (repository.findAuthorsByBloggerUrl(dtTag.child(0).attr("href")).size() > 0) {
                logger.info("author already existed! blogger url : " + dtTag.child(0).attr("href"));
                continue;
            }
            Element ddTag = info.child(1);
            Author author = new Author();
            author.setBloggerUrl(dtTag.child(0).attr("href"));
            author.setAvatarUrl(dtTag.child(0).child(0).attr("src"));
            author.setName(ddTag.getElementsByClass("expert_name").first().text());
            Element address_job = ddTag.getElementsByClass("address").first();
            author.setAddress(address_job.getElementsByTag("em").first() == null ? "" : address_job.getElementsByTag("em").first().text());
            author.setJob(address_job.getElementsByTag("span").first() == null ? "" : address_job.getElementsByTag("span").first().text());
            author.setBlogCount(Integer.parseInt(ddTag.getElementsByClass("count_l fl").first().child(0).text()));
            author.setReadCount(Integer.parseInt(ddTag.getElementsByClass("count_l fr").first().child(0).text()));
            author.setNickname(ddTag.getElementsByClass("attention").first().child(0).attr("value"));
            author.setCreateDate(new Date());
            try {
                author = repository.save(author);
                logger.info("save author succ! nickname:" + author.getNickname() + " id:" + author.getId());
                AuthorUrlMsg.setAuthorUrl(author.getBloggerUrl());
            } catch (Exception ex) {
                logger.error(ThrowUtils.printStackTraceToString(ex));
            }
        }
    }
}
