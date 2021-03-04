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

package edu.wit.dcsn.dmr.testing ;

import static org.junit.jupiter.api.Assertions.assertEquals ;
import static org.junit.jupiter.api.Assertions.assertFalse ;
import static org.junit.jupiter.api.Assertions.assertNull ;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively ;
import static org.junit.jupiter.api.Assertions.assertTrue ;
import static org.junit.jupiter.api.Assertions.fail ;

import org.junit.jupiter.api.DisplayName ;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation ;
import org.junit.jupiter.api.Order ;
import org.junit.jupiter.api.Test ;
import org.junit.jupiter.api.TestInfo ;
import org.junit.jupiter.api.TestInstance ;
import org.junit.jupiter.api.TestInstance.Lifecycle ;
import org.junit.jupiter.api.TestMethodOrder ;
import org.junit.jupiter.params.ParameterizedTest ;
import org.junit.jupiter.params.provider.CsvFileSource ;

import java.util.Arrays ;

import edu.wit.dcsn.comp2000.bag.adt.BagInterface ;
import edu.wit.dcsn.comp2000.bag.adt.LinkedBag ;

import edu.wit.dcsn.dmr.testing.junit.JUnitTestingBase ;
import edu.wit.dcsn.dmr.testing.junit.PlaceholderException ;

import static edu.wit.dcsn.dmr.testing.junit.Reflection.* ;
import static edu.wit.dcsn.dmr.testing.junit.TestData.* ;

/**
 * JUnit tests for the LinkedBag class. All public and package visible methods are
 * tested. These tests require the API for the LinkedBag class implement
 * {@code BagInterface<T>}.
 *
 * @author David M Rosenberg
 * @version 1.0.0 2018-05-25 initial set of tests<br>
 * @version 1.1.0 2018-06-09 revise structure to use TestInfo instead of certain
 *     hard-coded text
 * @version 1.2.0 2018-09-02 add timeouts
 * @version 1.3.0 2019-01-14 more implementation
 * @version 1.3.1 2019-01-17 cosmetic changes
 * @version 2.0.0 2019-05-12
 *     <ul>
 *     <li>restructure tests
 *     <li>disable System.exit() during testing
 *     <li>start making each subtest independent so they'll all run even if one fails
 *     </ul>
 * @version 2.1.0 2019-05-17
 *     <ul>
 *     <li>rename class
 *     <li>remove unnecessary throws clauses from @BeforeXxx and @AfterXxx
 *     <li>more fully utilize JUnit 5.4 features
 *     <li>switch tests to data-driven
 *     </ul>
 * @version 3.0.0 2019-06-27
 *     <ul>
 *     <li>complete re-write with reusable testing infrastructure
 *     <li>tests are now data-driven
 *     <li>add summary test results
 *     </ul>
 * @version 3.1.0 2019-06-28 move detailed activity to log file
 * @version 4.0.0 2019-07-04 split general purpose utilities methods into separate
 *     class
 * @version 4.1.0 2020-01-28
 *     <ul>
 *     <li>test additional methods (toString, array constructor, cloning constructor
 *     with non-LinkedBag argument)
 *     <li>reformat per class standard
 *     </ul>
 * @version 4.2.0 2020-02-09 make some tests stricter wrt source entry ordering (must
 *     be unchanged)
 * @version 4.3.0 2020-05-15
 *     <ul>
 *     <li>implement testToString()
 *     <li>add/enhance null argument test capabilities
 *     <li>display Exceptions as actual/verification results
 *     <li>verify that all non-destructive/non-modifying methods are repeatable
 *     </ul>
 * @version 4.4.0 2020-08-07 revisions to interrogate instance variables via testing
 *     infrastructure
 * @version 4.5.0 2020-08-08 track changes to BagInterface - switch to
 *     EnhancedBagInterface and LinkedBag
 * @version 4.6.0 2020-09-13
 *     <ul>
 *     <li>track changes to BagInterface - switch back to it
 *     <li>add non-LinkedBag tests of {@code difference()}, {@code intersection()}, 
 *     and {@code union()}
 *     <li>test 'standard' private methods: {@code getReferenceTo()} and
 *     {@code initializeState()}
 *     <li>in {@code populateBag()}, empty the bag before (re-)populating it
 *     </ul>
 */
@DisplayName( "LinkedBag" )
@TestInstance( Lifecycle.PER_CLASS )
@TestMethodOrder( OrderAnnotation.class )
class LinkedBagDMRTests extends JUnitTestingBase
    {
    // all state variables and support methods moved to end of class

    /**
     * Test method for {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#LinkedBag()}.
     * 
     * @param testInfo
     *     info about the test
     */
    @Test
    @DisplayName( "no-arg constructor" )
    @Order( 100100 )
    void testNoArgConstructor( final TestInfo testInfo )
        {
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // count this test
            this.currentTestsAttempted++ ;

            // display message describing this test
            writeLog( "[%,d, %,d] Testing: new LinkedBag()%n" + "\texpect: []%n",
                      this.currentTestGroup,
                      this.currentTestsAttempted ) ;

            // instantiate testBag
            final BagInterface<Object> testBag ;
            
            try
                {
                testBag = new LinkedBag<>() ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // empty?
            assertTrue( testBag.isEmpty() ) ;

            // no entries?"
            assertEquals( 0, testBag.getCurrentSize() ) ;

            // retrieve the contents of the test bag
            final Object[] testBagContents = getContentsOfChainBackedCollection( testBag ) ;

            // display message describing the actual result of this test
            writeLog( "\tactual: %s%n", arrayToString( testBagContents ) ) ;

            this.currentTestPassed = true ;
            } ) ;

        }	// end testNoArgConstructor()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#LinkedBag(java.lang.Object[])}.
     *
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param arrayContentsArgument
     *     contents for initialContents array
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-array-constructor.data",
                    numLinesToSkip = 1 )
    @DisplayName( "array constructor" )
    @Order( 100200 )
    void testArrayConstructor( final boolean isLastTest,
                               final boolean isStubBehavior,
                               final String arrayContentsArgument,
                               final TestInfo testInfo )
        {
        final Object[][] arrayContents = startTest( testInfo,
                                                    isLastTest,
                                                    isStubBehavior,
                                                    new String[] { "initialContents" },
                                                    arrayContentsArgument ) ;

        final Object[] initialContents = 
                arrayContentsArgument == null
                    ? null  // initialContents array is null
                    : // instantiate and populate initialContents array
                      Arrays.copyOf( arrayContents[ 0 ],
                                     arrayContents[ 0 ].length ) ;

        final boolean arrayContentsContainsNull =
                                        arrayContains( initialContents, null ) ;

        // display message describing the expected result of this test
        writeLog( "\texpect: %s%n",
                  arrayContents[ 0 ] == null
                      ? "[]"
                      : arrayContentsContainsNull
                          ? IllegalArgumentException.class.getSimpleName() +
                              ": \"entry cannot be null\""
                          : arrayToString( arrayContents[ 0 ] ) ) ;
        

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // instantiate testBag
            if ( arrayContentsContainsNull )
                {
                Exception thrownException = new PlaceholderException() ;

                try
                    {
                    new LinkedBag<>( initialContents ) ;
                    
                    // display message describing the actual result of this test
                    writeLog( "\tactual: no Exception thrown%n" ) ;
                    
                    fail() ;
                    }
                catch ( Exception e )
                    {
                    thrownException = e ;
                    
                    // display message describing the actual result of this test
                    writeLog( "\tactual: %s%s%n",
                              e.getClass().getSimpleName(),
                              e.getMessage() == null
                                  ? ""
                                  : ": \"" + e.getMessage() + "\"" ) ;
                    }
                
                assertEquals( IllegalArgumentException.class,
                              thrownException.getClass() ) ;
                }
            else
                {
                // instantiate testBag
                final BagInterface<Object> testBag ;
                
                try
                    {
                    testBag = new LinkedBag<>( initialContents ) ;
                    }
                catch ( Exception e )
                    {
                    writeLog( "\tactual: %s%s%n",
                              e.getClass().getSimpleName(),
                              e.getMessage() == null
                                  ? ""
                                  : ": " + e.getMessage() ) ;
                    
                    throw e ;   // re-throw it
                    }
    
                // empty?
                if ( arrayContents[ 0 ] == null )
                    {
                    assertTrue( testBag.isEmpty() ) ;
                    }
                else
                    {
                    assertEquals( arrayContents[ 0 ].length == 0, testBag.isEmpty() ) ;
                    }
    
                // correct number of entries?
                assertEquals( arrayContents[ 0 ] == null
                                ? 0
                                : arrayContents[ 0 ].length, 
                              testBag.getCurrentSize() ) ;
    
                // correct entries?
                final Object[] testBagContents = getContentsOfChainBackedCollection( testBag ) ;
    
                // display message describing the actual result of this test
                writeLog( "\tactual: %s%n", arrayToString( testBagContents ) ) ;
    
                // verify the test bag's contents
                compareArrays( arrayContents[ 0 ] == null
                                    ? new Object[ 0 ]
                                    : arrayContents[ 0 ], 
                               testBagContents, IS_UNORDERED ) ;
    
                // verify initialContents array's contents
                // - must be a non-destructive operation
    
                // correct entries?
                compareArrays( arrayContents[ 0 ], initialContents, IS_ORDERED ) ;
                }

            this.currentTestPassed = true ;
            } ) ;

        }   // end testArrayConstructor()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#LinkedBag(edu.wit.dcsn.comp2000.bag.adt.BagInterface)}
     * using a {@code LinkedBag} instance as the {@code sourceBag} argument.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param anotherBagContentsArgument
     *     contents for anotherBag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-cloning-constructor.data",
                    numLinesToSkip = 1 )
    @DisplayName( "cloning constructor with LinkedBag" )
    @Order( 100300 )
    void testCloningConstructorLinkedBag( final boolean isLastTest,
                                          final boolean isStubBehavior,
                                          final String anotherBagContentsArgument,
                                          final TestInfo testInfo )
        {
        final Object[][] anotherBagContents =
                                        startTest( testInfo,
                                                   isLastTest,
                                                   isStubBehavior,
                                                   new String[] { "sourceBag" },
                                                   anotherBagContentsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {

            // display message describing the expected result of this test
            writeLog( "\texpect: %s%n",
                      anotherBagContents[ 0 ] == null
                          ? "[]"
                          : arrayToString( anotherBagContents[ 0 ] ) ) ;

            BagInterface<Object> anotherBag = null ;
            if ( anotherBagContentsArgument == null )
                {
                // anotherBag is null
                }
            else
                {
                // instantiate and populate anotherBag
                anotherBag = new LinkedBag<>() ;

                populateBag( anotherBag, anotherBagContents[ 0 ] ) ;
                }

            // instantiate testBag
            final BagInterface<Object> testBag ;
            try
                {
                testBag = new LinkedBag<>( anotherBag ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // empty?
            if ( anotherBagContents[ 0 ] == null )
                {
                assertTrue( testBag.isEmpty() ) ;
                }
            else
                {
                assertEquals( anotherBagContents[ 0 ].length == 0,
                              testBag.isEmpty() ) ;
                }

            // correct number of entries?
            assertEquals( anotherBag == null
                            ? 0
                            : anotherBag.getCurrentSize(), 
                          testBag.getCurrentSize() ) ;

            // correct entries?
            final Object[] testBagContents = getContentsOfChainBackedCollection( testBag ) ;

            // display message describing the actual result of this test
            writeLog( "\tactual: %s%n", arrayToString( testBagContents ) ) ;

            // verify the test bag's contents
            if ( anotherBag == null )
                {
                assertEquals( 0, testBag.getCurrentSize() ) ;
                }
            else
                {
                compareArrays( anotherBagContents[ 0 ], testBagContents, IS_UNORDERED ) ;

                // verify another bag's contents
                // - must be a non-destructive operation

                // correct entries?
                final Object[] retrievedAnotherBagContents =
                                                getContentsOfChainBackedCollection( anotherBag ) ;

                compareArrays( anotherBagContents[ 0 ],
                               retrievedAnotherBagContents,
                               IS_UNORDERED ) ;
                }

            this.currentTestPassed = true ;
            } ) ;

        }	// end testCloningConstructorLinkedBag()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#LinkedBag(edu.wit.dcsn.comp2000.bag.adt.BagInterface)}
     * using a non-{@code LinkedBag} instance as the {@code sourceBag} argument.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param anotherBagContentsArgument
     *     contents for anotherBag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-cloning-constructor.data",
                    numLinesToSkip = 1 )
    @DisplayName( "cloning constructor with non-LinkedBag" )
    @Order( 100400 )
    void testCloningConstructorNonLinkedBag( final boolean isLastTest,
                                             final boolean isStubBehavior,
                                             final String anotherBagContentsArgument,
                                             final TestInfo testInfo )
        {
        final Object[][] anotherBagContents =
                                        startTest( testInfo,
                                                   isLastTest,
                                                   isStubBehavior,
                                                   new String[] { "sourceBag" },
                                                   anotherBagContentsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {

            // display message describing the expected result of this test
            writeLog( "\texpect: %s%n",
                      anotherBagContents[ 0 ] == null
                          ? "[]"
                          : arrayToString( anotherBagContents[ 0 ] ) ) ;

            BagInterface<Object> anotherBag = null ;
            if ( anotherBagContentsArgument == null )
                {
                // anotherBag is null
                }
            else
                {
                // instantiate and populate anotherBag
                anotherBag = new ResizableArrayBag<>() ;

                populateBag( anotherBag, anotherBagContents[ 0 ] ) ;
                }

            // instantiate testBag
            final BagInterface<Object> testBag ;
            
            try
                {
                testBag = new LinkedBag<>( anotherBag ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }
            
            // empty?
            if ( anotherBagContents[ 0 ] == null )
                {
                assertTrue( testBag.isEmpty() ) ;
                }
            else
                {
                assertEquals( anotherBagContents[ 0 ].length == 0,
                              testBag.isEmpty() ) ;
                }

            // correct number of entries?
            assertEquals( anotherBag == null
                ? 0
                : anotherBag.getCurrentSize(), testBag.getCurrentSize() ) ;

            // correct entries?
            final Object[] testBagContents = getContentsOfChainBackedCollection( testBag ) ;

            // display message describing the actual result of this test
            writeLog( "\tactual: %s%n", arrayToString( testBagContents ) ) ;

            // verify the test bag's contents
            compareArrays( anotherBag == null
                ? new Object[ 0 ]
                : anotherBagContents[ 0 ], testBagContents, IS_UNORDERED ) ;

            // verify another bag's contents
            // - must be a non-destructive operation

            // correct entries?
            final Object[] retrievedAnotherBagContents =
                                getContentsOfArrayBackedCollection( anotherBag,
                                                                    "bag" ) ;

            compareArrays( anotherBagContents[ 0 ],
                           retrievedAnotherBagContents,
                           IS_UNORDERED ) ;

            this.currentTestPassed = true ;
            } ) ;

        }	// end testCloningConstructorNonLinkedBag()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#add(java.lang.Object)}.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param bagContentsArgument
     *     contents to add to the bag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-add.data", numLinesToSkip = 1 )
    @DisplayName( "add()" )
    @Order( 400100 )
    void testAdd( final boolean isLastTest,
                  final boolean isStubBehavior,
                  final String bagContentsArgument,
                  final TestInfo testInfo )
        {
        final Object[][] bagContents = startTest( testInfo,
                                                  isLastTest,
                                                  isStubBehavior,
                                                  new String[] { "newEntry(s)" },
                                                  bagContentsArgument ) ;

        final boolean bagContentsContainsNull =
                                        arrayContains( bagContents[ 0 ], null ) ;

        // display message describing the expected result of this test
        writeLog( "\texpect: %s%n",
                  bagContents[ 0 ] == null
                      ? "[]"
                      : bagContentsContainsNull
                          ? IllegalArgumentException.class.getSimpleName() +
                              ": \"entry cannot be null\""
                          : arrayToString( bagContents[ 0 ] ) ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // instantiate testBag
            final BagInterface<Object> testBag = new LinkedBag<>() ;

            // populate it
            if ( bagContentsContainsNull )
                {
                Exception thrownException = new PlaceholderException() ;

                try
                    {
                    populateBag( testBag, bagContents[ 0 ] ) ;
                    
                    // display message describing the actual result of this test
                    writeLog( "\tactual: no Exception thrown%n" ) ;
                    
                    fail() ;
                    }
                catch ( Exception e )
                    {
                    thrownException = e ;
                    
                    // display message describing the actual result of this test
                    writeLog( "\tactual: %s%s%n",
                              e.getClass().getSimpleName(),
                              e.getMessage() == null
                                  ? ""
                                  : ": \"" + e.getMessage() + "\"" ) ;
                    }
                
                assertEquals( IllegalArgumentException.class,
                              thrownException.getClass() ) ;
                }
            else
                {
                try
                    {

                    try
                        {
                        populateBag( testBag, bagContents[ 0 ] ) ;
                        }
                    catch ( Exception e )
                        {
                        writeLog( "\tactual: %s%s%n",
                                  e.getClass().getSimpleName(),
                                  e.getMessage() == null
                                      ? ""
                                      : ": \"" + e.getMessage() + "\"" ) ;
                        
                        throw e ;   // re-throw it
                        }
                    }
                catch ( Exception e )
                    {
                    writeLog( "\tactual: %s%s%n",
                              e.getClass().getSimpleName(),
                              e.getMessage() == null
                                  ? ""
                                  : ": \"" + e.getMessage() + "\"" ) ;
                    
                    throw e ;   // re-throw it
                    }
    
                // correct number of entries?
                assertEquals( bagContents[ 0 ] == null
                                  ? 0
                                  : bagContents[ 0 ].length, 
                              testBag.getCurrentSize() ) ;
    
                // correct entries?
                final Object[] testBagContents = getContentsOfChainBackedCollection( testBag ) ;
    
                // display message describing the actual result of this test
                writeLog( "\tactual: %s%n", arrayToString( testBagContents ) ) ;
    
                // verify the bag's contents
                if ( bagContents[ 0 ] != null )
                    {
                    compareArrays( bagContents[ 0 ], testBagContents, IS_UNORDERED ) ;
                    }
                }

            this.currentTestPassed = true ;
            } ) ;

        }	// end testAdd()


    /**
     * Test method for {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#clear()}.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param bagContentsArgument
     *     contents to add to the bag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-clear.data", numLinesToSkip = 1 )
    @DisplayName( "clear()" )
    @Order( 200100 )
    void testClear( final boolean isLastTest,
                    final boolean isStubBehavior,
                    final String bagContentsArgument,
                    final TestInfo testInfo )
        {
        final Object[][] bagContents = startTest( testInfo,
                                                  isLastTest,
                                                  isStubBehavior,
                                                  new String[] { "initial contents" },
                                                  bagContentsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // display message describing the expected result of this test
            writeLog( "\texpect: %s%n", "[]" ) ;

            // instantiate testBag
            final BagInterface<Object> testBag = new LinkedBag<>() ;

            // populate it
            populateBag( testBag, bagContents[ 0 ] ) ;

            // correct number of entries?
            assertEquals( bagContents[ 0 ] == null
                            ? 0
                            : bagContents[ 0 ].length, testBag.getCurrentSize() ) ;

            // clear it
            try
                {
                testBag.clear() ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // empty?
            assertTrue( testBag.isEmpty() ) ;

            // make sure it is
            assertNull( testBag.remove() ) ;

            // display message describing the actual result of this test
            writeLog( "\tactual: %s%n", "[]" ) ;

            this.currentTestPassed = true ;
            } ) ;

        }	// end testClear()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#contains(java.lang.Object)}.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param bagContentsArgument
     *     contents to add to the bag
     * @param notContainedArgument
     *     items that aren't contained in the bag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-contains.data",
                    numLinesToSkip = 1 )
    @DisplayName( "contains()" )
    @Order( 200200 )
    void testContains( final boolean isLastTest,
                       final boolean isStubBehavior,
                       final String bagContentsArgument,
                       final String notContainedArgument,
                       final TestInfo testInfo )
        {
        final Object[][] contents = startTest( testInfo,
                                               isLastTest,
                                               isStubBehavior,
                                               new String[] { "initial contents", 
                                                              "anEntry(s)" },
                                               bagContentsArgument,
                                               notContainedArgument ) ;

        final Object searchFor = contents[ 1 ] == null
                                    ? null
                                    : contents[ 1 ].length == 0
                                        ? ""
                                        : contents[ 1 ][ 0 ] ;
        final boolean expectedResult = arrayContains( contents[ 0 ], searchFor ) ;

        // display message describing the expected result of this test
        writeLog( "\texpect: %b%n", expectedResult ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // instantiate testBag
            final BagInterface<Object> testBag = new LinkedBag<>() ;

            // populate it
            populateBag( testBag, contents[ 0 ] ) ;

            final boolean actualResult ;

            // test contains()
            try
                {
                actualResult = testBag.contains( searchFor ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: %b%n", actualResult ) ;

            // make sure we got the correct result
            assertEquals( expectedResult, actualResult ) ;

            // this operation is non-destructive so must be repeatable
            // - do it again to make sure...

            // repeat: test contains()
            final boolean verifyResult ;
            try
                {
                verifyResult = testBag.contains( searchFor ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tverify: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tverify: %b%n", verifyResult ) ;

            // repeat: check for the correct result
            assertEquals( expectedResult, verifyResult ) ;

            this.currentTestPassed = true ;
            } ) ;

        }	// end testContains()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#difference(edu.wit.dcsn.comp2000.bag.adt.BagInterface)}
     * using a {@code LinkedBag} instance as the {@code sourceBag} argument.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param testBagContentsArgument
     *     contents to add to the testBag
     * @param anotherBagContainsArgument
     *     contents to add to anotherBag
     * @param differenceBagContainsArgument
     *     contents expected in bag from testBag.difference(anotherBag)
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-difference.data",
                    numLinesToSkip = 1 )
    @DisplayName( "difference() with LinkedBag" )
    @Order( 500100 )
    void testDifferenceLinkedBag( final boolean isLastTest,
                                  final boolean isStubBehavior,
                                  final String testBagContentsArgument,
                                  final String anotherBagContainsArgument,
                                  final String differenceBagContainsArgument,
                                  final TestInfo testInfo )
        {
        final Object[][] contents = startTest( testInfo,
                                               isLastTest,
                                               isStubBehavior,
                                               new String[] { "initial contents", 
                                                              "anotherBag", 
                                                              "resultBag" },
                                               testBagContentsArgument,
                                               anotherBagContainsArgument,
                                               differenceBagContainsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // display message describing the expected result of this test
            writeLog( "\texpect: %s%n", arrayToString( contents[ 2 ] ) ) ;

            // instantiate testBag and populate it
            final BagInterface<Object> testBag = new LinkedBag<>() ;
            populateBag( testBag, contents[ 0 ] ) ;

            // instantiate anotherBag and populate it
            final BagInterface<Object> anotherBag ;
            if ( contents[ 1 ] == null )
                {
                anotherBag = null ;
                }
            else
                {
                anotherBag = new LinkedBag<>() ;
                populateBag( anotherBag, contents[ 1 ] ) ;
                }

            // calculate the difference
            final BagInterface<Object> resultBag ;
            
            try
                {
                resultBag = testBag.difference( anotherBag ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;

                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: %s%n", arrayToString( resultBag.toArray() ) ) ;

            // verify that the differenceBag's contents are correct
            compareArrays( contents[ 2 ], resultBag.toArray(), IS_UNORDERED ) ;

            // make sure testBag's contents are unchanged
            compareArrays( contents[ 0 ], testBag.toArray(), IS_UNORDERED ) ;

            // make sure anotherBag's contents are unchanged
            if ( anotherBag != null )
                {
                compareArrays( contents[ 1 ], anotherBag.toArray(), IS_UNORDERED ) ;
                }

            this.currentTestPassed = true ;
            } ) ;

        }	// end testDifferenceLinkedBag()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#difference(edu.wit.dcsn.comp2000.bag.adt.BagInterface)}
     * using a non-{@code LinkedBag} instance as the {@code sourceBag} argument.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param testBagContentsArgument
     *     contents to add to the testBag
     * @param anotherBagContainsArgument
     *     contents to add to anotherBag
     * @param differenceBagContainsArgument
     *     contents expected in bag from testBag.difference(anotherBag)
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-difference.data",
                    numLinesToSkip = 1 )
    @DisplayName( "difference() with non-LinkedBag" )
    @Order( 500100 )
    void testDifferenceNonLinkedBag( final boolean isLastTest,
                                     final boolean isStubBehavior,
                                     final String testBagContentsArgument,
                                     final String anotherBagContainsArgument,
                                     final String differenceBagContainsArgument,
                                     final TestInfo testInfo )
        {
        final Object[][] contents = startTest( testInfo,
                                               isLastTest,
                                               isStubBehavior,
                                               new String[] { "initial contents", 
                                                              "anotherBag", 
                                                              "resultBag" },
                                               testBagContentsArgument,
                                               anotherBagContainsArgument,
                                               differenceBagContainsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // display message describing the expected result of this test
            writeLog( "\texpect: %s%n", arrayToString( contents[ 2 ] ) ) ;

            // instantiate testBag and populate it
            final BagInterface<Object> testBag = new LinkedBag<>() ;
            populateBag( testBag, contents[ 0 ] ) ;

            // instantiate anotherBag and populate it
            final BagInterface<Object> anotherBag ;
            if ( contents[ 1 ] == null )
                {
                anotherBag = null ;
                }
            else
                {
                anotherBag = new ResizableArrayBag<>() ;
                populateBag( anotherBag, contents[ 1 ] ) ;
                }

            // calculate the difference
            final BagInterface<Object> resultBag ;
            
            try
                {
                resultBag = testBag.difference( anotherBag ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;

                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: %s%n", arrayToString( resultBag.toArray() ) ) ;

            // verify that the differenceBag's contents are correct
            compareArrays( contents[ 2 ], resultBag.toArray(), IS_UNORDERED ) ;

            // make sure testBag's contents are unchanged
            compareArrays( contents[ 0 ], testBag.toArray(), IS_UNORDERED ) ;

            // make sure anotherBag's contents are unchanged
            if ( anotherBag != null )
                {
                compareArrays( contents[ 1 ], anotherBag.toArray(), IS_UNORDERED ) ;
                }

            this.currentTestPassed = true ;
            } ) ;

        }	// end testDifferenceNonLinkedBag()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#getCurrentSize()}.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param bagContentsArgument
     *     contents to add to the bag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-get-current-size.data",
                    numLinesToSkip = 1 )
    @DisplayName( "getCurrentSize()" )
    @Order( 200300 )
    void testGetCurrentSize( final boolean isLastTest,
                             final boolean isStubBehavior,
                             final String bagContentsArgument,
                             final TestInfo testInfo )
        {
        final Object[][] bagContents = startTest( testInfo,
                                                  isLastTest,
                                                  isStubBehavior,
                                                  new String[] { "initial contents" },
                                                  bagContentsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // determine expected result
            final int expectedResult = bagContents[ 0 ].length ;

            // display message describing the expected result of this test
            writeLog( "\texpect: %,d%n", expectedResult ) ;

            // instantiate testBag
            final BagInterface<Object> testBag = new LinkedBag<>() ;

            // populate it
            populateBag( testBag, bagContents[ 0 ] ) ;

            // determine the number of entries in the bag
            int actualResult ;
            
            try
                {
                actualResult = testBag.getCurrentSize() ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: %,d%n", actualResult ) ;

            // check for the correct result
            assertEquals( expectedResult, actualResult ) ;

            // this operation is non-destructive so must be repeatable
            // - do it again to make sure...

            // repeat: determine the number of times the 'search for' entry appears
            // in the bag
            actualResult = testBag.getCurrentSize() ;

            // display message describing the actual result of this test
            writeLog( "\tverify: %,d%n", actualResult ) ;

            // repeat: check for the correct result
            assertEquals( expectedResult, actualResult ) ;

            this.currentTestPassed = true ;
            } ) ;

        }	// end testGetCurrentSize()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#getFrequencyOf(java.lang.Object)}.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param bagContentsArgument
     *     contents to add to the bag
     * @param searchForEntryArgument
     *     item for which we want its frequency in the bag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-get-frequency-of.data",
                    numLinesToSkip = 1 )
    @DisplayName( "getFrequencyOf()" )
    @Order( 200400 )
    void testGetFrequencyOf( final boolean isLastTest,
                             final boolean isStubBehavior,
                             final String bagContentsArgument,
                             final String searchForEntryArgument,
                             final TestInfo testInfo )
        {
        final Object[][] contents = startTest( testInfo,
                                               isLastTest,
                                               isStubBehavior,
                                               new String[] { "initial contents", 
                                                              "anEntry(s)" },
                                               bagContentsArgument,
                                               searchForEntryArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // determine number of times the 'search for' entry should
            // appear in the bag
            final Object searchFor = contents[ 1 ] == null
                                            ? null
                                            : contents[ 1 ].length == 0
                                                ? ""
                                                : contents[ 1 ][ 0 ] ;
            final int expectedResult = countOccurrences( contents[ 0 ], searchFor ) ;

            // display message describing the expected result of this test
            writeLog( "\texpect: %,d%n", expectedResult ) ;

            // instantiate testBag
            final BagInterface<Object> testBag = new LinkedBag<>() ;

            // populate it
            populateBag( testBag, contents[ 0 ] ) ;

            // determine the number of times the 'search for' entry appears
            // in the bag
            final int actualResult ;
            try
                {
                actualResult = testBag.getFrequencyOf( searchFor ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: %,d%n", actualResult ) ;

            // check for the correct frequency
            assertEquals( expectedResult, actualResult ) ;

            // this operation is non-destructive so must be repeatable
            // - do it again to make sure...

            // repeat: determine the number of times the 'search for' entry appears
            // in the bag
            final int verifyResult ;
            try
                {
                verifyResult = testBag.getFrequencyOf( searchFor ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tverify: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tverify: %,d%n", verifyResult ) ;

            // repeat: check for the correct frequency
            assertEquals( expectedResult, verifyResult ) ;

            this.currentTestPassed = true ;
            } ) ;

        }	// end testGetFrequencyOf()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#intersection(edu.wit.dcsn.comp2000.bag.adt.BagInterface)}
     * using a {@code LinkedBag} instance as the {@code sourceBag} argument.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param testBagContentsArgument
     *     contents to add to the testBag
     * @param anotherBagContainsArgument
     *     contents to add to anotherBag
     * @param intersectionBagContainsArgument
     *     contents expected in bag from testBag.intersection(anotherBag)
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-intersection.data",
                    numLinesToSkip = 1 )
    @DisplayName( "intersection() with LinkedBag" )
    @Order( 500300 )
    void testIntersectionLinkedBag( final boolean isLastTest,
                                    final boolean isStubBehavior,
                                    final String testBagContentsArgument,
                                    final String anotherBagContainsArgument,
                                    final String intersectionBagContainsArgument,
                                    final TestInfo testInfo )
        {
        final Object[][] contents = startTest( testInfo,
                                               isLastTest,
                                               isStubBehavior,
                                               new String[] { "initial contents", 
                                                              "anotherBag", 
                                                              "resultBag" },
                                               testBagContentsArgument,
                                               anotherBagContainsArgument,
                                               intersectionBagContainsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // display message describing the expected result of this test
            writeLog( "\texpect: %s%n", arrayToString( contents[ 2 ] ) ) ;

            // instantiate testBag and populate it
            final BagInterface<Object> testBag = new LinkedBag<>() ;
            populateBag( testBag, contents[ 0 ] ) ;

            // instantiate anotherBag and populate it
            final BagInterface<Object> anotherBag ;
            if ( contents[ 1 ] == null )
                {
                anotherBag = null ;
                }
            else
                {
                anotherBag = new LinkedBag<>() ;
                populateBag( anotherBag, contents[ 1 ] ) ;
                }

            // calculate the intersection
            final BagInterface<Object> resultBag ;
            
            try
                {
                resultBag = testBag.intersection( anotherBag ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;

                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: %s%n",
                      arrayToString( resultBag.toArray() ) ) ;

            // verify that the intersectionBag's contents are correct
            compareArrays( contents[ 2 ], resultBag.toArray(), IS_UNORDERED ) ;

            // make sure testBag's contents are unchanged
            compareArrays( contents[ 0 ], testBag.toArray(), IS_UNORDERED ) ;

            // make sure anotherBag's contents are unchanged
            if ( anotherBag != null )
                {
                compareArrays( contents[ 1 ], anotherBag.toArray(), IS_UNORDERED ) ;
                }

            this.currentTestPassed = true ;
            } ) ;

        }	// end testIntersectionLinkedBag()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#intersection(edu.wit.dcsn.comp2000.bag.adt.BagInterface)}
     * using a non-{@code LinkedBag} instance as the {@code sourceBag} argument.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param testBagContentsArgument
     *     contents to add to the testBag
     * @param anotherBagContainsArgument
     *     contents to add to anotherBag
     * @param intersectionBagContainsArgument
     *     contents expected in bag from testBag.intersection(anotherBag)
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-intersection.data",
                    numLinesToSkip = 1 )
    @DisplayName( "intersection() with non-LinkedBag" )
    @Order( 500300 )
    void testIntersectionNonLinkedBag( final boolean isLastTest,
                                       final boolean isStubBehavior,
                                       final String testBagContentsArgument,
                                       final String anotherBagContainsArgument,
                                       final String intersectionBagContainsArgument,
                                       final TestInfo testInfo )
        {
        final Object[][] contents = startTest( testInfo,
                                               isLastTest,
                                               isStubBehavior,
                                               new String[] { "initial contents", 
                                                              "anotherBag", 
                                                              "resultBag" },
                                               testBagContentsArgument,
                                               anotherBagContainsArgument,
                                               intersectionBagContainsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // display message describing the expected result of this test
            writeLog( "\texpect: %s%n", arrayToString( contents[ 2 ] ) ) ;

            // instantiate testBag and populate it
            final BagInterface<Object> testBag = new LinkedBag<>() ;
            populateBag( testBag, contents[ 0 ] ) ;

            // instantiate anotherBag and populate it
            final BagInterface<Object> anotherBag ;
            if ( contents[ 1 ] == null )
                {
                anotherBag = null ;
                }
            else
                {
                anotherBag = new ResizableArrayBag<>() ;
                populateBag( anotherBag, contents[ 1 ] ) ;
                }

            // calculate the intersection
            final BagInterface<Object> resultBag ;
            
            try
                {
                resultBag = testBag.intersection( anotherBag ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;

                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: %s%n",
                      arrayToString( resultBag.toArray() ) ) ;

            // verify that the intersectionBag's contents are correct
            compareArrays( contents[ 2 ], resultBag.toArray(), IS_UNORDERED ) ;

            // make sure testBag's contents are unchanged
            compareArrays( contents[ 0 ], testBag.toArray(), IS_UNORDERED ) ;

            // make sure anotherBag's contents are unchanged
            if ( anotherBag != null )
                {
                compareArrays( contents[ 1 ], anotherBag.toArray(), IS_UNORDERED ) ;
                }

            this.currentTestPassed = true ;
            } ) ;

        }	// end testIntersectionNonLinkedBag()


    /**
     * Test method for {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#isEmpty()}.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param bagContentsArgument
     *     contents to add to the bag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-is-empty.data",
                    numLinesToSkip = 1 )
    @DisplayName( "isEmpty()" )
    @Order( 200500 )
    void testIsEmpty( final boolean isLastTest,
                      final boolean isStubBehavior,
                      final String bagContentsArgument,
                      final TestInfo testInfo )
        {
        final Object[][] bagContents = startTest( testInfo,
                                                  isLastTest,
                                                  isStubBehavior,
                                                  new String[] { "initial contents" },
                                                  bagContentsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // determine expected result
            final boolean expectedResult = bagContents[ 0 ].length == 0 ;

            // display message describing the expected result of this test
            writeLog( "\texpect: %b%n", expectedResult ) ;

            // instantiate testBag
            final BagInterface<Object> testBag = new LinkedBag<>() ;

            // populate it
            populateBag( testBag, bagContents[ 0 ] ) ;

            // determine the actual result
            final boolean actualResult ;
            try
                {
                actualResult = testBag.isEmpty() ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: %b%n", actualResult ) ;

            // check for the correct result
            assertEquals( expectedResult, actualResult ) ;

            // this operation is non-destructive so must be repeatable
            // - do it again to make sure...

            // repeat: determine the actual result
            final boolean verifyResult ;
            try
                {
                verifyResult = testBag.isEmpty() ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tverify: %b%n", verifyResult ) ;

            // repeat: check for the correct result
            assertEquals( expectedResult, verifyResult ) ;

            this.currentTestPassed = true ;
            } ) ;

        }	// end testIsEmpty()


    /**
     * Test method for {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#remove()}.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param bagContentsArgument
     *     contents to add to the bag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-remove-unspecified.data",
                    numLinesToSkip = 1 )
    @DisplayName( "remove()" )
    @Order( 400200 )
    void testRemoveUnspecified( final boolean isLastTest,
                                final boolean isStubBehavior,
                                final String bagContentsArgument,
                                final TestInfo testInfo )
        {
        final Object[][] contents = startTest( testInfo,
                                               isLastTest,
                                               isStubBehavior,
                                               new String[] { "initial contents" },
                                               bagContentsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // instantiate testBag and populate it
            final BagInterface<Object> testBag = new LinkedBag<>() ;
            populateBag( testBag, contents[ 0 ] ) ;

            // remove every item from the bag
            final Object[] removedContents = new Object[ contents[ 0 ].length ] ;

            for ( int i = 0 ; i < contents[ 0 ].length ; i++ )
                {
                try
                    {
                    removedContents[ i ] = testBag.remove() ;
                    }
                catch ( Exception e )
                    {
                    writeLog( "\tactual: %s%s%n",
                              e.getClass().getSimpleName(),
                              e.getMessage() == null
                                  ? ""
                                  : ": \"" + e.getMessage() + "\"" ) ;
                    
                    throw e ;   // re-throw it
                    }
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: %s%n", arrayToString( removedContents ) ) ;

            // at this point the bag must be empty
            assertNull( testBag.remove() ) ;

            // make sure the items removed match the items added
            compareArrays( contents[ 0 ], removedContents, IS_UNORDERED ) ;

            this.currentTestPassed = true ;
            } ) ;

        }	// end testRemoveUnspecified() unspecified entry


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#remove(java.lang.Object)}.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param testBagContentsArgument
     *     contents to add to the testBag
     * @param testBagContainsArgument
     *     entries to remove successfully from testBag
     * @param testBagDoesNotContainsArgument
     *     entries we can't remove from testBag
     * @param testBagRemainderContentsArgument
     *     the expected entries remaining in testBag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-remove-specified.data",
                    numLinesToSkip = 1 )
    @DisplayName( "remove( anEntry )" )
    @Order( 400300 )
    void testRemoveSpecified( final boolean isLastTest,
                              final boolean isStubBehavior,
                              final String testBagContentsArgument,
                              final String testBagContainsArgument,
                              final String testBagDoesNotContainsArgument,
                              final String testBagRemainderContentsArgument,
                              final TestInfo testInfo )
        {
        final Object[][] contents = startTest( testInfo,
                                               isLastTest,
                                               isStubBehavior,
                                               new String[] { "initial contents", 
                                                              "removable anEntry(s)", 
                                                              "unremovable anEntry(s)", 
                                                              "result" },
                                               testBagContentsArgument,
                                               testBagContainsArgument,
                                               testBagDoesNotContainsArgument,
                                               testBagRemainderContentsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // instantiate testBag and populate it
            final BagInterface<Object> testBag = new LinkedBag<>() ;
            populateBag( testBag, contents[ 0 ] ) ;

            // display message describing the expected result of this test
            writeLog( "\texpect can be removed: %s%n",
                      arrayToString( contents[ 1 ] ) ) ;

            try
                {
                // remove entries the testBag contains
                for ( int i = 0 ; i < contents[ 1 ].length ; i++ )
                    {
                    assertTrue( testBag.remove( contents[ 1 ][ i ] ) ) ;
                    }
    
                // display message describing the expected result of this test
                writeLog( "\texpect cannot be removed: %s%n",
                          arrayToString( contents[ 2 ] ) ) ;
    
                // remove entries the testBag doesn't contain
                for ( int i = 0 ; i < contents[ 2 ].length ; i++ )
                    {
                    assertFalse( testBag.remove( contents[ 2 ][ i ] ) ) ;
                    }
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the expected result of this test
            writeLog( "\texpect what's left: %s%n",
                      arrayToString( contents[ 3 ] ) ) ;

            // display message describing the actual result of this test
            writeLog( "\tactual what's left: %s%n",
                      arrayToString( testBag.toArray() ) ) ;

            // verify that testBag contains the correct entries
            compareArrays( contents[ 3 ], testBag.toArray(), IS_UNORDERED ) ;

            this.currentTestPassed = true ;
            } ) ;

        }	// end testRemoveSpecified() specified entry


    /**
     * Test method for {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#toArray()}.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param bagContentsArgument
     *     contents to add to the bag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-to-array.data",
                    numLinesToSkip = 1 )
    @DisplayName( "toArray()" )
    @Order( 300100 )
    void testToArray( final boolean isLastTest,
                      final boolean isStubBehavior,
                      final String bagContentsArgument,
                      final TestInfo testInfo )
        {
        final Object[][] bagContents = startTest( testInfo,
                                                  isLastTest,
                                                  isStubBehavior,
                                                  new String[] { "initial contents" },
                                                  bagContentsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // display message describing the expected result of this test
            writeLog( "\texpect: %s%n", arrayToString( bagContents[ 0 ] ) ) ;

            // instantiate testBag
            final BagInterface<Object> testBag = new LinkedBag<>() ;

            // populate it
            populateBag( testBag, bagContents[ 0 ] ) ;

            // retrieve the contents of the test bag in an array
            final Object[] actualResult ;
            try
                {
                actualResult = testBag.toArray() ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: %s%n", arrayToString( actualResult ) ) ;

            // verify the bag's contents
            compareArrays( bagContents[ 0 ], actualResult, IS_UNORDERED ) ;

            // this operation is non-destructive so must be repeatable
            // - do it again to make sure...

            // retrieve the contents of the test bag in an array
            final Object[] verifyResult ;
            try
                {
                verifyResult = testBag.toArray() ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tverify: %s%n", arrayToString( verifyResult ) ) ;

            // verify the bag's contents
            compareArrays( bagContents[ 0 ], verifyResult, IS_UNORDERED ) ;

            this.currentTestPassed = true ;
            } ) ;

        }	// end testToArray()


    /**
     * Test method for {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#toString()}.
     *
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param bagContentsArgument
     *     contents to add to the bag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-to-array.data",
                    numLinesToSkip = 1 )
    @DisplayName( "toString()" )
    @Order( 300200 )
    void testToString( final boolean isLastTest,
                       final boolean isStubBehavior,
                       final String bagContentsArgument,
                       final TestInfo testInfo )
        {
        final Object[][] bagContents = startTest( testInfo,
                                                  isLastTest,
                                                  isStubBehavior,
                                                  new String[] { "initial contents" },
                                                  bagContentsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            Object[] expectedResult = new Object[ bagContents[ 0 ].length ] ;
            for ( int i = 0; i < expectedResult.length; i++ )
                {
                expectedResult[ i ] = bagContents[ 0 ][ expectedResult.length - i - 1 ] ;
                }
            final String expectedString = Arrays.toString( expectedResult ) ;
            
            // display message describing expected result of this test
            writeLog( "\texpect: \"%s\"%n", expectedString ) ;

            // instantiate testBag
            final BagInterface<Object> testBag = new LinkedBag<>() ;

            // populate it
            populateBag( testBag, bagContents[ 0 ] ) ;

            // retrieve the contents of the test bag in String form
            final String actualResult ;
            try
                {
                actualResult = testBag.toString() ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: \"%s\"%n", actualResult ) ;

            // verify the returned string
            assertEquals( expectedString, actualResult ) ;

            // this operation is non-destructive so must be repeatable
            // - do it again to make sure...

            // retrieve the contents of the test bag in String form
            final String verifyResult ;
            try
                {
                verifyResult = testBag.toString() ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tverify: \"%s\"%n", verifyResult ) ;

            // verify the returned string
            assertEquals( expectedString, verifyResult ) ;

            this.currentTestPassed = true ;
            } ) ;

        }	// end testToString()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#union(edu.wit.dcsn.comp2000.bag.adt.BagInterface)}
     * using a {@code LinkedBag} instance as the {@code sourceBag} argument.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param testBagContentsArgument
     *     contents to add to the testBag
     * @param anotherBagContainsArgument
     *     contents to add to anotherBag
     * @param unionBagContainsArgument
     *     contents expected in bag from testBag.union(anotherBag)
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-union.data", numLinesToSkip = 1 )
    @DisplayName( "union() with LinkedBag" )
    @Order( 500500 )
    void testUnionLinkedBag( final boolean isLastTest,
                             final boolean isStubBehavior,
                             final String testBagContentsArgument,
                             final String anotherBagContainsArgument,
                             final String unionBagContainsArgument,
                             final TestInfo testInfo )
        {
        final Object[][] contents = startTest( testInfo,
                                               isLastTest,
                                               isStubBehavior,
                                               new String[] { "initial contents", 
                                                              "anotherBag", 
                                                              "resultBag" },
                                               testBagContentsArgument,
                                               anotherBagContainsArgument,
                                               unionBagContainsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // display message describing the
            // expected result of this test
            writeLog( "\texpect: %s%n", arrayToString( contents[ 2 ] ) ) ;

            // instantiate testBag and populate it
            final BagInterface<Object> testBag = new LinkedBag<>() ;
            populateBag( testBag, contents[ 0 ] ) ;

            // instantiate anotherBag and populate it
            final BagInterface<Object> anotherBag ;
            if ( contents[ 1 ] == null )
                {
                anotherBag = null ;
                }
            else
                {
                anotherBag = new LinkedBag<>() ;
                populateBag( anotherBag, contents[ 1 ] ) ;
                }

            // calculate the union
            final BagInterface<Object> resultBag ;
            
            try
                {
                resultBag = testBag.union( anotherBag ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;

                throw e ;   // re-throw it
                }

            // display message describing the actual
            // result of this test
            writeLog( "\tactual: %s%n", arrayToString( resultBag.toArray() ) ) ;

            // verify that the intersectionBag's
            // contents are correct
            compareArrays( contents[ 2 ], resultBag.toArray(), IS_UNORDERED ) ;

            // make sure testBag's contents are
            // unchanged
            compareArrays( contents[ 0 ], testBag.toArray(), IS_UNORDERED ) ;

            // make sure anotherBag's contents are
            // unchanged
            if ( anotherBag != null )
                {
                compareArrays( contents[ 1 ], anotherBag.toArray(), IS_UNORDERED ) ;
                }

            this.currentTestPassed = true ;
            } ) ;

        }	// end testUnionLinkedBag()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#union(edu.wit.dcsn.comp2000.bag.adt.BagInterface)}
     * using a non-{@code LinkedBag} instance as the {@code sourceBag} argument.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param testBagContentsArgument
     *     contents to add to the testBag
     * @param anotherBagContainsArgument
     *     contents to add to anotherBag
     * @param unionBagContainsArgument
     *     contents expected in bag from testBag.union(anotherBag)
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-union.data", numLinesToSkip = 1 )
    @DisplayName( "union() with non-LinkedBag" )
    @Order( 500500 )
    void testUnionNonLinkedBag( final boolean isLastTest,
                                final boolean isStubBehavior,
                                final String testBagContentsArgument,
                                final String anotherBagContainsArgument,
                                final String unionBagContainsArgument,
                                final TestInfo testInfo )
        {
        final Object[][] contents = startTest( testInfo,
                                               isLastTest,
                                               isStubBehavior,
                                               new String[] { "initial contents", 
                                                              "anotherBag", 
                                                              "resultBag" },
                                               testBagContentsArgument,
                                               anotherBagContainsArgument,
                                               unionBagContainsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // display message describing the
            // expected result of this test
            writeLog( "\texpect: %s%n", arrayToString( contents[ 2 ] ) ) ;

            // instantiate testBag and populate it
            final BagInterface<Object> testBag = new LinkedBag<>() ;
            populateBag( testBag, contents[ 0 ] ) ;

            // instantiate anotherBag and populate it
            final BagInterface<Object> anotherBag ;
            if ( contents[ 1 ] == null )
                {
                anotherBag = null ;
                }
            else
                {
                anotherBag = new LinkedBag<>() ;
                populateBag( anotherBag, contents[ 1 ] ) ;
                }

            // calculate the union
            final BagInterface<Object> resultBag ;
            
            try
                {
                resultBag = testBag.union( anotherBag ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;

                throw e ;   // re-throw it
                }

            // display message describing the actual
            // result of this test
            writeLog( "\tactual: %s%n", arrayToString( resultBag.toArray() ) ) ;

            // verify that the intersectionBag's
            // contents are correct
            compareArrays( contents[ 2 ], resultBag.toArray(), IS_UNORDERED ) ;

            // make sure testBag's contents are
            // unchanged
            compareArrays( contents[ 0 ], testBag.toArray(), IS_UNORDERED ) ;

            // make sure anotherBag's contents are
            // unchanged
            if ( anotherBag != null )
                {
                compareArrays( contents[ 1 ], anotherBag.toArray(), IS_UNORDERED ) ;
                }

            this.currentTestPassed = true ;
            } ) ;

        }	// end testUnionNonLinkedBag()


    /**
     * Test method for {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#initializeState()}.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param bagContentsArgument
     *     contents to add to the bag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-initialize-state.data", numLinesToSkip = 1 )
    @DisplayName( "initializeState()" )
    @Order( 900100 )
    void testInitializeState( final boolean isLastTest,
                              final boolean isStubBehavior,
                              final String bagContentsArgument,
                              final TestInfo testInfo )
        {
        final Object[][] bagContents = startTest( testInfo,
                                                  isLastTest,
                                                  isStubBehavior,
                                                  new String[] { "initial contents" },
                                                  bagContentsArgument ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // display message describing the expected result of this test
            writeLog( "\texpect: %s%n", "[]" ) ;

            // instantiate testBag
            final BagInterface<Object> testBag = new LinkedBag<>() ;

            // populate it
            populateBag( testBag, bagContents[ 0 ] ) ;

            // correct number of entries?
            assertEquals( bagContents[ 0 ] == null
                            ? 0
                            : bagContents[ 0 ].length, testBag.getCurrentSize() ) ;

            // initialize its state
            try
                {
                invoke( LinkedBag.class, testBag, "initializeState", new Class<?>[] {}, new Object[] {} ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // empty?
            assertEquals( 0, getIntField( testBag, "numberOfEntries" ) ) ;
            assertNull( getReferenceField( testBag, "firstNode" ) ) ;
            
            assertTrue( testBag.isEmpty() ) ;

            // make sure it is
            assertNull( testBag.remove() ) ;

            // display message describing the actual result of this test
            writeLog( "\tactual: %s%n", "[]" ) ;

            this.currentTestPassed = true ;
            } ) ;

        }   // end testInitializeState()


    /**
     * Test method for
     * {@link edu.wit.dcsn.comp2000.bag.adt.LinkedBag#getReferenceTo(java.lang.Object)}.
     * 
     * @param isLastTest
     *     flag to indicate that this is the last dataset for this test
     * @param isStubBehavior
     *     flag to indicate that the result of testing this dataset matches the
     *     stubbed behavior
     * @param bagContentsArgument
     *     contents to add to the bag
     * @param itemContainedArgument
     *     items that aren't contained in the bag
     * @param testInfo
     *     info about the test
     */
    @ParameterizedTest( name = "{displayName}:: [{index}] {arguments}" )
    @CsvFileSource( resources = "./test-data-dmr/test-get-reference-to.data",
                    numLinesToSkip = 1 )
    @DisplayName( "getReferenceTo()" )
    @Order( 900200 )
    void testGetReferenceTo( final boolean isLastTest,
                             final boolean isStubBehavior,
                             final String bagContentsArgument,
                             final String itemContainedArgument,
                             final TestInfo testInfo )
        {
        final Object[][] contents = startTest( testInfo,
                                               isLastTest,
                                               isStubBehavior,
                                               new String[] { "initial contents", 
                                                              "anEntry" },
                                               bagContentsArgument,
                                               itemContainedArgument ) ;

        final Object searchFor = contents[ 1 ] == null
                                    ? null
                                    : contents[ 1 ].length == 0
                                        ? ""
                                        : contents[ 1 ][ 0 ] ;
        
        final boolean anEntryIsContained = searchFor == null
                                            ? false
                                            : arrayContains( contents[ 0 ], searchFor ) ;

        // display message describing the expected result of this test
        writeLog( "\texpect: %s: %b%n", "in bag", anEntryIsContained  ) ;

        // execute the test
        assertTimeoutPreemptively( testTimeLimit, () ->
            {
            // instantiate testBag
            final BagInterface<Object> testBag = new LinkedBag<>() ;

            // populate it
            populateBag( testBag, contents[ 0 ] ) ;

            Object foundNode = null ;
            Object foundData = null ;

            // test getReferenceTo()
            try
                {
                // get the node
                foundNode = invoke( LinkedBag.class,
                                    testBag,
                                    "getReferenceTo",
                                    new Class<?>[] { Object.class },
                                    new Object[] { searchFor } ) ;
                    
                // get its data
                if ( foundNode != null )
                    {
                    foundData = getReferenceField( foundNode, "data" ) ;
                    }
                }
            catch ( Exception e )
                {
                writeLog( "\tactual: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tactual: %s: %b%n", "in bag", foundNode != null ) ;
            
            // make sure the data from the referenced Node matches
            if ( foundNode != null )
                {
                assertEquals( searchFor, foundData ) ;
                }
            
            // find the node in the chain
            Object currentNode = getReferenceField( testBag, "firstNode" ) ;
            Object currentData = null ;
            while ( currentNode != null )
                {
                currentData = getReferenceField( currentNode, "data" ) ;
                if ( ( currentData != null ) &&
                     ( currentData.equals( searchFor ) ) )
                    {
                    break ;
                    }
                currentNode = getReferenceField( currentNode, "next" ) ;
                }

            // make sure we got the correct result
            // note: we are intentionally comparing instance references, not their contents
            writeLog( "\tverify: %s: %b%n",
                      "correct Node returned",
                      currentNode == foundNode ) ;
            assertTrue( currentNode == foundNode ) ;
            

            // this operation is non-destructive so must be repeatable
            // - do it again to make sure...

            // repeat: test contains()
            final Object verifyNode ;
            try
                {
                verifyNode = invoke( LinkedBag.class,
                                       testBag,
                                       "getReferenceTo",
                                       new Class<?>[] { Object.class },
                                       new Object[] { searchFor } ) ;
                }
            catch ( Exception e )
                {
                writeLog( "\tverify: %s%s%n",
                          e.getClass().getSimpleName(),
                          e.getMessage() == null
                              ? ""
                              : ": \"" + e.getMessage() + "\"" ) ;
                
                throw e ;   // re-throw it
                }

            // display message describing the actual result of this test
            writeLog( "\tverify: %s: %b%n",
                      "correct Node returned",
                      currentNode == verifyNode ) ;

            // repeat: check for the correct result
            assertTrue( currentNode == verifyNode ) ;

            this.currentTestPassed = true ;
            } ) ;

        }   // end testGetReferenceTo()


    // --------------------------------------------------
    //
    // The following utilities are used by the test methods
    //
    // --------------------------------------------------


    /**
     * Utility to populate a bag from the contents of an array
     * 
     * @param bagToFill
     *     the bag to populate
     * @param entries
     *     the entries to add to the bagToFill
     */
    private static void populateBag( final BagInterface<Object> bagToFill,
                                     final Object[] entries )
        {
        // make the bag empty
        if ( bagToFill instanceof LinkedBag )
            {
            setIntField( bagToFill, "numberOfEntries", 0 ) ;
            setReferenceField( bagToFill, "firstNode", null ) ;
            }
        else
            {
            bagToFill.clear() ;
            }
        
        // now fill it in order of the elements in the entries array
        if ( entries != null )
            {
            for ( final Object anEntry : entries )
                {
                bagToFill.add( anEntry ) ;
                }
            }

        }	// end populateBag()

    }	// end class LinkedBagDMRTests
