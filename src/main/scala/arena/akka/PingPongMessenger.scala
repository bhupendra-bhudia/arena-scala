package arena.akka

import akka.actor._

case object PingMessage

case object PongMessage

case object StartMessage

case object StopMessage

class Ping(pong: ActorRef) extends Actor {
  var count = 0

  def receive = {
    case StartMessage =>
      incrementAndPrint
      pong ! PingMessage
    case PongMessage =>
      incrementAndPrint
      if (count > 10) {
        sender ! StopMessage
        println("ping stopped")
        context.stop(self)
      } else {
        sender ! PingMessage
      }
  }

  def incrementAndPrint {
    count += 1
    println("ping")
  }
}

class Pong extends Actor {
  def receive = {
    case PingMessage =>
      println("  pong")
      sender ! PongMessage
    case StopMessage =>
      println("pong stopped")
      context.stop(self)
  }
}

/**
 * Created by bbhudia on 01/10/2015.
 */
object PingPongMessenger extends App {
  val system = ActorSystem("PingPongMessenger")
  val pong = system.actorOf(Props[Pong], name = "pong")
  val ping = system.actorOf(Props(new Ping(pong)), name = "ping")
  // start them going
  ping ! StartMessage
  system.terminate()
}
