package c2

import jcsp.lang.*
import groovyJCSP.*

class ThreeToEightTest extends GroovyTestCase {
	
	void testThreeToEight() {
		
		One2OneChannel connect1 = Channel.createOne2One()
		One2OneChannel connect2 = Channel.createOne2One()
		
		def generateSetsOfThree = new GenerateSetsOfThree(outChannel: connect1.out())		
		def listToStream = new ListToStream(inChannel: connect1.in(), outChannel: connect2.out())	
		def createSetsOfEight = new CreateSetsOfEight(var: 8, inChannel: connect2.in())
		
		def testList = [generateSetsOfThree, listToStream, createSetsOfEight]
						  				  
		new PAR (testList).run()
		
		
		def expected = [	[1, 2, 3, 4, 5, 6, 7, 8],
						[9, 10, 11, 12, 13, 14, 15, 16],
						[17, 18, 19, 20, 21, 22, 23, 24] ]
		def actual = createSetsOfEight.totalList
		assertTrue(expected == actual)		
		print "content of " + createSetsOfEight.totalList
	}
}
