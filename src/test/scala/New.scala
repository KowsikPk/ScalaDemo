import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.protobufv3.internal.TextFormat.Printer
import org.jsoup.Jsoup

import javax.management.Query.attr
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor, Future}
import scala.concurrent.duration.{Duration, DurationInt}
import scala.jdk.CollectionConverters._
import scala.language.postfixOps

case class ProductCrawler()(implicit val system: ActorSystem, ec: ExecutionContext) {
  def crawl(url: String): Future[String] = {
    val request = HttpRequest(uri = url)
    Http()
      .singleRequest(request)
      .flatMap(res =>
        res.entity
          .toStrict(30 seconds)
          .map(_.data.utf8String)
      )
  }
}
case class ResultObject(title:String)



case class ProductParser()  {
  def parse(htmlContent: String): ResultObject= {
    val doc   = Jsoup.parse(htmlContent)
    val title = doc.select("h1").text()
//    val Dict = doc.select("table:nth-child(2)")
    val table_head = doc.select("table:nth-child(2) thead tr th")
    val table_body = doc.select("table:nth-child(2) tbody tr td")

    val x = table_head.asScala.map(el => el.text()).toList
    val y = table_body.asScala.map(el => el.text()).toList
    val res = (x zip y).toMap

//    println(x)
//    println(y)
//    println(res)
    res.map(println)
    ResultObject(title)
  }
}

object New extends App {
  implicit val system: ActorSystem = ActorSystem("simple-workflow")
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  val url     = "https://tax.colorado.gov/sales-tax-rate-changes"
  val crawler = ProductCrawler()
  val parser  = ProductParser()

  val resultFut: Future[ResultObject]= crawler.crawl(url).map(htmlContent => parser.parse(htmlContent))

  val result  = Await.result(resultFut, Duration.Inf)

  println("-"*50)
  print('this is added by me')
//  println("Product Name : " + result.title)


}
