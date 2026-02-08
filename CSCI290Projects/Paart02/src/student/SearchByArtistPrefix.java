/**
 * File: SearchByArtistPrefix.java 
 *****************************************************************************
 *                       Revision History
 *****************************************************************************
 * 
 * 8/2015 Anne Applin - Added formatting and JavaDoc 
 * 2015 - Bob Boothe - starting code  
 *****************************************************************************

 */

package student;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
/**
 * Search by Artist Prefix searches the artists in the song database 
 * for artists that begin with the input String
 * @author Bob Booth 
 */

public class SearchByArtistPrefix {
    // keep a local direct reference to the song array
    private Song[] songs;  
    
    private int lcc = 0; //////task4

    /**
     * constructor initializes the property. [Done]
     * @param sc a SongCollection object
     */
    public SearchByArtistPrefix(SongCollection sc) {
        songs = sc.getAllSongs();
    }

    /**
     * find all songs matching artist prefix uses binary search should operate
     * in time log n + k (# matches)
     * converts artistPrefix to lowercase and creates a Song object with 
     * artist prefix as the artist in order to have a Song to compare.
     * walks back to find the first "beginsWith" match, then walks forward
     * adding to the arrayList until it finds the last match.
     *
     * @param artistPrefix all or part of the artist's name
     * @return an array of songs by artists with substrings that match 
     *    the prefix
     */
    public Song[] search(String artistPrefix) {
        Song.CmpArtist.resetCount();
        lcc = 0;
        
        

        
        ArrayList<Song> matches = new ArrayList<>();    ////task2 pt 2
        Song.CmpArtist cmp = new  Song.CmpArtist();
        
        Song ky = new Song(artistPrefix,"","");
        int id = Arrays.binarySearch(songs, ky, cmp);
        
        if (id < 0) {
            id = -id - 1;
    }
        
        int i = id;
        while (i > 0 &&
               songs[i - 1].getArtist()
               .toLowerCase()
               .startsWith(artistPrefix.toLowerCase())) {
            
            lcc++;              //task 4
            i--;
        }

        
       while (i < songs.length &&
               songs[i].getArtist()
                .toLowerCase()
               .startsWith(artistPrefix.toLowerCase())) {           ///task2 pt2
            lcc++;
            matches.add(songs[i]);
            i++;
       
       }
       
       return matches.toArray(new Song[0]);
    }
    /** 
     * 
     * testing method for this unit
     * @param args  command line arguments set in Project Properties - 
     * the first argument is the data file name and the second is the partial 
     * artist name, e.g. be which should return beatles, beach boys, bee gees,
     * etc.
     */
    public static void main(String[] args) {
    if (args.length == 0) {
        System.err.println("usage: prog songfile [search string]");
        return;                                          ///// Task 2 pt2
        
    }
    SongCollection sc = new SongCollection(args[0]);

    SearchByArtistPrefix sbap = new SearchByArtistPrefix(sc);

    if (args.length > 1) {
        System.out.println("searching for: " + args[1]);
        
        Song[] byArtistResult = sbap.search(args[1]);

        System.out.println("total matches: " + byArtistResult.length);
            Stream.of(byArtistResult).limit(10).forEach(System.out::println);
        
            
           System.out.println("binary search comparisons: "
                + Song.CmpArtist.getCount());
           
           System.out.println("linear scan comparisons: " + 
                   sbap.lcc);

        
    }
}
}
