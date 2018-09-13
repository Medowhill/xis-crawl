case class SearchResult(
  title: String,
  preview: String,
  link: String,
  board: String,
  id: String
) {
  def getTitle = title
  def getPreview = preview
  def getLink = link
  def getBoard = board
  def getId = id
}
