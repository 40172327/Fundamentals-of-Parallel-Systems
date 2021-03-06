package c17.sniff

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import jcsp.lang.*
import groovyJCSP.*
 

class SnifferComparator implements CSProcess {  
	
  def ChannelInput fromCopy
  def ChannelInput fromScaler
  def interval = 10000
  
  void run() {
    def connect = Channel.one2one()  
	  
    def testList = [ new Sniffer ( fromSystemCopy: fromCopy,
                                    toComparator: connect.out(),
                                    sampleInterval: interval), 
								
                     new Comparator ( fromSystemOutput: fromScaler,
                                       fromSniffer: connect.in() )
                    ]
    new PAR(testList).run()
  }
}
