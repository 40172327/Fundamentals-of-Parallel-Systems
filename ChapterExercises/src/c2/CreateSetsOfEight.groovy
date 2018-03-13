package c2

import jcsp.lang.*

class CreateSetsOfEight implements CSProcess{
	
	def ChannelInput inChannel
	def var = 8
	def totalList=[]
	void run(){
		
		def v = inChannel.read()
		while (v != -1){
			def outList = []
			for ( i in 1 ..  var) {
				// put v into outList and read next input
				outList.add(v)
				v = inChannel.read()	
			}
			println " Eight Object is ${outList}"	
			totalList.add(outList)
			println totalList
		}
		println "Finished"
	}
}