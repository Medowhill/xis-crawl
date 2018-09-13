import java.util.List;

class JavaSide {
  static void main(JavaInterface ji) {
    example(ji);
  }

  private static void example(JavaInterface ji) {
    String board = ji.getBoards()[0];
    int max = ji.getMaxPageOfBoard(board);
    List<String> ids = ji.getArticleIdsOfPage(board, 1);
    Article article = ji.getArticleById(board, ids.get(0));
	List<SearchResult> res = ji.searchAtPage("수강 신청", 1);

	System.out.println(board);
	System.out.println(max);
	System.out.println(ids);
	System.out.println(article.getTitle());
	System.out.println(article.getId());
	System.out.println(article.getTime());
	System.out.println(article.getAuthor());
	System.out.println(article.getImages());
	System.out.println(res.get(0).getTitle());
	System.out.println(res.get(0).getLink());
  }
}
