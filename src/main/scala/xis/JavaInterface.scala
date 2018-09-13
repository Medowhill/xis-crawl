import scala.collection.mutable.{Map => MMap, Buffer}
import scala.collection.JavaConverters

import java.util.{List => JList}

import ConnectUtil._
import LoginUtil._
import CrawlUtil._
import SearchUtil._

class JavaInterface(id: String, pw: String) {

  private implicit val cookie: Cookie = MMap()

  def getBoards = Array("it_newsletter", "notice", "International", "international_opportunities",
    "computer_network", "influenza_a_h1n1", "rnd_notices", "manuals_forms", /* "seminar_events", */
    "student_notice", "lecture_academic_paper", "leadership_intern_counseling", "dormitory_notice",
    "dormitory_scholarship_welfare", "parttime_scholarship", "academic_courses", "recruiting",
    "gsc_usc_notice", "student_club", "researcher_on_military_duty", "classified" /* , "work_notice" */)

  def getMaxPageOfBoard(board: String): Int = getMax(board).getOrElse(0)
  def getArticleIdsOfPage(board: String, page: Int): JList[String] = JavaConverters.bufferAsJavaList(getIds(board, page).to[Buffer])
  def getArticleById(board: String, id: String): Article = getArticle(board, id).getOrElse(null)

  def searchAtPage(keyword: String, page: Int): JList[SearchResult] = JavaConverters.bufferAsJavaList(search(keyword, page).to[Buffer])

  Login(id, pw)

  def main = JavaSide.main(this)
}
