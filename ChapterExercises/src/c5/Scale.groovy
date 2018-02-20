package c5

import jcsp.lang.*
import groovyJCSP.*
import c05.ScaledData

class Scale implements CSProcess {
	def int scaling
	def int multiplier = 2

	def ChannelOutput outChannel
	def ChannelOutput factor
	def ChannelInput inChannel
	def ChannelInput suspend
	def ChannelInput injector

	void run () {
		def SECOND = 1000
		def DOUBLE_INTERVAL = 5* SECOND
		def SUSPEND  = 0
		def INJECT   = 1
		def TIMER    = 2
		def INPUT    = 3

		def timer = new CSTimer()
		def scaleAlt = new ALT ( [
			suspend,
			injector,
			timer,
			inChannel ]
		)
		def preCon = new boolean [4]
		preCon[SUSPEND] = true
		preCon[INJECT] = false
		preCon[TIMER] = true
		preCon[INPUT] = true
		def suspended = false
		def timeout = timer.read() + DOUBLE_INTERVAL
		timer.setAlarm ( timeout )
		////////MY VERSION//////////
		while (true) {
			switch ( scaleAlt.priSelect(preCon) ) {
				
				case SUSPEND :
				//  deal with suspend input
					println "Suspended"
					suspend.read() // its a signal, no data content
					factor.write(scaling)
					preCon[INJECT] = true
					preCon[SUSPEND]= false 
					preCon[TIMER] = false
					
					break
				
				case INJECT:
				//  deal with inject input
					scaling = injector.read()   //this is the resume signal as well
					println "Injected scaling is $scaling"
					timeout = timer.read() + DOUBLE_INTERVAL
					timer.setAlarm ( timeout )
					preCon[SUSPEND] = true
					preCon[TIMER] = true
						
					preCon[INJECT] = false
					break

				case TIMER:
					timeout = timer.read() + DOUBLE_INTERVAL
					timer.setAlarm ( timeout )
					scaling = scaling * multiplier	
					println "Normal Timer: new scaling is $scaling"	
					break

				case INPUT:
					def inValue = inChannel.read()
					def result = new ScaledData()
					result.original = inValue
				//   deal with Input channel
					if (preCon[SUSPEND]) {
						result.scaled = inValue *scaling
					} else {
						result.scaled = inValue 		
					}
					outChannel.write ( result )
					break
			} //end-switch
		} //end-while
	} //end-run
}