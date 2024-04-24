
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
    private final int k;
    private final List<Point> points;
    private final List<Cluster> clusters;

    public KMeans(int k, List<Point> points) {
        this.k = k;
        this.points = points;
        clusters = new ArrayList<>();
    }

    public void initializeClusters() {
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            int randomIndex = random.nextInt(points.size());
            clusters.add(new Cluster(points.get(randomIndex)));
        }
    }

    public void classify() {
        boolean centroidsMoved = true;
        int iteration = 0;
        while (centroidsMoved) {
            clearClusters();
            List<Point> oldCentroids = getCentroids();
            clusterPoints();
            recalculateCentroids();
            iteration++;
            System.out.println("Iteration " + iteration + ":");
            System.out.println("Total square distance within clusters: ");
            for (Cluster cluster : clusters) {
                System.out.println("Cluster " + (clusters.indexOf(cluster) + 1) + ": " + cluster.calculateSquareDistance());
            }
            System.out.println();
            centroidsMoved = !hasConverged(oldCentroids, getCentroids());
        }

        System.out.println("\n==================================");
        System.out.println("Final clusters:");
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println();
            System.out.println("Cluster " + (i) + ":\n");
            System.out.println("Points: " + clusters.get(i).getPoints().size());
            System.out.println(clusters.get(i));
            System.out.println("Centroid: " + clusters.get(i).getCentroid() +"\n");
        }

        System.out.println("==================================");
    }

    private void clusterPoints() {
        for (Point Point : points) {
            Cluster closestCluster = null;
            double minDistance = Double.MAX_VALUE;
            for (Cluster cluster : clusters) {
                double distance = Point.distance(cluster.getCentroid());
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCluster = cluster;
                }
            }
            closestCluster.addPoint(Point);
        }
    }

    private boolean hasConverged(List<Point> oldCentroids, List<Point> newCentroids) {
        if (oldCentroids.size() != newCentroids.size()) {
            return false;
        }
        for (int i = 0; i < oldCentroids.size(); i++) {
            if (oldCentroids.get(i).distance(newCentroids.get(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    private void clearClusters() {
        for (Cluster cluster : clusters) {
            cluster.clearPoints();
        }
    }

    private List<Point> getCentroids() {
        List<Point> centroids = new ArrayList<>();
        for (Cluster cluster : clusters) {
            centroids.add(cluster.getCentroid());
        }
        return centroids;
    }

    private void recalculateCentroids() {
        for (Cluster cluster : clusters) {
            cluster.updateCentroid();
        }
    }


    public List<Cluster> getClusters() {
        return clusters;
    }

}