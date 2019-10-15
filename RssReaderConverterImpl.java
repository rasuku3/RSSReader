package rss.reader.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class RssReaderConverterImpl implements RssReaderConverter {

	private static final String REPLACE_STR = "DOCTYPE";
	private static final String TEMP_STR_FOR_REPLACE = "●●●●●●●";

	@Override
	public StringReader replaceStrInRSS(String urlStr) {

		String xml = getStringFromRSS(urlStr);

		/*
		 * XML内容から変換したい文字列があれば記載
		 * 以下の例ではNewsをブランクにしている。
		 */
		//xml = replaceStrToTempStr(xml);
		//xml = xml.replace("News", "");

		return new StringReader(xml);
	}

	@Override
	public String replaceStrToTempStr(String target) {
		return target.replace(REPLACE_STR, TEMP_STR_FOR_REPLACE);
	}

	@Override
	public String replaceTempStrToStr(String target) {
		return target.replace(TEMP_STR_FOR_REPLACE, REPLACE_STR);
	}

	/**
	 *
	 * @param urlStr RSS配信サイト用URL
	 * @return RSS配信サイトに接続し、内容を文字列として取得する。
	 */
	public String getStringFromRSS(String urlStr) {

		URL url = null;
		HttpURLConnection urlconn = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			System.err.print("Feed取得前 変換処理エラー：URL生成エラー");
			e.printStackTrace();
		}

		try {
			urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setRequestProperty("Accept-Charset", "UTF-8");
			urlconn.connect();
		} catch (IOException e) {
			System.err.print("Feed取得前 変換処理エラー：URL先接続エラー");
			e.printStackTrace();
		}

		// RSS内の文字列を置換するために、一度、文字列化
		String xml = "";
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "UTF-8"))) {

			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				xml += line;
			}
			reader.close();

		} catch (IOException e) {
			urlconn.disconnect();
			System.err.print("Feed取得前 変換処理でエラー ");
			e.printStackTrace();

		}

		urlconn.disconnect();

		return xml;
	}

}
