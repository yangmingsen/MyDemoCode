package top.yms.past11.reflect;

public class Computer {
    private String name;
    private double price;

    public Computer(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Computer(String name) {
        this.name = name;
    }

    public Computer() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
