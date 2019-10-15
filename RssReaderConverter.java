package rss.reader.domain;

import java.io.IOException;
import java.io.StringReader;

/**
 * RSSフィードの変換ロジックを担当するインターフェース
 *
 */
public interface RssReaderConverter {

	/**
	 * 取得したRSSフィードに変換処理を施した結果を取得する。
	 * @param RSSフィード配信サイトURL
	 * @return 取得したRSSフィードに変換処理を施した後のRssReader型のオブジェクト
	 * @throws IOException
	 */
	StringReader replaceStrInRSS(String urlStr);

	/**
	 * RSSライブラリによっては、セキュリティ対策のために、DOCTYPEを読み込まない仕様となっている。
	 * 今回使用しているRomeライブラリもその仕様があるため、一時的にXML内の文字列を置換する。
	 * 後に、元の文字列に戻すよう再置換する。
	 * @param target 置換前の文字列
	 * @return 置換後の文字列
	 */
	String replaceStrToTempStr(String target);

	/**
	 * RSSライブラリによっては、セキュリティ対策のために、DOCTYPEを読み込まない仕様となっている。
	 * 今回使用しているRomeライブラリもその仕様があるため、一時的にXML内で置換した文字列を
	 * 元の文字列に戻すよう再置換する。
	 * @param target 置換前の文字列
	 * @return 置換後の文字列
	 */
	String replaceTempStrToStr(String target);

}
