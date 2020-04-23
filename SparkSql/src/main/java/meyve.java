public class meyve {
    private String fruit;
    private String size;
    private String color;
    private long count;

    public meyve() {
    }

    public meyve(String fruit, String size, String color, long count) {
        this.fruit = fruit;
        this.size = size;
        this.color = color;
        this.count = count;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
