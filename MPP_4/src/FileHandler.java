import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public static File validateFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File does not exist. Please enter a valid file name: ");
            Scanner scanner = new Scanner(System.in);
            String newFileName = scanner.next();
            return validateFile(newFileName);
        }
        return file;
    }


    public static List<Point> readPointsFromFile(File file) {
        List<Point> Points = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] pointFromFile = line.split(",");
                try {
                    Double.parseDouble(pointFromFile[pointFromFile.length - 1]);
                    double[] coordinates = new double[pointFromFile.length];
                    for (int i = 0; i < pointFromFile.length; i++) {
                        coordinates[i] = Double.parseDouble(pointFromFile[i]);
                    }
                    Points.add(new Point(coordinates));
                } catch (NumberFormatException e) {
                    double[] coordinates = new double[pointFromFile.length - 1];
                    for (int i = 0; i < pointFromFile.length - 1; i++) {
                        coordinates[i] = Double.parseDouble(pointFromFile[i]);
                    }
                    String name = pointFromFile[pointFromFile.length - 1];
                    Points.add(new Point(coordinates, name));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return Points;
    }

    public static List<Point> getPointsListFromFile(Scanner scanner) {
        String fileName = scanner.next();
        File file = validateFile(fileName);
        return readPointsFromFile(file);
    }
}
