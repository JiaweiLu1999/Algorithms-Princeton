/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> lineSeg;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null) {
            throw new IllegalArgumentException("Null pointer points!");
        }

        checkDuplicatedEntries(points);
        lineSeg = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Null pointer points!");
            }
            Point[] pointsCopy = Arrays.copyOfRange(points, i + 1, points.length);
            Arrays.sort(pointsCopy, points[i].slopeOrder());
            double slopeCur, slopePrev = -1.1;
            int cnt = 0;
            for (int j = 0; j < pointsCopy.length; j++) {
                if (pointsCopy[j] == null) {
                    throw new IllegalArgumentException("Null pointer points!");
                }

                slopeCur = points[i].slopeTo(pointsCopy[j]);
                if (slopeCur == slopePrev) {
                    cnt++;
                }
                else {
                    if (cnt >= 3) {
                        lineSeg.add(new LineSegment(points[i], pointsCopy[j]));
                    }
                    cnt = 0;
                }
                slopePrev = slopeCur;
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
