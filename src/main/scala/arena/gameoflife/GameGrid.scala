package arena.gameoflife

import java.awt.geom.Rectangle2D
import java.awt.{Color, Dimension, Graphics2D}

import scala.actors.Actor
import scala.swing._

case class SetUpdating(update: Boolean)
case class AdjustSpeed(deltaMs: Int)
case object Randomize
case object Exit

class GameGrid extends Component with Actor {

  preferredSize = new Dimension(256, 256)

  val width = 128
  val height = 128
  var _board = randomBoard(width, height);
  var minX = 0
  var maxX = 0
  var minY = 0
  var maxY = 0
  private var speed = 250
  private var updating = true
  private var stopped = false

  override def paintComponent(g: Graphics2D) {
    val bounds = g.getClipBounds
    minX = minX min _board.minX
    maxX = maxX max _board.maxX
    minY = minY min _board.minY
    maxY = maxY max _board.maxY
    val width = maxX - minX
    val height = maxY - minY

    val xScale = (bounds.width - bounds.x) / (width max height).asInstanceOf[Double]
    val yScale = (bounds.height - bounds.y) / (width max height).asInstanceOf[Double]
    g.setColor(Color.WHITE)
    g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
    g.setColor(Color.BLACK)
    _board.aliveCells.foreach { location =>
      g.fill(new Rectangle2D.Double((location.x - minX) * xScale, (location.y - minY) * yScale, xScale, yScale))
    }
  }

  def act() = loopWhile(!stopped) {
    reactWithin(speed) {
      case SetUpdating(value) => updating = value
      case Randomize => _board = randomBoard(width, height)
      case AdjustSpeed(delta) => speed = 50 max (speed - delta)
      case Exit => stopped = true
      case _ if (updating) => {
        _board = _board.evolve
        repaint
      }
    }
  }

  def randomBoard = EndlessBoard.randomBoard _
}






