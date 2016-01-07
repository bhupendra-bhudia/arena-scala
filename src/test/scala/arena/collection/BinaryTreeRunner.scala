package arena.collection

/**
 * D Holbrook
 *
 * Code Club: PO1
 * https://gist.github.com/dholbrook/2967371
 *
 * (*) Define a binary tree data structure and related fundamental operations.
 *
 * Test data:
 * fold with preorder traversal (root, left, right)
 * Tail Recursive Optimized
 *
 *        F
 *      /   \
 *    B       G
 *   / \       \
 *  A   D       I
 *     / \     /
 *    C   E   H
 *
 */
object BinaryTreeRunner extends App {
  val t: BinaryTree[Symbol] = Node('F, Node('B, Leaf('A), Node('D, Leaf('C), Leaf('E))), Node('G, Empty, Node('I, Leaf('H), Empty)))
  println("tree: " + t)

  //print the value of b node navigating from root
  for {
    b <- t.left
    value <- b.value
  } println("B node: " + value)

  //print the value of e node navigating from root
  for {
    b <- t.left
    d <- b.right
    value <- d.value
  } println("D node: " + value)

  //no println() is executed for empty node chain
  for {
    b <- t.left
    d <- b.right
    e <- d.right
    x <- e.right
    value <- x.value
  } println("X node SHOUL NOT PRINT!: " + value)

  println("as seq: " + t.toSeq)

  println("count: " + t.size)
  assert(t.size == 9)

  println("height: " + t.height)
  assert(t.height == 3)

  println("leaft count: " + t.leafCount)
  assert(t.leafCount == 4)

  println("as seqPreorder: " + t.toSeqPreorder)
  println("as seqInorder: " + t.toSeqInorder)
  println("as seqPostorder: " + t.toSeqPostorder)
  println("as seqLevelorder: " + t.toSeqLevelorder)

  println("last preorder :" + t.lastPreorder)
  println("last inorder :" + t.lastInorder)
  println("last postorder :" + t.lastPostorder)
  println("last levelorder: " + t.lastLevelorder)

  println("nth preorder 5 : " + t.nthPreorder(5))
  println("nth inorder 5 : " + t.nthInorder(5))
  println("nth postorder 5 : " + t.nthPostorder(5))
  println("nth levelorder 5 : " + t.nthLevelorder(5))

  //
  /**
   * **** output *********
   *
  tree: Node('F,Node('B,Leaf('A),Node('D,Leaf('C),Leaf('E))),Node('G,Empty,Node('I,Leaf('H),Empty)))
  B node: 'B
  D node: 'D
  as seq: List('A, 'B, 'C, 'D, 'E, 'F, 'G, 'H, 'I)
  count: 9
  height: 3
  leaft count: 4
  as seqPreorder: List('F, 'B, 'A, 'D, 'C, 'E, 'G, 'I, 'H)
  as seqInorder: List('A, 'B, 'C, 'D, 'E, 'F, 'G, 'H, 'I)
  as seqPostorder: List('A, 'C, 'E, 'D, 'B, 'H, 'I, 'G, 'F)
  as seqLevelorder: List('F, 'B, 'G, 'A, 'D, 'I, 'C, 'E, 'H)
  last preorder :'H
  last inorder :'I
  last postorder :'F
  last levelorder: 'H
  nth preorder 5 : 'E
  nth inorder 5 : 'F
  nth postorder 5 : 'H
  nth levelorder 5 : 'I
   *
   * ***********************
   */
}