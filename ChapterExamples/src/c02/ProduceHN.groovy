package c02

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import phw.util.*
import jcsp.lang.*

class ProduceHN implements CSProcess {
  
  def ChannelOutput outChannel
  
  void run() {
    def hi = "Hello monkey honey"
    def thing = Ask.string ("\nName ? ")
    outChannel.write ( hi )
    outChannel.write ( thing )   
  } 
}

      
  
  
