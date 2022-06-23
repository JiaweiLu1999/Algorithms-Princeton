/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int cnt = 0;
        String res = null;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            cnt++;
            if (StdRandom.bernoulli(1.0/cnt)) {
                res = word;
            }
        }
        System.out.println(res);
    }
}
