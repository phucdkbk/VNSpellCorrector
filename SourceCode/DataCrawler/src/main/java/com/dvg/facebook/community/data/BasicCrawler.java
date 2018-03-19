/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvg.facebook.community.data;

/**
 *
 * @author Administrator
 */
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.http.Header;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @author Yasser Ganjisaffar
 */
public class BasicCrawler extends WebCrawler {

	private static final Logger LOG = Logger.getLogger(BasicCrawler.class);

	private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(css|js|bmp|gif|jpg|png|mp3|mp4)$");

	/**
	 * You should implement this function to specify whether the given url should be crawled or not (based on your crawling logic).
	 *
	 * @param referringPage
	 * @param url
	 * @return
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		// Ignore the url if it has an extension that matches our defined set of image extensions.
		if (IMAGE_EXTENSIONS.matcher(href).matches()) {
			return false;
		}

		// Only accept the url if it is in the "www.ics.uci.edu" domain and protocol is "http".
		boolean accept = href.startsWith("http://www.tribunnews.com");
		if (accept) {
			Pattern pattern = Pattern.compile("\\d{4}/\\d{2}/\\d{2}");
			Matcher matcher = pattern.matcher(href);
			if (!matcher.find()) {
				accept = false;
			}
		}
		return accept;

	}

	/**
	 * This function is called when a page is fetched and ready to be processed by your program.
	 *
	 * @param page
	 */
	@Override
	public void visit(Page page) {
		int docid = page.getWebURL().getDocid();
		String url = page.getWebURL().getURL();
		String domain = page.getWebURL().getDomain();
		String path = page.getWebURL().getPath();
		String subDomain = page.getWebURL().getSubDomain();
		String parentUrl = page.getWebURL().getParentUrl();
		String anchor = page.getWebURL().getAnchor();

		logger.debug("Docid: {}", docid);
		logger.info("URL: {}", url);
		logger.debug("Domain: '{}'", domain);
		logger.debug("Sub-domain: '{}'", subDomain);
		logger.debug("Path: '{}'", path);
		logger.debug("Parent page: {}", parentUrl);
		logger.debug("Anchor text: {}", anchor);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			Set<WebURL> links = htmlParseData.getOutgoingUrls();

			News news = new News();
			news.website = BasicCrawlController.WEBSITE;
			news.countryId = BasicCrawlController.COUNTRY_ID;
			Document document = Jsoup.parse(html);
			news.title = document.getElementById("arttitle").text();
			Element contentElement = document.getElementById("article_con");
			contentElement.select("br").append("\\n");
			contentElement.select("p").prepend("\\n\\n");
			news.content = contentElement.text().replaceAll("\\\\n", "\n");
			news.publishInfo = document.getElementsByTag("time").get(0).text();

			Pattern pattern = Pattern.compile("\\d{4}/\\d{2}/\\d{2}");
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				news.year = Integer.parseInt(matcher.group(0).substring(0, 4));
				news.month = Integer.parseInt(matcher.group(0).substring(5, 7));
				news.day = Integer.parseInt(matcher.group(0).substring(8, 10));
			}
			news.url = url;

			saveNews(news);

			logger.debug("Text length: {}", text.length());
			logger.debug("Html length: {}", html.length());
			logger.debug("Number of outgoing links: {}", links.size());
		}

		Header[] responseHeaders = page.getFetchResponseHeaders();
		if (responseHeaders != null) {
			logger.debug("Response headers:");
			for (Header header : responseHeaders) {
				logger.debug("\t{}: {}", header.getName(), header.getValue());
			}
		}

		logger.debug("=============");
	}

	private void saveNews(News news) {
		Connection conn = null;
		try {
			conn = DataSource.getInstance().getFBCommunityStoreConnection();
			PreparedStatement insertNews = conn.prepareStatement(
					"insert into news(url,title,content,publish_info,year,month,day,country_id,website,update_date)"
					+ " values(?,?,?,?,?,?,?,?,?,?)");
			insertNews.setString(1, news.url);
			insertNews.setString(2, news.title);
			insertNews.setString(3, news.content);
			insertNews.setString(4, news.publishInfo);
			insertNews.setInt(5, news.year);
			insertNews.setInt(6, news.month);
			insertNews.setInt(7, news.day);
			insertNews.setInt(8, news.countryId);
			insertNews.setString(9, news.website);
			insertNews.setInt(10, getCurrentDateInt());
			insertNews.executeUpdate();
		} catch (Exception e) {
			LOG.error(e, e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				LOG.error(e, e);
			}
		}
	}

	private static int getCurrentDateInt() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		int date = Integer.valueOf(dateFormat.format(new Date()));
		return date;
	}
}
