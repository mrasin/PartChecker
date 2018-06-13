package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import files.JsonRead;
import model.Category;
import model.CategoryList;
import model.TestPart;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PartChecker {

    public CategoryList readCategories() {

        JsonRead jsonReader = new JsonRead();
        CategoryList listOfCategories;

        try {
            listOfCategories = jsonReader.readFromJson();

        } catch (Exception e) {
            System.out.println("JSON reading error");
            e.printStackTrace();
            listOfCategories = new CategoryList();
        }
        /*
        for (int i = 0; i < listOfCategories.getListOfCategories().size(); i++) {
            System.out.println(listOfCategories.getListOfCategories().get(i));
        }
        */

        return listOfCategories;

    }

        /*
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        String point1 = scanner.nextLine();
        String point2 = scanner.nextLine();
        double minLimit = scanner.nextDouble();
        scanner.nextLine();
        double maxLimit = scanner.nextDouble();
        scanner.nextLine();
        boolean ifAverageToCheck = scanner.nextBoolean();

        Category category1 = new Category();
        category1.setName(name);
        Set<String> points = new HashSet<>();

        points.add(point1);
        points.add(point2);
        category1.setPoints(points);

        category1.setMinLimit(minLimit);
        category1.setMaxLimit(maxLimit);
        category1.setIfAverageToCheck(ifAverageToCheck);

        listOfCategories.add(category1);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper
                    .writerWithDefaultPrettyPrinter() //zapisanie JSONa w postaci wierszowej
                    .writeValue(new File("categories_test.json"), listOfCategories);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            listOfCategories = mapper.readValue(new File("categories.json"),
                    new TypeReference<List<Category>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < listOfCategories.size(); ++i){
            //System.out.println(listOfCategories.get(i));
        }

        Map<String, String> pointToCatMap = new HashMap<>();

        for (int i = 0; i < listOfCategories.size(); ++i) {
            System.out.println(listOfCategories.get(i));
            String categoryName = listOfCategories.get(i).getName();
            Set<String> pointSet = listOfCategories.get(i).getPoints();

            for (String tmp : pointSet) {
                pointToCatMap.put(tmp, categoryName);
            }
        }

        Set<String> keySet = pointToCatMap.keySet();
        for (String key : keySet) {
            System.out.println(key + " " + pointToCatMap.get(key));
        }
    */

}
