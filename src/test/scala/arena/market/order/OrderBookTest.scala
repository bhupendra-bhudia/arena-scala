package arena.market.order

object OrderBookTest extends App {
  val random = new scala.util.Random

  val msftBook = new OrderBook("MSFT")

  msftBook.listenForEvents((response) => {
    response match {
      case resp => println(resp)
    }
  })

  msftBook.listenForMarketData((response) => {
    response match {
      case resp => println(resp)
    }
  })


  //one bid, only bidQ should be populated
  val order1 = NewOrder(1, "ETI1", "MSFT", 100, true, Some(50))
  msftBook.processOrderBookRequest(order1)
  assert(!msftBook.bidsQ.isEmpty)
  assert(msftBook.offersQ.isEmpty)

  //execute 50 shares of the order in bidsQ
  val order2 = NewOrder(1, "ETI2", "MSFT", 50, false, Some(50))
  msftBook.processOrderBookRequest(order2)
  assert(msftBook.bidsQ.peek.qty == 50)
  assert(msftBook.offersQ.isEmpty)

  //offer shares at a price where both bid and offer queues are populated with 50 shares
  val order3 = NewOrder(1, "ETI3", "MSFT", 50, false, Some(51))
  msftBook.processOrderBookRequest(order3)
  assert(msftBook.bidsQ.peek.qty == 50)
  assert(msftBook.offersQ.peek.qty == 50)

}