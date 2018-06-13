package controller;

import model.TestPart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InitialOperations {

    public List<TestPart> readDataFromCsv() {

        List<List<String>> allDataList = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new File(
                            "c:/Users/Mateusz/Desktop/MyFirstJavaProject/myFirstProject/data05.csv"));

            while(scanner.hasNextLine()){
                String csvLine = scanner.nextLine();
                List<String> items = Arrays.asList(csvLine.split("\\s*;\\s*"));
                allDataList.add(items);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
        }

        //Creating List of TestPart Objects

        List<String> testPartNames = new ArrayList<>();

        for (int i = 0; i < allDataList.get(0).size(); ++i) {
            String data = allDataList.get(0).get(i);
            if (!data.equals("")) {
                testPartNames.add(data);
                //System.out.println(data + "; ");
            }
        }

        List<TestPart> listOfTestParts = new ArrayList<>();
        for (int i = 0; i < testPartNames.size(); ++i){
            TestPart part = new TestPart();
            Map<String,Double> partDataMap = new HashMap<>();
            int column = i + 1;

            for (int j = 1; j < allDataList.size(); ++j){
                String pointName = allDataList.get(j).get(0);
                Double pointMeasurement = Double.parseDouble(allDataList.get(j).get(column));
                partDataMap.put(pointName, pointMeasurement);
            }

            part.setName(testPartNames.get(i));
            part.setPartData(partDataMap);
            listOfTestParts.add(part);
        }


        /*
        try {
            FileWriter writer = new FileWriter(new File("output.txt"));
            for (int i = 0; i < listOfTestParts.size(); ++i){
                writer.write(listOfTestParts.get(i).getName() + "\n");

                Map<String, Double> tmpDataMap = new HashMap<>();
                tmpDataMap = listOfTestParts.get(i).getPartData();
                Set keys = tmpDataMap.keySet();
                for (Object key : keys){
                    writer.write(key + " = " + tmpDataMap.get(key) + "\n");
                }
                writer.write("\n");

            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Cannot write to output.txt file");
            e.printStackTrace();
        }
        */
        return listOfTestParts;
    }
}
