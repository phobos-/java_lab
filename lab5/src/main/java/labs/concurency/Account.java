package labs.concurency;

/**
 * Created by pawel on 26.11.15.
 */
public class Account {

    private String name;
    private Integer amount;

    public Account(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void add(Integer toAdd) {
        amount = amount + toAdd;
    }

    public void withdraw(Integer toAdd) {
        amount = amount - toAdd;
    }
}
