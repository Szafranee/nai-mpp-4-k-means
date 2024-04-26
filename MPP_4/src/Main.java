import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============ K - means classifier ============");
        System.out.print("Enter the file name: ");
        List<Point> points = FileHandler.getPointsListFromFile(scanner);
        System.out.print("Enter the number of clusters: ");
        int k = scanner.nextInt();
        System.out.println("===============================================");

        KMeans kMeans = new KMeans(k, points);
        kMeans.initializeClusters();

        System.out.println("=====================================");
        System.out.println("Initial centroids: ");
        for (Cluster cluster : kMeans.getClusters()) {
            System.out.println(cluster.getCentroid());
        }
        System.out.println("=====================================");

        kMeans.classify();

        if (kMeans.getPoints().getFirst().name != null) {
            System.out.println("==================================");
            for (Cluster cluster : kMeans.getClusters()) {
                Map<String, Integer> clusterNames = new HashMap<>();
                System.out.println("Cluster " + (kMeans.getClusters().indexOf(cluster) + 1) + ": ");
                for (Point point : cluster.getPoints()) {
                    if (clusterNames.containsKey(point.name)) {
                        clusterNames.put(point.name, clusterNames.get(point.name) + 1);
                    } else {
                        clusterNames.put(point.name, 1);
                    }
                }
                for (Map.Entry<String, Integer> entry : clusterNames.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println();
            }
            System.out.println("==================================");
        }
    }
}