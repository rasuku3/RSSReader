package rss.reader.domain;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rometools.rome.feed.synd.SyndFeed;

import rss.reader.Infrastructure.RssReader;


@Service
public class RssReaderServiceImpl implements RssReaderService  {

	RssReader rssReader;
	RssReaderConverter rssReaderConverter;

	RssReaderServiceImpl(RssReader rssReader, RssReaderConverter rssReaderConverter){
		this.rssReader = rssReader;
		this.rssReaderConverter = rssReaderConverter;
	}


	@Override
	public List<SyndFeed> getFeeds() {

		List<SyndFeed> rssList = new ArrayList<>();
		List<String> urlList = rssReader.getRssUrlList();


		for (String url : urlList) {

			// RSSフィードの元となるXMLを取得し、RSSフィードとして出力する前に、
			// 文字列の削除や置換を実施する。
			StringReader preProcessedXml = rssReaderConverter.replaceStrInRSS(url);

			SyndFeed feed = rssReader.getRss(preProcessedXml);
			rssList.add(feed);

		}

		return rssList;

	}

}
