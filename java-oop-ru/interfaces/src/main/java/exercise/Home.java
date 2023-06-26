package exercise;

// BEGIN
public interface Home {
    double getArea();

    String toString();

    default int compareTo(Home otherHome) {
        return Double.compare(getArea(), otherHome.getArea());
    }

}
// END
