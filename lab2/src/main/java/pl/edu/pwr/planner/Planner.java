package pl.edu.pwr.planner;

import pl.edu.pwr.msg.NoMessageTypeFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Planner {

    private List<Task> tasks = new ArrayList<>();

    public void printForCategory(String category){
        for(Task task : tasks)
            if(task.getCategory().equals(category))
                System.out.println(task.toString());
    }

    public Task findByName(String name) {
        for (Task task : tasks)
            if (task.getName().equals(name))
                return task;
        return null;
    }

    public void removeTask(String name){
        Task task = findByName(name);
        if(task != null)
            tasks.remove(task);
        else System.out.println("Task not found!");
    }

    public void markAsDone(String name){
        Task task = findByName(name);
        if(task != null) {
            tasks.remove(task);
            task.setDone();
            tasks.add(task);
        }
        else System.out.println("Task not found!");
    }

    public List<Task> getUnfinished(){
        List<Task> unfinished = new ArrayList<>();
        for(Task t : tasks){
            if(!t.isDone())
                unfinished.add(t);
        }
        return unfinished;
    }

    public void createTask() throws IOException {
        System.out.println("1. Type your task's name: ");
        String name = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        System.out.println("2. Type your task's description: ");
        String desc = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        System.out.println("3. Type your task's category (i.e. TODAY, TOMORROW): ");
        String category = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        System.out.println("4. Type your task's starting date (dd-MM-yyyy): ");
        String date = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dt;
        while(true)
            try {
                dt = formatter.parse(date);
                break;
            } catch (ParseException p){
                System.out.println("Couldn't parse your date, type again with correct format (dd-mm-yyyy)");
                date = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
            }

        System.out.println("5. Is your task done?: y/n");
        String done = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        while(true)
            if(!done.equals("y") && !done.equals("n")) {
                System.out.println("Not recognized, reply y/n...");
                done = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
            } else break;

        Task task = null;
        try {
            task = new Task(name,desc,category,dt,done.equals("y") ? true : false);
        } catch (NoMessageTypeFoundException e) {
            System.out.println("Category was not in the database and got automatically added.");
            Categories.add(desc);
        }
        tasks.add(task);
        System.out.println("Task CREATED!");
    }

}
