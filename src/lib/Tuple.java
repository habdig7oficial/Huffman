package lib;

public class Tuple {
    private Character key;
    private Integer value;

    Tuple(Character key){
        this.key = key;
    }

    public Tuple(Character key, Integer value){
        this.key = key;
        this.value = value;
    }
    public Character getKey() {
        return key;
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return  "( " + key + ", " + value + " )";
    }
}
