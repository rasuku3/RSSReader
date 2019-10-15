package rss.reader.Infrastructure;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;

@Service
public class RssReaderImpl implements RssReader {

	@Value("${path.rss.url}")
	private String urlPath;

	@Override
	public SyndFeed getRss(Reader reader) {
		SyndFeedInput feedInput = new SyndFeedInput(false, Locale.JAPAN);

		// DOCTYPE宣言のあるエントリーがRomeデフォルトでは読み込めないため、
		// 読み込めるように設定
		feedInput.setAllowDoctypes(true);

		try {
			return feedInput.build(reader);
		} catch (IllegalArgumentException | FeedException e) {
			System.err.print("Feed取得処理でエラー：Feed先をブラウザで開き、RSSとして利用できるか確認してください。");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<String> getRssUrlList() {
		//String filePath = "src\\main\\resources\\static\\url.txt";
		String filePath = urlPath;
		Path file = null;
		try {
			file = Paths.get(filePath);
			return Files.readAllLines(file);

		} catch (IOException e) {
			System.err.print("URLテキストオープン処理でエラー：" + filePath + "の存在を確認してください。");
			e.printStackTrace();
		}
		return null;

	}

}
