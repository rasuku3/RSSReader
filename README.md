# 概要
JavaのRomeライブラリの検証用ソースです。サンプルとして簡易なRSSリーダーAPIを作成しました。
標準出力に以下を出力します。
* RSSフィード：title
* RSSフィードエントリー：title
* RSSフィードエントリー：description
## 使用技術
* [Rome 1.12.0](https://github.com/rometools/rome)
* Java 8
* Spring boot 2.1.5

## クラス図
![class_image cld](https://user-images.githubusercontent.com/30997318/58959031-b77ee600-8793-11e9-9968-6d7208529260.jpg)

## 作成から実行まで

1. Spring Initializerでmavenプロジェクトをダウンロード
2. IDEを開き、pom.xmlにRomeを記載する。
	```xml
	<dependency>
		<groupId>com.rometools</groupId>
		<artifactId>rome</artifactId>
	    <version>1.12.0</version>
	</dependency>
	```
3. RSSリーダーを作成
※gistに上がったソースをご利用ください。
4. maven install後、Spring Bootアプリケーションとして実行
5. urlにhttp://localhost:8080/を指定し、httpリクエストを送信
6. 標準出力にRSSフィードの情報が表示される

## ポイント  
* 責務に応じてクラスを分離
たとえば、Controllerはドメインロジックを呼び出す、RSSフィード出力処理を呼び出すのみにとどめています。
RSS変換処理はConverter系のクラス(RssReaderConverter)に任せ、他のクラスで変換処理をすることを抑制しました。
* 処理に応じて実装クラスを変えられるような構成をとりました。
インターフェースクラスを用意し、実装クラスをDIするような構成にしました。
* 複数のRSSフィード取得に対応しました。
以下のように記載していくことで複数のRSSフィードが読み取れます。

	```
	(src\main\resources\static\url.txt)
	urlA
	urlB
	urlC
	```
* 標準出力だけでなく、ファイル出力にも対応しています。
FIXMEタグがあるソースをコメントアウト解除してご利用ください。
コメントアウトを解除して、アプリケーションを実行すると、以下のような結果がファイルに出力されます。  

	```
	(log\rss_output.txt)
	■■Feed読み込み 開始：RSSフィードのタイトル
	▼Feed entry読み込み 開始：RSSフィードエントリーのタイトル
	RSSフィードエントリーのdescription
	▲Feed entry読み込み 終了
	■■Feed読み込み 終了
	```
* RSS配信サイトURL、RSS取得結果の出力先ファイルのパスは、変更できるようにしました。
 * RSS配信サイトURL
「src\main\resources\static\url.txt」に記載。
 * RSS配信サイトURL
 「src\main\resources\application.properties」に記載。
