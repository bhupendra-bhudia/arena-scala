/*
 * Represents a cell in the board.  May be alive or dead.
 */

package arena.gameoflife

sealed abstract class Cell {
  def createNextGeneration(liveNeighborCount: Int): Cell = {
    if (liveNeighborCount < 0 || liveNeighborCount > 8)
      throw new IllegalArgumentException("Live neighbor count must be between 0 and 8. Was %s" format liveNeighborCount)

    doCreation(liveNeighborCount);
  }

  protected def doCreation(liveNeighborCount: Int): Cell
}

/*
At each step in time, the following transitions occur:

- Any live cell with fewer than two live neighbours dies, as if caused by under-population.
- Any live cell with two or three live neighbours lives on to the next generation.
- Any live cell with more than three live neighbours dies, as if by overcrowding.
- Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

Aka The standard Game of Life symbolised by "B3/S23";
A cell is "Born" if it has exactly 3 neighbours, "Stays alive" if it has 2 or 3 living neighbours; it dies otherwise.
 */
case object AliveCell extends Cell {
  override def doCreation(liveNeighborCount: Int) = liveNeighborCount match {
    case 2 => AliveCell
    case 3 => AliveCell
    case _ => DeadCell
  }

  override def toString = "X"
}

case object DeadCell extends Cell {
  override def doCreation(liveNeighborCount: Int) = liveNeighborCount match {
    case 3 => AliveCell
    case _ => DeadCell
  }

  override def toString = "."
}
