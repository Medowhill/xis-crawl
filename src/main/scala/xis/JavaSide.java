import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import org.joda.time.DateTime;

class JavaSide {
  static void main(JavaInterface ji) {
   // example(ji);
    randomSelect(ji);
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

  private static DateTime parseTime(Article article) {
    String time = article.getTime();
    String[] strTokens = time.split("\\.|\\s+|\\:");
    int[] tokens = new int[strTokens.length];
    for(int i = 0; i < strTokens.length; i++) {
      tokens[i] = Integer.parseInt(strTokens[i]);
    }
    DateTime uploadTime = new DateTime(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
    return uploadTime;
  }

  private static void randomSelect(JavaInterface ji) {
    List<Article> selectedArticles = new ArrayList();

    while(selectedArticles.size() < 10) {
      String board = ji.getBoards()[new Random().nextInt(ji.getBoards().length)];
      int max = ji.getMaxPageOfBoard(board);
      List<String> ids = ji.getArticleIdsOfPage(board, new Random().nextInt(max));
      Article article = ji.getArticleById(board, ids.get(new Random().nextInt(ids.size())));

      DateTime curTime = new DateTime();
      DateTime limitTime = curTime.minusYears(1);
      DateTime articleTime = parseTime(article);
      if(articleTime.isAfter(limitTime)) {
        selectedArticles.add(article);
        System.out.println(board);
        System.out.println(max);
        System.out.println(ids);
        System.out.println(article.getTitle());
        System.out.println(article.getId());
        System.out.println(article.getTime());
        System.out.println(article.getAuthor());
        System.out.println(article.getImages());
      }
    }
  }
}

