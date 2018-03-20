package c05
 
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import groovyJCSP.*
import jcsp.lang.*
import jcsp.awt.*
import java.awt.*

class UserInterfaceScaler implements CSProcess {
	
  def ActiveCanvas scalarCanvas
  def int canvasSize
  def ChannelInput scalarValueConfig
  def ChannelInput pauseButtonConfig
  def ChannelOutput buttonEvent
  
  void run() {
    def root = new ActiveClosingFrame ("Scaling System")
    def mainFrame = root.getActiveFrame()
    def scaleLabel = new Label ("Scalar value")
    def scaleValue = new ActiveLabel (scalarValueConfig)
    scaleValue.setAlignment( Label.CENTER)
    def pauseButton = new ActiveButton (pauseButtonConfig, buttonEvent, "PAUSE" )
    def scalarContainer = new Container()
    scalarContainer.setLayout ( new GridLayout ( 1, 5 ) )
    scalarContainer.add ( pauseButton )
    scalarContainer.add ( scaleLabel )  
    scalarContainer.add ( scaleValue )
   
    mainFrame.setLayout( new BorderLayout() )
    scalarCanvas.setSize (canvasSize, canvasSize) 
    mainFrame.add (scalarCanvas, BorderLayout.CENTER)
    mainFrame.add (scalarContainer, BorderLayout.SOUTH)
    mainFrame.pack()
    mainFrame.setVisible ( true )
    def network = [ root, scalarCanvas, scaleValue, pauseButton ]
    new PAR (network).run()
  }
}

    
    