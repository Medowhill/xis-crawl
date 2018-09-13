* How to run
```shell
$ git clone https://github.com/Medowhill/xis-crawl.git
$ cd xis-crawl
$ sbt
...
sbt:xIS> run [portal_id] [portal_pw]
```

* Where to write code: `JavaSide` class
  * Write code, which you want to run, inside the `main` method in the `JavaSide` class.
  * You can invoke the methods of `JavaInterface` class using the argument.
  * Fill free to declare helper methods or classes.

* JavaInterface class
```Java
String[] getBoards();
int getMaxPageOfBoard(String board);
java.util.List<String> getArticleIdsOfPage(String board, int page);
Article getArticleById(String board, String id);
java.util.List<SearchResult> searchAtPage(String keyword, int page);
```

* Article class
```Java
String getBoard();
String getId();
String getTitle();
String getAuthor();
String getDepartment();
String getTime();
int getReads();
java.util.List<String> getAttached();
String getContent();
java.util.List<String> getImages();
```

* SearchResult class
```Java
String getTitle();
String getPreview();
String getLink();
String getBoard();
String getId();
```
