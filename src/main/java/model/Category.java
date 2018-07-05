package model;

import java.util.Set;

public class Category {
    private String name;
    private Set<String> points;
    private double minLimit;
    private double maxLimit;
    private boolean ifAverageToCheck;
    private double minAvg;
    private double maxAvg;

    public Category() {
    }

    public Category(String name, Set<String> points, double minLimit, double maxLimit, boolean ifAverageToCheck, double minAvg, double maxAvg) {
        this.name = name;
        this.points = points;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        this.ifAverageToCheck = ifAverageToCheck;
        this.minAvg = minAvg;
        this.maxAvg = maxAvg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPoints() {
        return points;
    }

    public void setPoints(Set<String> points) {
        this.points = points;
    }

    public double getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(double minLimit) {
        this.minLimit = minLimit;
    }

    public double getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(double maxLimit) {
        this.maxLimit = maxLimit;
    }

    public boolean isIfAverageToCheck() {
        return ifAverageToCheck;
    }

    public void setIfAverageToCheck(boolean ifAverageToCheck) {
        this.ifAverageToCheck = ifAverageToCheck;
    }

    public double getMinAvg() {
        return minAvg;
    }

    public void setMinAvg(double minAvg) {
        this.minAvg = minAvg;
    }

    public double getMaxAvg() {
        return maxAvg;
    }

    public void setMaxAvg(double maxAvg) {
        this.maxAvg = maxAvg;
    }



    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", minLimit=" + minLimit +
                ", maxLimit=" + maxLimit +
                ", ifAverageToCheck=" + ifAverageToCheck +
                ", minAvg=" + minAvg +
                ", maxAvg=" + maxAvg +
                '}';
    }
}
