package lib;

public class Tuple <Generic1, Generic2> {
    private Generic1 key;
    private Generic2 value;

    Tuple(Generic1 key, Generic2 value){
        this.key = key;
        this.value = value;
    }
    public Generic1 getKey() {
        return key;
    }
    public Generic2 getValue() {
        return value;
    }
    public void setValue(Generic2 value) {
        this.value = value;
    }
}
