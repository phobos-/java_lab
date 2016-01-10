package pl.edu.pwr.reader;

/**
 * Created by Pawel on 2014-10-24.
 */
public class ReaderFactory {
    public static AbstractReader create(String path){
        if(path == null) throw new IllegalArgumentException("Path may not be null");
        if (path.startsWith("http://") || path.startsWith("www"))
            return new WebPageReader(path);
        return new FileReader(path);
    }
}
