/* @formatter:off
 *
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: Bag ADT
 * Fall, 2020
 *
 * Usage restrictions:
 *
 * You may use this code for exploration, experimentation, and furthering your
 * learning for this course. You may not use this code for any other
 * assignments, in my course or elsewhere, without explicit permission, in
 * advance, from myself (and the instructor of any other course).
 *
 * Further, you may not post nor otherwise share this code with anyone other than
 * current students in my sections of this course. Violation of these usage
 * restrictions will be considered a violation of the Wentworth Institute of
 * Technology Academic Honesty Policy.
 *
 * Do not remove this notice.
 *
 * @formatter:on
 */

package edu.wit.dcsn.comp2000.bag.adt ;

/**
 * Student tests for the LinkedBag class.
 * <p>
 * All public methods are tested.
 * <p>
 * These tests require the LinkedBag class to implement {@code BagInterface<T>}.
 *
 * @author Nick Magnante
 * @version 1.0.0 2020-09-14 initial version per assignment
 */
public class LinkedBagStudentTests
    {

    /**
     * Test driver
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {
        // SUGGESTION: implement a method or two then test them before implementing
        // the next method or two

        // QUESTION: In what order did you implement methods?

        // constructors
        testNoArgConstructor() ;
        testArrayConstructor() ;
        testCloningConstructor() ;

        // base API methods
        testAdd() ;
        testClear() ;
        testContains() ;
        testGetCurrentSize() ;
        testGetFrequencyOf() ;
        testIsEmpty() ;
        testRemove() ;
        testToArray() ;
        testToString() ;

        // enhanced API methods
        testDifference() ;
        testIntersection() ;
        testUnion() ;

        }	// end main()


    /**
     * Tests of the LinkedBag no-arg constructor
     */
    private static void testNoArgConstructor()
        {
        System.out.println( "Starting no-arg constructor tests\n" ) ;

        final LinkedBag<String> testBag = new LinkedBag<>() ;

        if ( testBag.isEmpty() )
            {
            System.out.println( "SUCCESS: Bag created and is empty." ) ;
            }
        else
            {
            System.out.println( "FAILED: Bag was not created or it isn't empty." ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished no-arg constructor tests\n" ) ;

        }   // end testNoArgConstructor()


    /**
     * Tests of the LinkedBag 'array' constructor
     */
    private static void testArrayConstructor()
        {
        System.out.println( "Starting 'array' constructor tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 45, 67 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Hello" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;

        if ( testBag.getCurrentSize() == testArray.length )
            {
            System.out.println( "Test 1: SUCCESS Bag created and filled with array contents" ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED Bag not created or not filled with array contents" ) ;
            }

        if ( testBag2.getCurrentSize() == testArray2.length )
            {
            System.out.println( "Test 2: SUCCESS Bag created and empty" ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED Bag not created or not empty" ) ;
            }

        if ( testBag3.getCurrentSize() == testArray3.length )
            {
            System.out.println( "Test 3: SUCCESS Bag created and empty" ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED Bag not created or not empty" ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished 'array' constructor tests\n" ) ;

        }   // end testArrayConstructor()


    /**
     * Tests of the LinkedBag 'cloning' constructor
     */
    private static void testCloningConstructor()
        {
        System.out.println( "Starting 'cloning' constructor tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 30, 45 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Test" } ;

        final BagInterface<Integer> testBag = new LinkedBag<>( testArray ) ;
        final BagInterface<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final BagInterface<String> testBag3 = new LinkedBag<>( testArray3 ) ;

        final LinkedBag<Integer> testBagClone = new LinkedBag<>( testBag ) ;
        final LinkedBag<Integer> testBagClone2 = new LinkedBag<>( testBag2 ) ;
        final LinkedBag<String> testBagClone3 = new LinkedBag<>( testBag3 ) ;

        if ( testBag.getCurrentSize() == testBagClone.getCurrentSize() )
            {
            System.out.println( "Test 1: SUCCESS Bag created and equal to original bag." ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED Bag not created or not equal to original bag." ) ;
            }

        if ( testBag2.getCurrentSize() == testBagClone2.getCurrentSize() )
            {
            System.out.println( "Test 2: SUCCESS Bag created and equal to original bag." ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED Bag not created or not equal to original bag." ) ;
            }

        if ( testBag3.getCurrentSize() == testBagClone3.getCurrentSize() )
            {
            System.out.println( "Test 3: SUCCESS Bag created and equal to original bag." ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED Bag not created or not equal to original bag." ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished 'cloning' constructor tests\n" ) ;

        }   // end testCloningConstructor()


    /**
     * Tests of the add() method
     */
    private static void testAdd()
        {
        System.out.println( "Starting add() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 30, 45 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Test" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;

        final int testBagInit = testBag.getCurrentSize() ;
        final int testBagInit2 = testBag2.getCurrentSize() ;
        final int testBagInit3 = testBag3.getCurrentSize() ;

        testBag.add( 5 ) ;
        testBag.add( 2 ) ;
        testBag.add( 1 ) ;

        testBag2.add( 3 ) ;

        testBag3.add( "Test" ) ;
        testBag3.add( "Yes" ) ;

        if ( testBag.getCurrentSize() > testBagInit )
            {
            System.out.println( "Test 1: SUCCESS, Bag added more item(s)." ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED, Bag didn't add more item(s)." ) ;
            }

        if ( testBag2.getCurrentSize() > testBagInit2 )
            {
            System.out.println( "Test 2: SUCCESS, Bag added more item(s)." ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED, Bag didn't add more item(s)." ) ;
            }

        if ( testBag3.getCurrentSize() > testBagInit3 )
            {
            System.out.println( "Test 3: SUCCESS, Bag added more item(s)." ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED, Bag didn't add more item(s)." ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished add() tests\n" ) ;

        }	// end testAdd()


    /**
     * Tests of the clear() method
     */
    private static void testClear()
        {
        System.out.println( "Starting clear() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 30, 45 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Test" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;

        testBag.clear() ;
        testBag2.clear() ;
        testBag3.clear() ;

        if ( testBag.getCurrentSize() == 0 )
            {
            System.out.println( "Test 1: SUCCESS, Bag cleared." ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED, Bag not cleared." ) ;
            }

        if ( testBag2.getCurrentSize() == 0 )
            {
            System.out.println( "Test 2: SUCCESS, Bag cleared." ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED, Bag not cleared." ) ;
            }

        if ( testBag3.getCurrentSize() == 0 )
            {
            System.out.println( "Test 3: SUCCESS, Bag cleared." ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED, Bag not cleared." ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished clear() tests\n" ) ;

        }	// end testClear()


    /**
     * Tests of the contains() method
     */
    private static void testContains()
        {
        System.out.println( "Starting contains() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 30, 45 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Test" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;

        if ( testBag.contains( 30 ) )
            {
            System.out.println( "Test 1: SUCCESS, bag does contain input and returned as such." ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED, bag does contain input and not returned as such" ) ;
            }

        if ( !testBag2.contains( 34 ) )
            {
            System.out.println( "Test 2: SUCCESS, bag is doesn't contain item and returned as such" ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED, bag doesn't contain item and returned as it did" ) ;
            }

        if ( !testBag3.contains( "Yes" ) )
            {
            System.out.println( "Test 3: SUCCESS, bag is doesn't contain item and returned it did" ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED, bag doesn't contain item and returned it did" ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished contains() tests\n" ) ;

        }	// end testContains()


    /**
     * Tests of the difference() method
     */
    private static void testDifference()
        {
        System.out.println( "Starting difference() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 30, 45 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Test" } ;
        final Integer[] testArray4 = new Integer[] { 5, 10, 1, 2 } ;
        final Integer[] testArray5 = new Integer[] { 4 } ;
        final String[] testArray6 = new String[] { "Test" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;
        final LinkedBag<Integer> testBag4 = new LinkedBag<>( testArray4 ) ;
        final LinkedBag<Integer> testBag5 = new LinkedBag<>( testArray5 ) ;
        final LinkedBag<String> testBag6 = new LinkedBag<>( testArray6 ) ;

        final BagInterface<Integer> testBagDiff = testBag.difference( testBag4 ) ;
        final BagInterface<Integer> testBagDiff2 = testBag2.difference( testBag5 ) ;
        final BagInterface<String> testBagDiff3 = testBag3.difference( testBag6 ) ;

        if ( testBagDiff.getCurrentSize() == 2 )
            {
            System.out.println( "Test 1: SUCCESS, bag contains correct amount of differences" ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED, bag contains incorrect amount of differences" ) ;
            }

        if ( testBagDiff2.getCurrentSize() == 0 )
            {
            System.out.println( "Test 2: SUCCESS, bag is null so it wont compare" ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED, bag is null so difference should return 0" ) ;
            }

        if ( testBagDiff3.getCurrentSize() == 0 )
            {
            System.out.println( "Test 3: SUCCESS, bag contains correct amount of differences" ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED, bag contains incorrect amount of differences" ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished difference() tests\n" ) ;

        }	// end testDifference()


    /**
     * Tests of the getCurrentSize() method
     */
    private static void testGetCurrentSize()
        {
        System.out.println( "Starting getCurrentSize() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 30, 45 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Test" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;

        if ( testBag.getCurrentSize() == 4 )
            {
            System.out.println( "Test 1: SUCCESS, returns size of bag correctly." ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED, returns size of bag incorrectly." ) ;
            }

        if ( testBag2.getCurrentSize() == 0 )
            {
            System.out.println( "Test 2: SUCCESS, returns size of bag correctly." ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED, returns size of bag incorrectly." ) ;
            }

        if ( testBag3.getCurrentSize() == 1 )
            {
            System.out.println( "Test 3: SUCCESS, returns size of bag correctly." ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED, returns size of bag incorrectly." ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished getCurrentSize() tests\n" ) ;

        }	// end testGetCurrentSize()


    /**
     * Tests of the getFrequencyOf() method
     */
    private static void testGetFrequencyOf()
        {
        System.out.println( "Starting getFrequencyOf() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 30, 45, 2, 2 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Test", "Yes" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;

        if ( testBag.getFrequencyOf( 2 ) == 3 )
            {
            System.out.println( "Test 1: SUCCESS, returns right amount of items in the bag." ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED, returns incorrect amount of items in the bag." ) ;
            }

        if ( testBag2.getFrequencyOf( 4 ) == 0 )
            {
            System.out.println( "Test 2: SUCCESS, returns right amount of items in the bag." ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED, returns incorrect amount of items in the bag." ) ;
            }

        if ( testBag3.getFrequencyOf( "No" ) == 0 )
            {
            System.out.println( "Test 3: SUCCESS, returns right amount of items in the bag." ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED, returns incorrect amount of items in the bag." ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished getFrequencyOf() tests\n" ) ;

        }	// end testGetFrequencyOf()


    /**
     * Tests of the intersection() method
     */
    private static void testIntersection()
        {
        System.out.println( "Starting intersection() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 30, 45 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Test" } ;
        final Integer[] testArray4 = new Integer[] { 5, 10, 1, 2 } ;
        final Integer[] testArray5 = new Integer[] { 4 } ;
        final String[] testArray6 = new String[] { "Test" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;
        final LinkedBag<Integer> testBag4 = new LinkedBag<>( testArray4 ) ;
        final LinkedBag<Integer> testBag5 = new LinkedBag<>( testArray5 ) ;
        final LinkedBag<String> testBag6 = new LinkedBag<>( testArray6 ) ;

        final BagInterface<Integer> testBagInter = testBag.intersection( testBag4 ) ;
        final BagInterface<Integer> testBagInter2 =
                                        testBag2.intersection( testBag5 ) ;
        final BagInterface<String> testBagInter3 =
                                        testBag3.intersection( testBag6 ) ;

        if ( testBagInter.getCurrentSize() == 2 )
            {
            System.out.println( "Test 1: SUCCESS, bag contains correct amount of intersections" ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED, bag contains incorrect amount of intersections" ) ;
            }

        if ( testBagInter2.getCurrentSize() == 0 )
            {
            System.out.println( "Test 2: SUCCESS, bag is null so it wont compare" ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED, bag is null so intersection should return 0" ) ;
            }

        if ( testBagInter3.getCurrentSize() == 1 )
            {
            System.out.println( "Test 3: SUCCESS, bag contains correct amount of intersections" ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED, bag contains incorrect amount of intersections" ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished intersection() tests\n" ) ;

        }	// end testIntersection()


    /**
     * Tests of the isEmpty() method
     */
    private static void testIsEmpty()
        {
        System.out.println( "Starting isEmpty() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 30, 45, 2, 2 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Test", "Yes" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;

        testBag3.clear() ;

        if ( !testBag.isEmpty() )
            {
            System.out.println( "Test 1: SUCCESS, returns bag is not empty when it isn't." ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED, returns bag is empty when it isn't." ) ;
            }

        if ( testBag2.isEmpty() )
            {
            System.out.println( "Test 2: SUCCESS, returns empty and is empty." ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED, returns not empty and is empty." ) ;
            }

        if ( testBag3.isEmpty() )
            {
            System.out.println( "Test 3: SUCCESS, returns empty and is empty." ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED, returns not empty and is empty." ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished isEmpty() tests\n" ) ;

        }	// end testIsEmpty()


    /**
     * Tests of the remove() methods
     */
    private static void testRemove()
        {
        System.out.println( "Starting remove() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 30, 45 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Test" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;

        final int testBagInit = testBag.getCurrentSize() ;
        final int testBagInit2 = testBag2.getCurrentSize() ;
        final int testBagInit3 = testBag3.getCurrentSize() ;

        testBag.remove( 5 ) ;
        testBag.remove( 2 ) ;
        testBag.remove( 1 ) ;

        testBag2.remove( 3 ) ;

        testBag3.remove( "Test" ) ;

        if ( testBag.getCurrentSize() < testBagInit )
            {
            System.out.println( "Test 1: SUCCESS, Bag removed more item(s)." ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED, Bag didn't remove more item(s)." ) ;
            }

        if ( testBag2.getCurrentSize() == testBagInit2 )
            {
            System.out.println( "Test 2: SUCCESS, Bag can't remove anything from null." ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED, Bag can't remove anything from null." ) ;
            }

        if ( testBag3.getCurrentSize() < testBagInit3 )
            {
            System.out.println( "Test 3: SUCCESS, Bag removed more item(s)." ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED, Bag didn't remove more item(s)." ) ;
            }

        // TODO Auto-generated method stub

        System.out.println( "\nFinished remove() tests\n" ) ;

        }	// end testRemove()


    /**
     * Tests of the toArray() method
     */
    private static void testToArray()
        {
        System.out.println( "Starting toArray() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 45, 67 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Hello" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;

        final Object[] testToArray = testBag.toArray() ;
        final Object[] testToArray2 = testBag2.toArray() ;
        final Object[] testToArray3 = testBag3.toArray() ;

        if ( testToArray.length == testBag.getCurrentSize() )
            {
            System.out.println( "Test 1: SUCCESS Array length matches bag size." ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED Array length doesn't match bag size." ) ;
            }

        if ( testToArray2.length == testBag2.getCurrentSize() )
            {
            System.out.println( "Test 2: SUCCESS Array length matches bag size." ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED Array length doesn't match bag size." ) ;
            }

        if ( testToArray3.length == testBag3.getCurrentSize() )
            {
            System.out.println( "Test 3: SUCCESS Array length matches bag size." ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED Array length doesn't match bag size." ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished toArray() tests\n" ) ;

        }	// end testToArray()


    /**
     * Tests of the toString() method
     */
    private static void testToString()
        {
        System.out.println( "Starting toString() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 45, 67 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Hello" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;

        if ( testBag.toString().equals( "[67, 45, 2, 1]" ) )
            {
            System.out.println( "Test 1: SUCCESS String matches contents." ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED String doesn't match contents." ) ;
            }

        if ( testBag2.toString().equals( "[]" ) )
            {
            System.out.println( "Test 2: SUCCESS String matches contents." ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED String doesn't match contents." ) ;
            }

        if ( testBag3.toString().equals( "[Hello]" ) )
            {
            System.out.println( "Test 3: SUCCESS String matches contents." ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED Array String doesn't match contents." ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished toString() tests\n" ) ;

        }	// end testToString()


    /**
     * Tests of the union() method
     */
    private static void testUnion()
        {
        System.out.println( "Starting union() tests\n" ) ;

        final Integer[] testArray = new Integer[] { 1, 2, 30, 45 } ;
        final Integer[] testArray2 = new Integer[] {} ;
        final String[] testArray3 = new String[] { "Test" } ;
        final Integer[] testArray4 = new Integer[] { 5, 10, 1, 2 } ;
        final Integer[] testArray5 = new Integer[] { 4 } ;
        final String[] testArray6 = new String[] { "Testing" } ;

        final LinkedBag<Integer> testBag = new LinkedBag<>( testArray ) ;
        final LinkedBag<Integer> testBag2 = new LinkedBag<>( testArray2 ) ;
        final LinkedBag<String> testBag3 = new LinkedBag<>( testArray3 ) ;
        final LinkedBag<Integer> testBag4 = new LinkedBag<>( testArray4 ) ;
        final LinkedBag<Integer> testBag5 = new LinkedBag<>( testArray5 ) ;
        final LinkedBag<String> testBag6 = new LinkedBag<>( testArray6 ) ;

        final BagInterface<Integer> testBagUnion = testBag.union( testBag4 ) ;
        final BagInterface<Integer> testBagUnion2 = testBag2.union( testBag5 ) ;
        final BagInterface<String> testBagUnion3 = testBag3.union( testBag6 ) ;

        if ( testBagUnion.getCurrentSize() ==
             ( testBag.getCurrentSize() + testBag4.getCurrentSize() ) )
            {
            System.out.println( "Test 1: SUCCESS, Union bag matches size of 2 combined bags" ) ;
            }
        else
            {
            System.out.println( "Test 1: FAILED, Union bag doesn't size of 2 combined bags" ) ;
            }

        if ( testBagUnion2.getCurrentSize() ==
             ( testBag2.getCurrentSize() + testBag5.getCurrentSize() ) )
            {
            System.out.println( "Test 2: SUCCESS, Union bag matches size of 2 combined bags" ) ;
            }
        else
            {
            System.out.println( "Test 2: FAILED, Union bag doesn't size of 2 combined bags" ) ;
            }

        if ( testBagUnion3.getCurrentSize() ==
             ( testBag3.getCurrentSize() + testBag6.getCurrentSize() ) )
            {
            System.out.println( "Test 3: SUCCESS, Union bag matches size of 2 combined bags" ) ;
            }
        else
            {
            System.out.println( "Test 3: FAILED, Union bag doesn't size of 2 combined bags" ) ;
            }

        // DONE Auto-generated method stub

        System.out.println( "\nFinished union() tests\n" ) ;

        }	// end testUnion()

    }	// end class LinkedBagStudentTests
