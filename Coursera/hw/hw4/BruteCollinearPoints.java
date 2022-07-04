/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSeg;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null) {
            throw new IllegalArgumentException("Null pointer points!");
        }
        Arrays.sort(points);

        checkDuplicatedEntries(points);
        int len = points.length;
        lineSeg = new ArrayList<>();
        for (int i = 0; i < len - 3; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Null pointer!");
            }
            for (int j = i + 1; j < len - 2; j++) {
                if (points[j] == null) {
                    throw new IllegalArgumentException("Null pointer!");
                }
                for (int m = j + 1; m < len - 1; m++) {
                    if (points[m] == null) {
                        throw new IllegalArgumentException("Null pointer!");
                    }
                    for (int n = m + 1; n < len; n++) {
                        if (points[n] == null) {
                            throw new IllegalArgumentException("Null pointer!");
                        }
                        if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[m])
                                && points[j].slopeTo(points[m]) == points[m].slopeTo(points[n])) {
                            lineSeg.add(new LineSegment(points[i], points[n]));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSeg.size();
    }

    public LineSegment[] segments() {
        // the line segments
        return lineSeg.toArray(new LineSegment[0]);
    }

    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries in given points.");
                }
            }
        }
    }
}
