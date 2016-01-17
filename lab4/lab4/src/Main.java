public class Main {
    public static void main(String[] args) {
        GdzieDzisiaj gd = new GdzieDzisiaj(new CsvParser("\t").parse("test.csv"));
    }
}
