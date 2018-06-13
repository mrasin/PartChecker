package controller;

import files.JsonRead;
import model.Category;
import model.CategoryList;
import model.TestPart;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Menu {
    public void displayMenu() {
        InitialOperations initialOperations = new InitialOperations();
        List<TestPart> listOfTestParts = initialOperations.readDataFromCsv();

        Scanner scanner = new Scanner(System.in);

        String commands = "1 - Display imported data set\n" +
                "2 - check reported parts (DEMO)\n" +
                "q - Exit the program";

        System.out.println("*Check Part System 1.0*");
        System.out.println("What would you like to do? Please choose an option:\n" + commands);

        String decision;
        do {
                decision = scanner.nextLine();
                switch (decision) {
                case "1":
                    Map<String, Double> tmpDataMap = new HashMap<>();

                    for (int i = 0; i < listOfTestParts.size(); ++i) {
                        System.out.print(listOfTestParts.get(i).getName() + ": \t");
                        tmpDataMap = listOfTestParts.get(i).getPartData();
                        Set keys = tmpDataMap.keySet();
                        for (Object key : keys) {
                            System.out.print(key + " = " + tmpDataMap.get(key) + "\n\t\t\t");
                        }
                        System.out.println();
                    }
                    break;

                case "2":
                    JsonRead jsonReader = new JsonRead();

                    CategoryList listOfCategories;

                    try {
                        listOfCategories = jsonReader.readFromJson();
                    } catch (Exception e) {
                        System.out.println("Cannot read JSON");
                        e.printStackTrace();
                        listOfCategories = new CategoryList();
                    }
                    listOfCategories.show();

                    System.out.println();
                    System.out.print("Category to check: ");
                    String categoryToCheck = scanner.nextLine();

                    boolean categoryFound = false;
                    Category categoryData;
                    Set<String> outOfLimitSet = new HashSet();

                    for (int i = 0; i < listOfCategories.getListOfCategories().size(); i++) {
                        categoryData = listOfCategories.getListOfCategories().get(i);

                        if (categoryToCheck.equalsIgnoreCase(categoryData.getName())) {
                            categoryFound = true;

                            for (int j = 0; j < listOfTestParts.size(); j++) {
                                TestPart testPart = listOfTestParts.get(j);
                                boolean outOfLimit = testPart.ifOutOfLimit(categoryData);

                                if (outOfLimit) {
                                    outOfLimitSet.add(testPart.getName());
                                }
                            }
                        }
                    }
                    System.out.println("\nParts out of the limit for " + categoryToCheck +":");
                    for (String tmp : outOfLimitSet) {
                        System.out.println(tmp);
                    }



                    /*
                    String name = "cat001";
                    Set<String> points = new HashSet<>();
                    points.add("p1");
                    points.add("p2");
                    double minLimit = -0.01;
                    double maxLimit = 0.01;
                    boolean ifAverageToCheck = false;
                    double minAvg = 0;
                    double maxAvg = 0;


                    Category category = new Category(name, points, minLimit, maxLimit, ifAverageToCheck, minAvg, maxAvg);
                    System.out.println(category);

                    ArrayList<Category> list = new ArrayList<>();
                    list.add(category);

                    CategoryList listOfCategories = new CategoryList(list); // = partChecker.readCategories(); //reading from JSON
                    //listOfCategories.add(category);
                    listOfCategories.show();

                    try {
                        jsonReader.writeToJson(listOfCategories);
                    } catch (Exception e) {
                        System.out.println("cannot write to JSON");
                        e.printStackTrace();
                    }
                    */





                    /*
                    System.out.print("Choose category to be checked (q - quit): " );
                    String categoryToCheck = scanner.nextLine();
                    double lowerLimit;
                    double upperLimit;
                    boolean categoryFound = false;

                    Category categoryData;
                    Map<String, List<String>> outOfLimitMap = new HashMap<>();



                    while (!categoryToCheck.equalsIgnoreCase("q") && !categoryFound){
                        for (int i = 0; i < listOfCategories.getListOfCategories().size(); i++) {
                            categoryData = listOfCategories.getListOfCategories().get(i);
                            List<String> outOfLimitList = new ArrayList<>();

                            if (categoryToCheck == categoryData.getName()) {
                                categoryFound = true;
                                Set<String> points = categoryData.getPoints();
                                lowerLimit = categoryData.getMinLimit();
                                upperLimit = categoryData.getMaxLimit();

                                for (String tmpPoint : points) {

                                    for (int j = 0; j <listOfTestParts.size() ; j++) {
                                        TestPart currentPart = listOfTestParts.get(j);

                                        Map<String, Double> currentPartData = currentPart.getPartData();
                                        Set<String> keys = currentPartData.keySet();

                                        for (String key : keys) {
                                            if (key == tmpPoint){
                                                Double measurement = currentPartData.get(key);
                                                if (measurement < lowerLimit || measurement > upperLimit){
                                                    outOfLimitList.add(currentPart.getName());
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            outOfLimitMap.put(categoryData.getName(), outOfLimitList);

                        }
                    }
                    if (!categoryFound) {
                        System.out.println("Such a category does not exist!");
                    }
                    */

                    break;

                case "q":
                    System.out.println("Thank you for using *Check Part System 1.0*");
                    break;

                default:
                    System.out.println("Something went wrong, choose one of the options below:\n" + commands);
            }

        } while (!decision.equalsIgnoreCase("q"));
    }
}
