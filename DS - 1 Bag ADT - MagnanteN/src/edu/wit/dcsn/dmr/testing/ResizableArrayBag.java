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

import edu.wit.dcsn.comp2000.bag.adt.BagInterface ;

import java.util.Arrays ;

/**
 * A class that implements a bag of objects by using an array. The bag is never full.
 *
 * @author Frank M. Carrano, Timothy M. Henry
 * @version 5.0
 * 
 * @author David M Rosenberg
 * @version 5.1.0 2019-09-17
 *     <ul>
 *     <li>reformatted and minor changes
 *     <li>increased maximum capacity from 10,000 to 100,000 to support spell checker
 *     </ul>
 * @version 5.2.0 2020-01-26
 *     <ul>
 *     <li>increased maximum capacity to 250K
 *     <li>convert private method block comments to Javadoc
 *     </ul>
 * @version 5.3.0 2020-01-28 add stub difference(), intersection(), union() methods
 * @version 5.4.0 2020-05-14
 *     <ul>
 *     <li>change checkCapacity() to static
 *     <li>add missing comments
 *     </ul>
 * @version 5.5.0 2020-08-08 track changes to BagInterface - remove stubs for
 *     difference(), intersection(), and union() methods
 *     
 * @param <T>
 *     class of items the bag will hold
 */
public final class ResizableArrayBag<T>
                                    implements BagInterface<T>
    {

    private static final int DEFAULT_CAPACITY = 25 ; // Initial capacity of bag
    private static final int MAX_CAPACITY =     250_000 ;
    private T[] bag ;                       // Cannot be final due to resizing

    private int numberOfEntries ;
    private boolean integrityOK =               false ;


    /** Creates an empty bag whose initial capacity is 25. */
    public ResizableArrayBag()
        {
        this( DEFAULT_CAPACITY ) ;

        } // end no-arg constructor


    /**
     * Creates an empty bag having a given initial capacity.
     *
     * @param initialCapacity
     *     The integer capacity desired.
     */
    public ResizableArrayBag( final int initialCapacity )
        {
        checkCapacity( initialCapacity ) ;

        // The cast is safe because the new array contains null entries
        @SuppressWarnings( "unchecked" )
        final T[] tempBag =     (T[]) new Object[ initialCapacity ] ;

        this.bag =              tempBag ;
        this.numberOfEntries =  0 ;
        this.integrityOK =      true ;

        } // end 1-arg (initial size) constructor


    /**
     * Creates a bag containing given entries.
     *
     * @param contents
     *     An array of objects.
     */
    public ResizableArrayBag( final T[] contents )
        {
        checkCapacity( contents.length ) ;

        this.bag =              Arrays.copyOf( contents,
                                               contents.length ) ;
        this.numberOfEntries =  contents.length ;
        this.integrityOK =      true ;

        } // end 1-arg (source array) constructor


    /**
     * Adds a new entry to this bag.
     *
     * @param newEntry
     *     The object to be added as a new entry.
     * @return true
     */
    @Override
    public boolean add( final T newEntry )
        {
        checkIntegrity() ;

        if ( isArrayFull() )
            {
            doubleCapacity() ;
            } // end if

        this.bag[ this.numberOfEntries ] =  newEntry ;
        this.numberOfEntries++ ;

        return true ;

        } // end add()


    /** Removes all entries from this bag. */
    @Override
    public void clear()
        {
        while ( !isEmpty() )
            {
            remove() ;
            }

        } // end clear()


    /**
     * Tests whether this bag contains a given entry.
     *
     * @param anEntry
     *     The entry to locate.
     * @return true if this bag contains anEntry, or false otherwise.
     */
    @Override
    public boolean contains( final T anEntry )
        {
        checkIntegrity() ;

        return getIndexOf( anEntry ) > -1 ;         // or >= 0

        } // end contains()


    /**
     * Gets the current number of entries in this bag.
     *
     * @return The integer number of entries currently in this bag.
     */
    @Override
    public int getCurrentSize()
        {
        return this.numberOfEntries ;

        } // end getCurrentSize()


    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param anEntry
     *     The entry to be counted.
     * @return The number of times anEntry appears in this bag.
     */
    @Override
    public int getFrequencyOf( final T anEntry )
        {
        checkIntegrity() ;

        int counter =       0 ;

        for ( int index = 0 ; index < this.numberOfEntries ; index++ )
            {
            if ( anEntry.equals( this.bag[ index ] ) )
                {
                counter++ ;
                } // end if
            } // end for

        return counter ;

        } // end getFrequencyOf()


    /**
     * Sees whether this bag is empty.
     *
     * @return true if this bag is empty, or false if not.
     */
    @Override
    public boolean isEmpty()
        {
        return this.numberOfEntries == 0 ;

        } // end isEmpty()


    /**
     * Removes one unspecified entry from this bag, if possible.
     *
     * @return Either the removed entry, if the removal was successful, or null.
     */
    @Override
    public T remove()
        {
        checkIntegrity() ;

        final T result =    removeEntry( this.numberOfEntries - 1 ) ;
        
        return result ;

        } // end remove()


    /**
     * Removes one occurrence of a given entry from this bag.
     *
     * @param anEntry
     *     The entry to be removed.
     *     
     * @return true if the removal was successful, or false if not.
     */
    @Override
    public boolean remove( final T anEntry )
        {
        checkIntegrity() ;

        final int index =   getIndexOf( anEntry ) ;
        final T result =    removeEntry( index ) ;

        return anEntry.equals( result ) ;

        } // end remove()


    /**
     * Retrieves all entries that are in this bag.
     *
     * @return A newly allocated array of all the entries in this bag.
     */
    @Override
    public T[] toArray()
        {
        checkIntegrity() ;

        // The cast is safe because the new array contains null entries.
        @SuppressWarnings( "unchecked" )
        final T[] result =      (T[]) new Object[ this.numberOfEntries ] ;
                                                // Unchecked cast

        for ( int index = 0 ; index < this.numberOfEntries ; index++ )
            {
            result[ index ] =   this.bag[ index ] ;
            } // end for

        return result ;

        } // end toArray()


    /**
     * Throws an exception if the client requests a capacity that is too large.
     * 
     * @param capacity
     *     the client requested capacity
     */
    private static void checkCapacity( final int capacity )
        {
        if ( capacity > MAX_CAPACITY )
            {
            throw new IllegalStateException( "Attempt to create a bag whose " +
                                             "capacity exceeds allowed " +
                                             "maximum of " + MAX_CAPACITY ) ;
            }

        } // end checkCapacity()


    /**
     * Throws an exception if receiving object is not initialized.
     */
    private void checkIntegrity()
        {
        if ( !this.integrityOK )
            {
            throw new SecurityException( "ArrayBag object is corrupt." ) ;
            }

        } // end checkIntegrity()


    /**
     * Doubles the size of the array bag.
     * <p>
     * Precondition: checkInitialization has been called.
     */
    private void doubleCapacity()
        {
        final int newLength =   2 * this.bag.length ;

        checkCapacity( newLength ) ;

        this.bag =              Arrays.copyOf( this.bag,
                                               newLength ) ;

        } // end doubleCapacity()


    /**
     * Locates a given entry within the array bag.
     * <p>
     * Precondition: checkIntegrity has been called.
     * 
     * @param anEntry
     *     the entry to locate
     *     
     * @return the index of the entry, if located, or -1 otherwise
     */
    private int getIndexOf( final T anEntry )
        {
        int where =             -1 ;
        boolean found =         false ;
        int index =             0 ;

        while ( !found && ( index < this.numberOfEntries ) )
            {
            if ( anEntry.equals( this.bag[ index ] ) )
                {
                found =         true ;
                where =         index ;
                } // end if
            index++ ;
            } // end while

        // Assertion: If where > -1, anEntry is in the array bag, and it
        // equals bag[where]; otherwise, anEntry is not in the array.

        return where ;

        } // end getIndexOf()


    /**
     * Tests for an array full condition.
     * 
     * @return true if the array bag is full, or false if not
     */
    private boolean isArrayFull()
        {
        return this.numberOfEntries >= this.bag.length ;

        } // end isArrayFull()


    /**
     * Removes and returns the entry at a given index within the array.
     * <p>
     * Precondition: {@code 0 <= givenIndex < numberOfEntries}.<br>
     * Precondition: checkIntegrity has been called.
     * 
     * @param givenIndex
     *     the entry's position in the array
     * 
     * @return the entry or null if no such entry exists
     */
    private T removeEntry( final int givenIndex )
        {
        T result = null ;

        if ( !isEmpty() && ( givenIndex >= 0 ) )
            {
            result =                this.bag[ givenIndex ] ;  // Entry to remove
            final int lastIndex =   this.numberOfEntries - 1 ;
            this.bag[ givenIndex ] = this.bag[ lastIndex ] ;
                                                    // Replace entry to remove
            // with last entry
            this.bag[ lastIndex ] = null ;  // Remove reference to last entry
            this.numberOfEntries-- ;
            } // end if

        return result ;

        } // end removeEntry()

    } // end class ResizableArrayBag
