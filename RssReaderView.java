package rss.reader.application;

import java.util.List;

import com.rometools.rome.feed.synd.SyndFeed;

/**
 * RSSフィードの出力処理を担当する
 *
 */
public interface RssReaderView {

	/**
	 * 取得したRSSフィードを出力する処理
	 * @param feeds
	 */
	void outputRssList(List<SyndFeed> feeds);

	/**
	 * 個々のRSSフィードを出力する処理
	 * 標準出力やファイル出力を実装する。
	 */
	void outputRss(String str);

	/**
	 * 取得したRSSフィードを出力するにあたりレイアウトを整える。
	 * @param feed
	 * @return レイアウト調整後のFeed文字列
	 */
	String getOutputRssStr(SyndFeed feed);
}
