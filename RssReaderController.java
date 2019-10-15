package rss.reader.application;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rometools.rome.feed.synd.SyndFeed;

import rss.reader.domain.RssReaderService;

/**
 * RSSフィード取得の全処理を呼び出す起点のコントローラー
 *
 */
@RestController("/")
class RssReaderController {

	RssReaderService rssReaderService;
	RssReaderView rssReaderView;

	RssReaderController(RssReaderService rssReaderService, RssReaderView rssReaderView) {
		this.rssReaderService = rssReaderService;
		this.rssReaderView = rssReaderView;
	}

	@GetMapping
	public void startRssRead() {
		List<SyndFeed> feeds = rssReaderService.getFeeds();
		rssReaderView.outputRssList(feeds);

	}
}