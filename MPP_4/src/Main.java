import java.util.List;
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
    }
}