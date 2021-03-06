package c2

import jcsp.lang.*
 
class Multiplier implements CSProcess {
  
  def ChannelOutput outChannel
  def ChannelInput inChannel
  def int factor
  
  void run() {
    def i = inChannel.read()
    while (i > 0) {
      // write i * factor to outChannel
      // read in the next value of i
		def num = i * factor
		outChannel.write(num)
		i = inChannel.read()	
    }
    outChannel.write(i)
  }
}

    
