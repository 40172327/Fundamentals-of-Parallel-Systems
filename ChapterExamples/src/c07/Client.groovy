package c07

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import jcsp.lang.*
import groovyJCSP.*


class Client implements CSProcess{  
	
  def ChannelInput receiveChannel
  def ChannelOutput requestChannel
  def clientNumber   
  def selectList = [ ] 
  def correct = 0
  def incorrect = 0
   
  void run () {
    def iterations = selectList.size
    println "Client $clientNumber has $iterations values in $selectList"
    for ( i in 0 ..< iterations) {
      def key = selectList[i]
      requestChannel.write(key)
      def v = receiveChannel.read()
	  
	  if(v/10 == key){
		//  println "Client n= " + clientNumber + " v= " + v + " key=" + key
		 // println "DATA FOUND IN DATABASE"
		  correct++
	  }
	  else {
		 // println "DATA NOT FOUND IN DATABASE"
		  incorrect++
	  }
		
	  
    }
    println "Client $clientNumber has finished"
	println "Number of correct transactions for Client $clientNumber: " + correct
	println "Number of incorrect transactions for Client $clientNumber: " + incorrect
  }
}
