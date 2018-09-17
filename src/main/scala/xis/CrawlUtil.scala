import ConnectUtil._
import SqlUtil._

import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model._

object CrawlUtil {

  private val boardUrl = "https://portal.kaist.ac.kr/board/list.brd"
  private val articleUrl = "https://portal.kaist.ac.kr/board/read.brd"
  private val connection = getConnection()

  private def getBoard(board: String, index: Int)(implicit cookie: Cookie): Document = {
    post(boardUrl, Map("page" -> index.toString, "boardId" -> board))._1
  }

  def getMax(board: String)(implicit cookie: Cookie): Option[Int] = {
    val doc = getBoard(board, 1)
    val str = (doc >> elementList("script")).map(_.innerHtml).mkString("").split("\n")
      .filter(_.contains("var totalPage")).toList.head
    val start = str.indexOf("= ")
    if (start < 0) None
    else {
      val end = str.indexOf(";")
      val num = """(\d+)""".r
      str.substring(start + 2, end) match {
        case num(max) => Some(max.toInt)
        case _ => None
      }
    }
  }

  def getIds(board: String, index: Int)(implicit cookie: Cookie): List[String] = {
    val doc = getBoard(board, index)
    val key = s"/ennotice/${board}/"
    (doc >> elementList("a") >?> (_ >> attr("href")("a")))
      .flatten.filter(_.contains(key)).map(s => s.substring(key.length, s.length))
  }

  def getArticle(board: String, id: String)(implicit cookie: Cookie): Option[Article] = {
    val doc = get(articleUrl, Map("cmd" -> "READ", "boardId" -> board, "bltnNo" -> id))._1

    def parse(au: String) = {
      val (start, end) = (au.indexOf('('), au.indexOf(')'))
      if (start < 0 || end < 0) (au, "")
      else (au.substring(0, start), au.substring(start + 1, end))
    }
    (doc >> elementList("tbody")).head >> elementList("td") match {
      case tit :: aut :: tim :: att :: con :: _ =>
        val title = tit >> allText("td")
        val (author, department) = parse(aut >> allText("td"))
        val (time, reads) = parse(tim >> allText("td"))
        val attached = (att >> elementList(".req_file") >?> allText("a")).flatten
        val contents = con >> allText("td")
        val images = (con >> elementList("img") >?> attr("src")("img")).flatten
        val statement = connection.createStatement

        val hits = reads.toInt
        statement.executeUpdate("USE CRAWL")
        val sqlArticle = s"INSERT INTO ARTICLES VALUES (\'$board\', \'$id\', \'${title.replaceAll("['\"\\\\]", "\\\\$0")}\', \'$author\', \'$department\', \'$time\', $hits, \'${contents.replaceAll("['\"\\\\]", "\\\\$0")}\')"
		println(sqlArticle)
        statement.executeUpdate(sqlArticle)
        attached.foreach(elem => statement.executeUpdate(s"INSERT INTO ATTACHEDLINKS (articleId, linkPath) VALUES (\'$id\', \'$elem\')"))
        images.foreach(elem => statement.executeUpdate(s"INSERT INTO IMAGELINKS (articleId, imagePath) VALUES (\'$id\', \'$elem\')"))
        Some(Article(board, id, title, author, department, time, hits, attached, contents, images))
      case _ => None
    }
  }
}
