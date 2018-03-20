package c05
 // copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import groovyJCSP.*
import jcsp.lang.*
import phw.util.*
import jcsp.util.*
import jcsp.awt.*

class ScalerInterface implements CSProcess {
	
  def ChannelInput inChannel
  def ChannelOutput outChannel
  def int canvasSize = 100
  
  void run() {
    def dList = new DisplayList()
    def scalarCanvas = new ActiveCanvas()
    scalarCanvas.setPaintable (dList)
    def tempConfig = Channel.one2one()
    def pauseConfig = Channel.one2one()
    def uiEvents = Channel.any2one( new OverWriteOldestBuffer(5) )
    def network = [ new ScalarManager ( fromScalar: inChannel, 
                                          toScalar: outChannel,
                                          toUI: dList,
                                          fromUIButtons: uiEvents.in(),
                                          toUIPause: pauseConfig.out(),
                                          toUILabel: tempConfig.out(),
                                           ),
                    new UserInterfaceScaler   ( scalarCanvas: scalarCanvas, 
                                          canvasSize: canvasSize,
                                          pauseButtonConfig: pauseConfig.in(),
                                          buttonEvent: uiEvents.out()  )
                  ]
    new PAR ( network ).run()
  }
}
   