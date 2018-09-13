import scala.collection.mutable.Buffer
import scala.collection.JavaConverters

case class Article(
  board: String,
  id: String,
  title: String,
  author: String, 
  department: String,
  time: String,
  reads: Int,
  attached: List[String],
  content: String,
  images: List[String]
) {
  def getBoard = board
  def getId = id
  def getTitle = title
  def getAuthor = author
  def getDepartment = department
  def getTime = time
  def getReads = reads
  def getAttached = JavaConverters.bufferAsJavaList(attached.to[Buffer])
  def getContent = content
  def getImages = JavaConverters.bufferAsJavaList(images.to[Buffer])
}
