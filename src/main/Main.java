package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static main.Service.startComparator;


public class Main {

    public static List<Service> readFromList(List<String> list) {
        List<Service> data = new ArrayList<>();


        for (String s : list) {
            data.add(new Service(s));
        }

        return data;
    }

    public static List<Service> soSlow(List<Service> serviceList) {
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).getFinish() - serviceList.get(i).getStart() > 60) {
                serviceList.remove(i);
                i--;
            }
        }
        return serviceList;
    }

    public static List<Service> removeSameTime(List<Service> serviceList) {
        for (int i = 0; i < serviceList.size() - 1; i++) {
            if (serviceList.get(i).getStart().equals(serviceList.get(i + 1).getStart()) && serviceList.get(i).getFinish().equals(serviceList.get(i + 1).getFinish())) {
                if (!serviceList.get(i + 1).isCompany()) {
                    serviceList.remove(i + 1);
                    i--;
                } else if (!serviceList.get(i).isCompany()) {
                    serviceList.remove(i);
                    i--;
                }
            }
        }
        return serviceList;
    }

    public static List<Service> removeSameFinish(List<Service> serviceList) {
        for (int i = 0; i < serviceList.size() - 1; i++) {
            if (serviceList.get(i).getFinish().equals(serviceList.get(i + 1).getFinish())) {
                if (serviceList.get(i + 1).getStart() < serviceList.get(i).getStart()) {
                    serviceList.remove(i + 1);
                    i--;
                } else {
                    serviceList.remove(i);
                    i--;
                }
            }
        }
        return serviceList;
    }

    public static List<Service> removeOfExcess(List<Service> serviceList) {
        for (int i = 0; i < serviceList.size() - 1; i++) {
            if (serviceList.get(i + 1).getStart() < serviceList.get(i).getStart() && serviceList.get(i + 1).getFinish() > serviceList.get(i).getFinish()) {

                serviceList.remove(i + 1);
                i--;
            }
            if (serviceList.get(i + 1).getStart() > serviceList.get(i).getStart() && serviceList.get(i + 1).getFinish() < serviceList.get(i).getFinish()) {

                serviceList.remove(i);
                i--;
            }

        }
        return serviceList;
    }

    public static void listToFile(List<Service> serviceList) throws IOException {
        File file = new File("src/main/resources/output.txt");

        FileWriter fileWriter = new FileWriter(file);
        // writer.write("Test data");

        for (Service s : serviceList) {
            if (s.isCompany()) {
                fileWriter.write(s.toString());
                fileWriter.write("\n");
            }
        }
        fileWriter.write("\n");
        for (Service s : serviceList) {
            if (!s.isCompany()) {
                fileWriter.write(s.toString());
                fileWriter.write("\n");
            }
        }
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException {


        List<String> data = Files.readAllLines(Paths.get("src/main/resources/input.txt"));
        List<Service> serviseList = readFromList(data);
        soSlow(serviseList);
        serviseList.sort(startComparator);
        removeSameTime(serviseList);
        removeSameFinish(serviseList);
        removeOfExcess(serviseList);
        listToFile(serviseList);





    }


}
