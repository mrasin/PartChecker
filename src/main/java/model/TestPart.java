package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestPart {

    private String name;
    private Map<String, Double> partData;

    private String categoryToCheck;
    //private Map<Map<String,List<Double>>,List<String>> limitValuesMap;
    //key: map containing category and list of lower and upper limit,
    //values: list of points

    public TestPart() {
    }

    public TestPart(String name, Map<String, Double> partData) {
        this.name = name;
        this.partData = partData;
    }

    public String getName() {
        return name;
    }

    public void checkNonConformance(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getPartData() {
        return partData;
    }

    public void setPartData(Map<String, Double> partData) {
        this.partData = partData;
    }

    public boolean ifOutOfLimit (Category category) {

        //boolean partOutOfLimit = false;
        Set<String> points = category.getPoints();
        double minLimit = category.getMinLimit();
        double maxLimit = category.getMaxLimit();

        Double belowLimit = null;
        Double aboveLimit = null;

        for (String tmp : points) {
            double measurement = partData.get(tmp);
            //System.out.println(measurement);

            if (measurement < minLimit) {
                if (belowLimit != null && measurement < belowLimit) {
                    belowLimit = measurement;
                } else if (belowLimit == null) {
                    belowLimit = measurement;
                }
            }

            /*
                if (measurement < belowLimit || belowLimit == null){
                    belowLimit = measurement;
                    System.out.println(belowLimit);
                }
            }
            */
            if (measurement > maxLimit) {
                if (aboveLimit != null && measurement > aboveLimit) {
                    aboveLimit = measurement;
                } else if (aboveLimit == null) {
                    aboveLimit = measurement;
                }
            }
            /*
            if (measurement > maxLimit) {
                if (measurement > maxLimit || aboveLimit == null){
                    aboveLimit = measurement;
                    System.out.println(aboveLimit);
                }
            }
            */
        }
        boolean partOutOfLimit;

        if (belowLimit != null || aboveLimit != null) {
            partOutOfLimit = true;
        } else {
            partOutOfLimit = false;
        }
        System.out.println(name + " out of the limit " + category.getName() + ": " + partOutOfLimit);
        return partOutOfLimit;
    }

    @Override
    public String toString(){
        String result = name + partData + "\n";
        return result;
    }
}
