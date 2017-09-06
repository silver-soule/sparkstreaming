package edu.knoldus

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by Neelaksh on 5/9/17.
  */

object StreamProcessor extends App {
  Logger.getLogger("org").setLevel(Level.OFF)
  val conf = new SparkConf().setMaster("local[*]").setAppName("NameCount")
  val ssc = new StreamingContext(conf, Seconds(2))
  val data = ssc.receiverStream(new CustomReceiver())
  data.countByValue().print()
  ssc.start()
  ssc.awaitTermination()
}
