package pl.edu.pwr;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FileCreator {
    private AtomicInteger counter = new AtomicInteger(1);
    private String delimiter;
    private String goodPrefix;
    private String badPrefix;
    private Integer chunkSize;

    public FileCreator(String delimiter, String goodPrefix, String badPrefix, Integer chunkSize){
        this.delimiter = delimiter;
        this.goodPrefix = goodPrefix;
        this.badPrefix = badPrefix;
        this.chunkSize = chunkSize;
    }

    private String prepareFilename(Integer lines, String prefix){
        return prefix+'_'+lines.toString()+'_'+counter.get()+".csv";
    }

    private void writeChunk(String prefix, List<User> chunk){
        try {
            FileWriter writer = new FileWriter(prepareFilename(chunkSize,prefix));
            writer.write(new User(delimiter).getHeader());
            for(User user : chunk)
                writer.write(user.toString());

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        counter.incrementAndGet();
    }

    public void writeFiles(CsvParser.ParsingResult result){
        Integer chunk = 0;
        while(chunk+chunkSize < result.getValidUsers().size()){
            writeChunk(goodPrefix,new ArrayList<User>(result.getValidUsers().subList(chunk, chunk+chunkSize)));
            chunk = chunk+chunkSize;
        }
        if(chunk < result.validUsers.size())
            writeChunk(goodPrefix,new ArrayList<User>(result.getValidUsers().subList(chunk, result.validUsers.size())));

        chunk = 0;
        counter.set(1);
        while(chunk+chunkSize < result.getInvalidUsers().size()){
            writeChunk(badPrefix,new ArrayList<User>(result.getInvalidUsers().subList(chunk, chunk+chunkSize)));
            chunk = chunk+chunkSize;
        }
        if(chunk < result.invalidUsers.size())
            writeChunk(badPrefix,new ArrayList<User>(result.getInvalidUsers().subList(chunk, result.invalidUsers.size())));
    }

    public String getDelimiter(){
        return delimiter;
    }
}
