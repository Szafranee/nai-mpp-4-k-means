import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private Point centroid;
    private final List<Point> points;

    public Cluster(Point centroid) {
        this.centroid = centroid;
        points = new ArrayList<>();
    }

    public void updateCentroid() {
        if (points.isEmpty()) {
            return;
        }
        double[] newCoordinates = new double[centroid.getCoordinates().length];
        for (Point point : points) {
            for (int i = 0; i < newCoordinates.length; i++) {
                newCoordinates[i] += point.getCoordinates()[i];
            }
        }
        for (int i = 0; i < newCoordinates.length; i++) {
            newCoordinates[i] /= points.size();
        }
        centroid = new Point(newCoordinates);
    }

    public double calculateSquareDistance() {
        double totalSquareDistance = 0;
        for (Point point : points) {
            totalSquareDistance += point.distance(centroid);
        }
        return totalSquareDistance;
    }

    public void clearPoints() {
        points.clear();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public Point getCentroid() {
        return centroid;
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point point : points) {
            sb.append(point).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
