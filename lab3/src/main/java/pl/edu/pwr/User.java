package pl.edu.pwr;

/**
 * Created by Pawel on 2014-11-05.
 */
public class User {

    private String delimiter;

    private Integer id;

    private String name;

    private String surname;

    private String address;

    private String country;

    private String email;

    private String network_operator;

    private Long salary;

    public User(String delimiter, Integer id, String name, String surname, String address, String country, String email, String network_operator, Long salary) {
        this.delimiter = delimiter;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.country = country;
        this.email = email;
        this.network_operator = network_operator;
        this.salary = salary;
    }

    public User(String delimiter, Integer id){
        this.delimiter = delimiter;
        this.id = id;
        this.name = "";
        this.surname = "";
        this.address = "";
        this.country = "";
        this.email = "";
        this.network_operator = "";
        this.salary = Long.MIN_VALUE;
    }

    public User(String delimiter) {
        this.delimiter = delimiter;
        this.id = -1;
        this.name = "";
        this.surname = "";
        this.address = "";
        this.country = "";
        this.email = "";
        this.network_operator = "";
        this.salary = Long.MIN_VALUE;
    }

    public String getHeader(){
        return "id" + delimiter +
                "name" + delimiter +
                "surname" + delimiter +
                "address" + delimiter +
                "country" + delimiter +
                "email" + delimiter +
                "network_operator" + delimiter +
                "salary\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        return !(name != null ? !name.equals(user.name) : user.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return id.toString() + delimiter +
               name + delimiter +
               surname + delimiter +
               address + delimiter +
               country + delimiter +
               email + delimiter +
               network_operator + delimiter +
               salary.toString() + "\n";
    }
}
