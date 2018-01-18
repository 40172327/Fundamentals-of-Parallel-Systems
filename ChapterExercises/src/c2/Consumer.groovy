package c2
 
import phw.util.*
import jcsp.lang.*


class Consumer implements CSProcess {
  
  def ChannelInput inChannel
  
  void run() {
    def i = inChannel.read()
	println i
    while ( i > 0 ) {
      //insert a modified println statement
      i = inChannel.read()
	  println i 
    }
    println "Finished"
  }
}

