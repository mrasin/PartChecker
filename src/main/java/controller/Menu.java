package controller;

import files.JsonRead;
import model.Category;
import model.CategoryAndPart;
import model.CategoryList;
import model.TestPart;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Menu {
    public void displayMenu() {
        InitialOperations initialOperations = new InitialOperations();
        List<TestPart> listOfTestParts = initialOperations.readDataFromCsv();

        CategoryList listOfCategories;

        JsonRead jsonReader = new JsonRead();

        try {
            listOfCategories = jsonReader.readFromJson();
        } catch (Exception e) {
            System.out.println("Cannot read categories from JSON");
            e.printStackTrace();
            listOfCategories = new CategoryList();
        }
        //listOfCategories.show();

        Scanner scanner = new Scanner(System.in);

        String commands = "1 - Display imported data set\n" +
                "2 - check reported parts\n" +
                "3 - print out of the limit parts\n" +
                "q - Exit the program";

        System.out.println("*Check Part System 1.0*");
        System.out.println(commands);

        SortedSet<CategoryAndPart> outOfLimitSet = new TreeSet();

        String decision;
        do {
                System.out.print("Please choose an option: ");
                decision = scanner.nextLine();
                switch (decision) {
                case "1": {
                    Map<String, Double> tmpDataMap = new HashMap<>();

                    for (int i = 0; i < listOfTestParts.size(); ++i) {
                        System.out.print(listOfTestParts.get(i).getName() + ": \t");

                        tmpDataMap = listOfTestParts.get(i).getPartData();
                        SortedSet<String> keys = new TreeSet<>(tmpDataMap.keySet()); //printing alphabetically according to points (keys)

                        for (Object key : keys) {
                            System.out.print(key + " = " + tmpDataMap.get(key) + "\n\t\t\t");
                        }
                        System.out.println();
                    }
                }
                    break;

                case "2": {
                    String categoryToCheck;
                    do {
                        System.out.println();
                        System.out.print("Category to check (or q - quit):");
                        categoryToCheck = scanner.nextLine();

                        boolean categoryFound = false;
                        Category categoryData;

                        if(!categoryToCheck.equalsIgnoreCase("q")){

                            for (int i = 0; i < listOfCategories.getListOfCategories().size(); i++) {
                                categoryData = listOfCategories.getListOfCategories().get(i);

                                if (categoryToCheck.equalsIgnoreCase(categoryData.getName())) {
                                    categoryFound = true;

                                    for (int j = 0; j < listOfTestParts.size(); j++) {
                                        TestPart testPart = listOfTestParts.get(j);
                                        boolean outOfLimit = testPart.ifOutOfLimit(categoryData);

                                        if (outOfLimit) {
                                            CategoryAndPart categoryAndPart = new CategoryAndPart();

                                            //List catAndPartName = new ArrayList();
                                            categoryAndPart.setCategory(categoryData.getName());
                                            categoryAndPart.setPart(testPart.getName());
                                            outOfLimitSet.add(categoryAndPart);
                                            //catAndPartName.add(categoryData.getName());
                                            //catAndPartName.add(testPart.getName());
                                            //outOfLimitSet.add(catAndPartName);
                                        }
                                    }
                                }
                            }
                            System.out.println("\nParts out of the limit for " + categoryToCheck + ":");
                            for (CategoryAndPart tmp : outOfLimitSet) {
                                if (tmp.getCategory().equals(categoryToCheck)) {
                                    System.out.println(tmp);
                                }

                            }
                        }
                    }

                         while (!categoryToCheck.equalsIgnoreCase("q"));
                    }


                    break;

                case "3": {

                    //for (CategoryAndPart tmp : outOfLimitSet) {
                    //    System.out.println(tmp);
                    //}

                    System.out.println("Would you like to print out of limit parts to .csv file? (Y / N)");
                    decision = scanner.nextLine();
                    String csvString = "Category;Part;Point;Measurement\n";
                    if (decision.equalsIgnoreCase("y")) {

                        for (CategoryAndPart tmp : outOfLimitSet) {
                            String categoryName = tmp.getCategory();
                            String partName = tmp.getPart();

                            boolean categoryFound = false;
                            Category category = new Category();

                            for (int i = 0; i < listOfCategories.getListOfCategories().size() && !categoryFound; ++i) {
                                if (listOfCategories.getListOfCategories().get(i).getName().equals(categoryName)) {
                                    category = listOfCategories.getListOfCategories().get(i);
                                    categoryFound = true;
                                }
                            }

                            SortedSet points = new TreeSet<>(category.getPoints()); //printing points alphabetically

                            boolean partFound = false;
                            for (int i = 0; i < listOfTestParts.size() && !partFound; ++i) {
                                if (listOfTestParts.get(i).getName().equals(partName)) {
                                    TestPart testPart = listOfTestParts.get(i);
                                    Map<String, Double> partData = testPart.getPartData();

                                    for (Object tmPoint : points) {
                                        String tmPointString = tmPoint.toString();
                                        Double tmpData = partData.get(tmPointString);

                                        csvString += categoryName + ";" + partName + ";" + tmPointString + ";" + tmpData + "\n";


                                    }
                                    partFound = true;
                                }
                            }
                        }
                    }
                    //System.out.println(csvString);
                    try {
                        initialOperations.writeDataToCsv(csvString);

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Cannot save to .csv file");
                    }
                }
                    break;



                case "q": {
                    System.out.println("Thank you for using *Check Part System 1.0*");
                }
                    break;

                default:
                    System.out.println("Something went wrong, choose one of the options below:\n" + commands);
            }

        } while (!decision.equalsIgnoreCase("q"));
    }
}
