import java.sql.{DriverManager, Connection}

object SqlUtil {
  private var driverLoaded = false

  private def loadDriver() {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance
      driverLoaded = true
    } catch {
      case e : Exception => {
        println("Driver not available")
      }
    }
  }

  def getConnection() : Connection = {
    this.synchronized {
      if (!driverLoaded) {
        loadDriver()
      }
    }

    try {
      val url = "jdbc:mysql://172.18.0.3:3306"
      val username = "creambears"
      val pwd = "zmflaqpdjwm"
      DriverManager.getConnection(url, username, pwd)
    } catch {
      case e : Exception => {
        println("Bad")
        throw e
      }
    }
  }
} 
