package arena.algorithms.graph

import scala.collection.mutable

/**
 * **************************************************************************
 * File: DirectedGraph
 * Author: Keith Schwarz (htiek@cs.stanford.edu)
 * <p>
 * A class representing a directed graph.  Internally, the class is represented
 * by an adjacency list.
 *
 * Translated and updated into Scala by
 *
 * @author Bhupendra Bhudia <bhupendra.bhudia@quedex.co.uk>
 *         26/11/2015 14:37
 */
class DirectedGraph[N, V] extends Iterable[N] {
  private final val graphMap: mutable.HashMap[N, mutable.HashMap[N, V]] = mutable.HashMap[N, mutable.HashMap[N,V]]()

  def show() = {
    graphMap.keys.foreach { start =>
      graphMap(start).keys.foreach { dest => println("(" + start + " ~~[" + graphMap(start)(dest) + "]~~> " + dest + ")") }
    }
  }

  def weightOf(start: N, dest: N): Option[V] = {
    if (graphMap.contains(start) && graphMap.contains(dest))
      Option[V](graphMap(start)(dest))
    else
      None
  }

  def nodes: Set[N] = graphMap.keys.toSet

  /**
   * Adds a new node to the graph.  If the node already exists, this
   * function is a no-op.
   *
   * @param node The node to add.
   * @return Whether or not the node was added.
   */
  def addNode(node: N): Boolean = {
    if (graphMap.contains(node))
      false
    else {
      graphMap(node) = mutable.HashMap()
      true
    }
  }

  /**
   * Given a start node, and a destination, adds an arc from the start node
   * to the destination.  If an arc already exists, this operation is a
   * no-op.  If either endpoint does not exist in the graph, throws a
   * NoSuchElementException.
   *
   * @param start The start node.
   * @param dest  The destination node.
   * @param weight The weight or distance between start and destination node.
   * @throws java.util.NoSuchElementException If either the start or destination nodes do not exist.
   */
  def addEdge(start: N, dest: N, weight: V) = {
    if (!graphMap.contains(start))
      graphMap(start) = mutable.HashMap()

    if (!graphMap.contains(dest))
      graphMap(dest) = mutable.HashMap()

    graphMap(start) += dest -> weight
  }

  /**
   * Removes the edge from start to dest from the graph.  If the edge does
   * not exist, this operation is a no-op.  If either endpoint does not
   * exist, this throws a NoSuchElementException.
   *
   * @param start The start node.
   * @param dest  The destination node.
   * @throws java.util.NoSuchElementException If either node is not in the graph.
   */
  def removeEdge(start: N, dest: N) = {
    if (graphMap.contains(start) && graphMap.contains(dest))
      graphMap(start) -= dest
  }

  /**
   * Given two nodes in the graph, returns whether there is an edge from the
   * first node to the second node.  If either node does not exist in the
   * graph, throws a NoSuchElementException.
   *
   * @param start The start node.
   * @param end   The destination node.
   * @return Whether there is an edge from start to end.
   * @throws java.util.NoSuchElementException If either endpoint does not exist.
   */
  def edgeExists(start: N, end: N): Boolean = {
    if (!graphMap.contains(start) || !graphMap.contains(end))
      false
    else
      graphMap.get(start).contains(end)
  }

  /**
   * Given a node in the graph, returns an immutable view of the edges
   * leaving that node as a set of endpoints.
   *
   * @param node The node whose edges should be queried.
   * @return An immutable view of the edges leaving that node.
   * @throws java.util.NoSuchElementException If the node does not exist.
   */
  def edgesFrom(node: N): Set[N] = graphMap(node).keys.toSet

  /**
   * Returns an iterator that can traverse the nodes in the graph.
   *
   * @return An iterator that traverses the nodes in the graph.
   */
  def iterator: Iterator[N] = graphMap.keys.iterator

  /**
   * Returns the number of nodes in the graph.
   *
   * @return The number of nodes in the graph.
   */
  override def size: Int = graphMap.size

  /**
   * Returns whether the graph is empty.
   *
   * @return Whether the graph is empty.
   */
  override def isEmpty: Boolean = graphMap.isEmpty
}
