package rss.reader.Infrastructure;

import java.io.Reader;
import java.util.List;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;

/**
 * RSSフィードのURL、RSSフィードの取得ロジックを担当するインターフェース
 *
 */
public interface RssReader {
	/**
	 *
	 * @param reader
	 * @return SyndFeedオブジェクト RSSフィードの内容(ex title, description)
	 * @throws IllegalArgumentException
	 * @throws FeedException
	 */
	SyndFeed getRss(Reader reader);

	/**
	 *
	 * @return RSSフィードを公開しているサイトのURLリスト
	 */
	List<String> getRssUrlList();

}
