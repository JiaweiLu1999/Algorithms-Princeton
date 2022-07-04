/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private List<Point> startPoints = new ArrayList<>();
    private List<Point> endPoints = new ArrayList<>();
    private List<Double> lineSlope = new ArrayList<>();
    private LineSegment[] res;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        check(points);
        Point[] points1 = Arrays.copyOf(points, points.length);
        Arrays.sort(points1);

        for (int i = 0; i < points1.length; i++) {
            Point[] pointsCopy = Arrays.copyOfRange(points1, i + 1, points1.length);
            Arrays.sort(pointsCopy, points1[i].slopeOrder());
            double slopeCur = 0.0, slopePrev = -1.1;
            int cnt = 0;
            for (int j = 0; j < pointsCopy.length; j++) {
                slopeCur = points1[i].slopeTo(pointsCopy[j]);
                if (slopeCur == slopePrev) {
                    cnt++;
                }
                else {
                    if (cnt >= 2 && isNew(pointsCopy[j - 1], slopePrev)) {
                        startPoints.add(points1[i]);
                        endPoints.add(pointsCopy[j - 1]);
                        lineSlope.add(slopePrev);
                    }
                    cnt = 0;
                }
                slopePrev = slopeCur;
            }
            if (cnt >= 2 && isNew(pointsCopy[pointsCopy.length - 1], slopeCur)) {
                startPoints.add(points1[i]);
                endPoints.add(pointsCopy[pointsCopy.length - 1]);
                lineSlope.add(slopeCur);
            }
        }

        res = new LineSegment[startPoints.size()];
        for (int i = 0; i < startPoints.size(); i++) {
            res[i] = new LineSegment(startPoints.get(i), endPoints.get(i));
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return res.length;
    }

    public LineSegment[] segments() {
        // the line segments
        return res;
    }

    private boolean isNew(Point p, double slope) {
        for (int i = 0; i < endPoints.size(); i++) {
            if (endPoints.get(i) == p && lineSlope.get(i) == slope) {
                return false;
            }
        }
        return true;
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
