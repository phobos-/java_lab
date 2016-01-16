package pl.edu.pwr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvParser {
    private String delimiter;
    private IdParser idParser = new IdParser();

    public CsvParser(String delimiter){
        this.delimiter = delimiter;
    }

    public ParsingResult parse(String csvFile) {

        BufferedReader br = null;
        String line = "";
        List<User> users = new ArrayList<>();
        List<User> badUsers = new ArrayList<>();

        try {
            Map<Integer, String[]> map = new HashMap<>();
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] result = line.split(delimiter);
                Optional<Integer> id = idParser.parse(result[0]);
                if(id.isPresent())
                    map.put(id.get(), result);
            }

            for(Map.Entry<Integer, String[]> entry : map.entrySet()){
                if(validate(entry.getValue()))
                    users.add(convert(entry.getKey(),entry.getValue()));
                else
                    badUsers.add(convert(entry.getKey(),entry.getValue()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new ParsingResult(users,badUsers);
    }

    private User convert(Integer id, String [] input){
        User user = null;
        if(input.length == 8)
            try{
                user = new User(
                        delimiter,id,input[1],input[2],input[3],input[4],input[5],input[6],Long.parseLong(input[7])
                );
            } catch (Exception e){
                e.printStackTrace();
            }
        else user = new User(delimiter,id);
        return user;
    }

    private boolean validate(String [] input){
        //validate size
        if(input.length != 8) return false;

        //validate email
        try {
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(input[5]);
            if(!m.matches()) return false;
        } catch (Exception ex) {
            return false;
        }

        //validate long
        try {
            Long.parseLong(input[7]);
        } catch (Exception ex){
            return false;
        }

        //validate operator
        if (input[6].equalsIgnoreCase("plus")
         || input[6].equalsIgnoreCase("orange")
         || input[6].equalsIgnoreCase("heyah")
         || input[6].equalsIgnoreCase("play")
         || input[6].equalsIgnoreCase("tmobile")
         || input[6].equalsIgnoreCase("t-mobile")) return true;

        return false;
    }

    public class ParsingResult{
        public List<User> validUsers;
        public List<User> invalidUsers;

        public ParsingResult(){
            this.validUsers = new ArrayList<>();
            this.invalidUsers = new ArrayList<>();
        }

        public ParsingResult(final List<User> validUsers, final List<User> invalidUsers){
            this.validUsers = validUsers;
            this.invalidUsers = invalidUsers;
        }

        public List<User> getValidUsers(){
            return validUsers;
        }

        public List<User> getInvalidUsers(){
            return invalidUsers;
        }
    }
}
