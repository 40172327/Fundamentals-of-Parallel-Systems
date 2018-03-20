package c09

import jcsp.lang.CSProcess
import jcsp.lang.ChannelInput
import jcsp.lang.ChannelOutput

class Check implements CSProcess{

	def ChannelInput inChannel
	def ChannelOutput outChannel
	def eventsList=[]
	def counter = 0

	void run() {



		while (true) {

			def i=inChannel.read().copy()

			eventsList.add(i.copy())

			def d = i.data
			def m = i.missed


			if(counter!=0) {

				def currentData = eventsList.get(counter).data
				def currentMissed = eventsList.get(counter).missed

				//current data - current missed - 1 = data -1

				if( currentData - currentMissed - 1 == eventsList.get(counter-1).data) {
					println "Number of missed event is correct and equal to: " + currentMissed
				}else {
					println "Number of missed event is NOT correct"
				}
			}

			counter++

			outChannel.write( i )
		}
	}
}
