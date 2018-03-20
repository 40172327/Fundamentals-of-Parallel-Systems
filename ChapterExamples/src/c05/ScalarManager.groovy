package c05
 // copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import groovyJCSP.*
import jcsp.lang.*
import phw.util.*
import jcsp.awt.*
import java.awt.*

class ScalarManager implements CSProcess {
	
  def ChannelInput fromScalar
  def ChannelOutput toScalar
  def DisplayList toUI
  def ChannelInput fromUIButtons
  def ChannelOutput toUILabel
  def ChannelOutput toUIPause  
  
  void run() {
  
  } // run
}
                   