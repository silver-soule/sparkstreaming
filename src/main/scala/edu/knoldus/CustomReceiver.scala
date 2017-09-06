package edu.knoldus

import java.sql.DriverManager
import com.typesafe.config.ConfigFactory
import org.apache.spark.internal.Logging
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Neelaksh on 5/9/17.
  */
class CustomReceiver extends Receiver[String](StorageLevel.MEMORY_AND_DISK_2) with Logging {
  private val config = ConfigFactory.load()
  val driver = config.getString("driver")
  val url = config.getString("url")
  val username = config.getString("username")
  val password = config.getString("password")

  override def onStart(): Unit = {
    Future {
      receive()
    }
  }

  override def onStop(): Unit = {

  }

  private def receive() = {
    Class.forName(driver)
    logInfo("connecting to db....")
    val connection = DriverManager.getConnection(url, username, password)
    val sql = "SELECT name FROM names"
    val statement = connection.prepareStatement(sql)
    val op = statement.executeQuery()
    logInfo("query executed")
    while (op.next()) {
      val data = op.getString("name")
      store(data)
    }
    connection.close()
    restart("restarting receiver")
  }
}
