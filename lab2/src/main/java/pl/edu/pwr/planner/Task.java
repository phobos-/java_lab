package pl.edu.pwr.planner;

import pl.edu.pwr.msg.NoMessageTypeFoundException;

import java.util.Date;

public class Task {
    private String name;
    private String category;
    private String desc;
    private Date startDate;
    private boolean done;

    public Task(String name,String desc, String cat, Date date, boolean done) throws NoMessageTypeFoundException {
        this.name = name;
        this.category = Categories.exists(cat);
        this.desc = desc;
        this.startDate = date;
        this.done = done;
    }

    public boolean isDone(){
        return done;
    }

    public void setDone(){
        done = true;
    }

    public String getName(){
        return name;
    }

    public  String getCategory(){
        return category;
    }

    @Override
    public String toString(){
        return "Name: " + name + " Category: " + category + " Description: " + desc + " Date: " + startDate.toString();
    }
}
