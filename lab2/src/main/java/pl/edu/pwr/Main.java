package pl.edu.pwr;

import pl.edu.pwr.planner.Categories;
import pl.edu.pwr.planner.Planner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class Main {

    static Planner planner;

    public static void main(String[] args) throws IOException, ParseException {
        planner = new Planner();
        while(true){
            System.out.println("1. Add a task.");
            System.out.println("2. Print categories.");
            System.out.println("3. Print Tasks for given category.");
            System.out.println("4. Remove a task.");
            System.out.println("5. Mark task as done.");
            System.out.println("6. Show unifinished tasks.");

            String reply = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

            switch(reply){
                case "1":
                    planner.createTask();
                    break;
                case "2":
                    Categories.print();
                    break;
                case "3":
                    System.out.println("Type the category: ");
                    planner.printForCategory(new BufferedReader(new InputStreamReader(System.in)).readLine().trim());
                    break;
                case "4":
                    System.out.println("Type the name of the task to be deleted: ");
                    planner.removeTask(new BufferedReader(new InputStreamReader(System.in)).readLine().trim());
                    break;
                case "5":
                    System.out.println("Type the name of the task to mark it as done: ");
                    planner.markAsDone(new BufferedReader(new InputStreamReader(System.in)).readLine().trim());
                    break;
                case "6":
                    planner.getUnfinished().forEach(t-> System.out.println(t.toString()));
                    break;
                default:
                    System.out.println("Answer not recognized, try again.");
                    break;
            }
        }
    }
}
