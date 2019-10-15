package rss.reader.domain;

import java.util.List;

import com.rometools.rome.feed.synd.SyndFeed;

/**
 *
 * RSSリーダーのドメインロジックインターフェース
 * RSSフィードの取得処理を担当するRSSReaderを呼び出す。
 * RSSフィードの変換処理を担当するRssReaderConverterを呼び出す。
 *
 */
public interface RssReaderService {

	/**
	 *
	 * @return 取得したRSSフィードのリスト
	 */
	List<SyndFeed> getFeeds();
}
