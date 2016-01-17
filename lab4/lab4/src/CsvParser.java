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

    public List<Pub> parse(String csvFile) {

        BufferedReader br = null;
        String line = "";
        List<Pub> pubs = new ArrayList<>();

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
                    pubs.add(convert(entry.getKey(),entry.getValue()));
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

        return pubs;
    }

    private Pub convert(Integer id, String [] input){
        Pub pub = null;
        if(input.length == 5)
            try{
                pub = new Pub(
                        id,input[1],input[2],input[3],Float.parseFloat(input[4])
                );
            } catch (Exception e){
                e.printStackTrace();
            }
        return pub;
    }

    public boolean validate(String [] input){
        //validate size
        if(input.length != 5) return false;

        //validate rating
        try {
            Float.parseFloat(input[4]);
        } catch (Exception ex){
            return false;
        }

        return true;
    }
}
