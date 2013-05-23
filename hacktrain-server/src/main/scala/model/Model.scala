package model

import java.util.Date


case class Train(id: Long, name: String, origin: String, destination: String, departure: Date, arrival: Date)

case class Station(id: Long, name: String)


object Station {
  def all = List(
    Station(1000, "Hamburg"),
    Station(1001, "Hamburg Hbf"),
    Station(1002, "Hamburg Dammtor"),
    Station(1003, "Hamburg Altona"),
    Station(1234, "München"),
    Station(2345, "Kassel"),
    Station(4567, "Frankfurt"),
    Station(7890, "Berlin"),
    Station(8901, "Basel")
  )
  
  def byName(name: String) = all.filter(_.name.toLowerCase.startsWith(name.toLowerCase))
}

object Train {
  val now = new Date()
  def all = List(
    Train(1, "ICE 970", "Hamburg", "München", now, new Date(now.getYear(), now.getMonth(), now.getDay(), now.getHours() + 5, now.getMinutes())),
    Train(2, "ICE 582", "Berlin", "Basel", now, new Date(now.getYear(), now.getMonth(), now.getDay(), now.getHours() + 7, now.getMinutes()))
  )
}