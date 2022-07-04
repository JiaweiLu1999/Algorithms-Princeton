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
        check(points);
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        int len = pointsCopy.length;
        lineSeg = new ArrayList<>();
        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len - 2; j++) {
                for (int m = j + 1; m < len - 1; m++) {
                    for (int n = m + 1; n < len; n++) {
                        if (pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[j].slopeTo(
                                pointsCopy[m])
                                && pointsCopy[j].slopeTo(pointsCopy[m]) == pointsCopy[m].slopeTo(
                                pointsCopy[n])) {
                            lineSeg.add(new LineSegment(pointsCopy[i], pointsCopy[n]));
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

    private void check(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Duplicated entries in given points.");
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Null Pointer...");
            }
        }
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries in given points.");
                }
            }
        }
    }
}
