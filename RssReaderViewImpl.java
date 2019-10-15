package rss.reader.application;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

import rss.reader.domain.RssReaderConverter;

@Component
public class RssReaderViewImpl implements RssReaderView {

	@Value("${path.rss.output}")
	private String logPath;

	RssReaderConverter rssReaderConverter;

	RssReaderViewImpl(RssReaderConverter rssReaderConverter) {
		this.rssReaderConverter = rssReaderConverter;
	}

	@Override
	public void outputRssList(List<SyndFeed> feeds) {

		StringBuffer sb = new StringBuffer();
		for (SyndFeed feed : feeds) {
			sb.append(getOutputRssStr(feed));

		}

		outputRss(sb.toString());

	}

	@Override
	public void outputRss(String str) {
		System.out.println(str);

		// FIXME ファイル出力を実施する場合には、以下のoutputRssToFile(str)を呼び出すこと
		//outputRssToFile(str);
	}

	@Override
	public String getOutputRssStr(SyndFeed feed) {
		StringBuilder sb = new StringBuilder();
		sb.append("■■Feed読み込み 開始：" + feed.getTitle() );
		sb.append(System.getProperty("line.separator"));
		for (SyndEntry entry : feed.getEntries()) {
			String tempEntryStr = entry.getDescription().getValue();
			String entryStrAfterReplace = rssReaderConverter.replaceTempStrToStr(tempEntryStr);
			entry.getDescription().setValue(entryStrAfterReplace);

			sb.append("▼Feed entry読み込み 開始：" + entry.getTitle() );
			sb.append(System.getProperty("line.separator"));
			sb.append(entry.getDescription().getValue());
			sb.append(System.getProperty("line.separator"));
			sb.append("▲Feed entry読み込み 終了");
			sb.append(System.getProperty("line.separator"));
		}
		sb.append("■■Feed読み込み 終了");
		sb.append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));

		return sb.toString();
	}

	/**
	 * 取得したRSSFeedを出力する。出力先：ファイル
	 * @param feed
	 */
	protected void outputRssToFile(String outputRssStr) {

		//String filePath = "log\\rss_output.txt";
		String filePath = logPath;
		try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath))) {

			bw.append(outputRssStr);
			bw.close();
		} catch (IOException e) {
			System.err.print("RSSフィードファイル出力エラー：" + filePath + "が存在することを確認してください。");
			e.printStackTrace();
		}

	}

}
