public class Point {
    double[] coordinates;
    String name;

    public Point(double[] coordinates, String name) {
        this.coordinates = coordinates;
        this.name = name;
    }

    public Point(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double distance(Point centroid) {
        double sum = 0;
        for (int i = 0; i < coordinates.length; i++) {
            sum += Math.pow(coordinates[i] - centroid.coordinates[i], 2);
        }
        return sum;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (double coordinate : coordinates) {
            sb.append(coordinate).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(")");
        return sb.toString();
    }
}
