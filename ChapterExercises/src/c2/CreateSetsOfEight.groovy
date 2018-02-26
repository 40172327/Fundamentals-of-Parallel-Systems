package c2

import jcsp.lang.*

class CreateSetsOfEight implements CSProcess{
	
	def ChannelInput inChannel

	void run(){
		def var = 4
		def v = inChannel.read()
		while (v != -1){
			def outList = []
			for ( i in 0 ..  var) {
				// put v into outList and read next input
				outList.add(v)
				v = inChannel.read()	
			}
			println " Eight Object is ${outList}"	
		}
		println "Finished"
	}
}