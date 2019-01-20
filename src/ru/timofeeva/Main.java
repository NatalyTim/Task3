package ru.timofeeva;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        if (args == null || args.length < 5) {
            System.out.println("Please add the path to files, when calling the program...");
            return;
        }
        String filePath1 = args[0];
        String filePath2 = args[1];
        String filePath3 = args[2];
        String filePath4 = args[3];
        String filePath5 = args[4];


        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        File file3 = new File(filePath3);
        File file4 = new File(filePath4);
        File file5 = new File(filePath5);



        List<Double> cachbox1 = getList(file1);
        List<Double> cachbox2 = getList(file2);
        List<Double> cachbox3 = getList(file3);
        List<Double> cachbox4 = getList(file4);
        List<Double> cachbox5 = getList(file5);
        int maxLength = Math.max(cachbox1.size(), cachbox2.size());
        maxLength = Math.max(maxLength, cachbox3.size());
        maxLength = Math.max(maxLength, cachbox4.size());
        maxLength = Math.max(maxLength, cachbox5.size());

        Map<LocalTime, Double> maxBuyers = new HashMap<>();
        LocalTime time = LocalTime.of(10, 00);
        for (int i = 0; i < maxLength; i++) {
            double q = 0.0;
            if(cachbox1.size() <= i + 1){
                q += cachbox1.get(i);
            };
            if(cachbox2.size() <= i + 1){
                q += cachbox2.get(i);
            };
            if(cachbox3.size() <= i + 1){
                q += cachbox3.get(i);
            };
            if(cachbox4.size() <= i + 1){
                q += cachbox4.get(i);
            };
            if(cachbox5.size() <= i + 1){
                q += cachbox5.get(i);
            };
            time = time.plusMinutes(30);
            maxBuyers.put(time, q);
        }
        LocalTime r = getTimeInterval(maxBuyers);
        System.out.println("The maximum count of people were from " + r.minusMinutes(30) + " to " + r + " hour.");

    }

    public static List<Double> getList(File file) {
        Scanner scanner = null;
        ArrayList<Double> result = new ArrayList<>();
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String str = scanner.nextLine();
                str = str.replace(",", ".");
                if("".equalsIgnoreCase(str.trim())){
                    continue;
                }
                Double val = Double.parseDouble(str);
                result.add(val);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + file + " not found");

        }catch(NumberFormatException e){
            System.out.println("File " + file + " has wrong format data");
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return result;
    }

    public static LocalTime getTimeInterval(Map<LocalTime, Double> hashMap) {
        LocalTime key = LocalTime.of(00, 00);
        double maxValueInHashMap = (Collections.max(hashMap.values()));
        for (Map.Entry<LocalTime, Double> entry : hashMap.entrySet()) {
            if (entry.getValue() == maxValueInHashMap) {
                key = entry.getKey();
            }
        }
        return key;
    }
}
