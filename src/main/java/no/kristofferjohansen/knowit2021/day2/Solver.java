package no.kristofferjohansen.knowit2021.day2;

import no.kristofferjohansen.knowit2021.common.FileUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solver {

    public Solver() {
        try {
            Date start = new Date();
            List<String> dataLines = FileUtil.readInputFileGeneric(this.getClass(), "cities.csv");
            String[][] input = new String[dataLines.size()-1][2];
            for (int i = 1; i < dataLines.size(); i++) {
                input[i-1] = dataLines.get(i).split(",(?=P)");
            }

            System.out.println(solve(input, false));
            System.out.printf("Regular solution took %d ms\n", new Date().getTime()-start.getTime());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static int solve(String[][] data, boolean logMovement) {
        List<City> cities = Arrays.stream(data).map(a -> new City(a[0], a[1])).collect(Collectors.toList());
        City northPole = new City("North Pole", "Point(0.0 90.0)");
        City currentCity = northPole;
        City nearestCity;
        double shortestDistance;
        double travelledDistance = 0;
        while (!cities.isEmpty()) {
            shortestDistance = Double.MAX_VALUE;
            nearestCity = null;
            for (City city : cities) {
                double distance = currentCity.getDistance(city);
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    nearestCity = city;
                }
            }

            currentCity = nearestCity;
            cities.remove(nearestCity);
            travelledDistance += shortestDistance;
            if (logMovement) System.out.printf("Moving %f km to %s\n", shortestDistance, currentCity.getName());
        }

        double distanceHome = currentCity.getDistance(northPole);
        if (logMovement) System.out.printf("Moving %f km to %s\n", distanceHome, northPole.getName());

        travelledDistance += distanceHome;

        return (int) Math.round(travelledDistance);
    }

    public static void main(String[] args) {
        new Solver();
    }
}

class City {
    static Pattern pointPattern = Pattern.compile("Point\\((-?\\d+\\.\\d+) (-?\\d+\\.\\d+)\\)");
    static double radius = 6371;

    private final String name;
    private final double lat;
    private final double lon;

    public City(String name, String pointData) {
        this.name = name;

        try {
            Matcher matcher = pointPattern.matcher(pointData);
            matcher.find();
            this.lon = Double.parseDouble(matcher.group(1));
            this.lat = Double.parseDouble(matcher.group(2));
        } catch (Exception exc) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getDistance(City other) {
        double diffLong = Math.toRadians(other.getLon() - this.getLon());
        double diffLat = Math.toRadians(other.getLat() - this.getLat());

        double a = Math.sin(diffLat/2) * Math.sin(diffLat/2)
                + Math.cos(Math.toRadians(this.getLat())) * Math.cos(Math.toRadians(other.getLat()))
                * Math.sin(diffLong/2) * Math.sin(diffLong/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return radius * c;
    }



    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", x=" + lat +
                ", y=" + lon +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
