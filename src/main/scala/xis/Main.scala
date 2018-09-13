/*
import scala.collection.mutable.{Map => MMap, Buffer}

import ConnectUtil._
import LoginUtil._
import CrawlUtil._
import SearchUtil._
*/

object Main {
  def main(args: Array[String]): Unit = args.toList match {
    case id :: pw :: Nil => (new JavaInterface(id, pw)).main
    case _ =>
  }
}

/*
 * private implicit val cookie: Cookie = MMap()
 * private val boards = List("it_newsletter", "notice", "International", "international_opportunities",
 *   "computer_network", "influenza_a_h1n1", "rnd_notices", "manuals_forms",
 *   "student_notice", "lecture_academic_paper", "leadership_intern_counseling", "dormitory_notice",
 *   "dormitory_scholarship_welfare", "parttime_scholarship", "academic_courses", "recruiting",
 *   "gsc_usc_notice", "student_club", "researcher_on_military_duty", "classified")
 * def main(args: Array[String]): Unit = { Login
 *     args.toList match {
 *       case "-c" :: Nil => Crawl
 *       case "-s" :: keyword :: Nil => Search For keyword
 *       case _ =>
 *     }
 * }
 * def Crawl =
 *   for (boa <- boards;
 *        max <- getMax(boa);
 *        ind <- max to 1 by -1;
 *        id  <- getIds(boa, ind);
 *        art <- getArticle(boa, id))
 *     Test.f(art)
 * object Search {
 *   def For(keyword: String) = println(search(keyword, 1).map {
 *     case SearchResult(t, p, l, _, _) =>
 *       """<a href="""" + l + """" target="_blank">""" + t + """</a><p>""" + p + """</p>"""
 *   }.mkString("\n"))
 * }
 */
