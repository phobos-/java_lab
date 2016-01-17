import java.util.concurrent.atomic.AtomicInteger;

public class Pub {
    private AtomicInteger counter = new AtomicInteger(1);
    private Integer id;
    private String name;
    private String addr;
    private String coords;
    private Float score;
    private Integer pickCount;

    public Pub(Integer id, String name, String addr, String coords, Float score){
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.coords = coords;
        this.score = score;
        this.pickCount = counter.getAndIncrement();
    }

    public void pick(){
        pickCount = counter.getAndIncrement();
    }

    public Integer getPickCount(){
        return pickCount;
    }

    public String getCoords(){
        return coords;
    }

    public String getName(){
        return name;
    }

    public String getAddr(){
        return addr;
    }

    public Float getScore(){
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pub pub = (Pub) o;

        if (id != null ? !id.equals(pub.id) : pub.id != null) return false;
        return !(name != null ? !name.equals(pub.name) : pub.name != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        String delimiter = "\t";
        return id.toString() + delimiter +
                name + delimiter +
                addr + delimiter +
                coords + delimiter +
                score + "\n";
    }
}
