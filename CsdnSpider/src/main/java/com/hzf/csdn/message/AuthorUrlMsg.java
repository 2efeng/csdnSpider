package com.hzf.csdn.message;

import org.apache.log4j.Logger;

import java.util.LinkedList;

public class AuthorUrlMsg {

    private static Logger logger = Logger.getLogger(AuthorUrlMsg.class.getName());
    private static final LinkedList<String> authorUrls = new LinkedList<>();

    public static String getAuthorUrl() {
        synchronized (authorUrls) {
            while (authorUrls.isEmpty() || authorUrls.size() == 0) {
                try {
                    authorUrls.wait();
                } catch (Exception e) {
                    logger.error(e);
                    return null;
                }
            }
            return authorUrls.removeFirst();
        }
    }

    public static void setAuthorUrl(String url) {
        synchronized (authorUrls) {
            authorUrls.add(url);
            authorUrls.notify();
        }
    }

}
