package lib;

public class Tuple <Generic1, Generic2> {
    private Generic1 key;
    private Generic2 value;

    Tuple(Generic1 key){
        this.key = key;
    }

    public Tuple(Generic1 key, Generic2 value){
        this.key = key;
        this.value = value;
    }
    public Generic1 getKey() {
        return key;
    }
    public Generic2 getValue() {
        return value;
    }

    public void setKey(Generic1 key) {
        this.key = key;
    }

    public void setValue(Generic2 value) {
        this.value = value;
    }

    /* 
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof this) {
            if (obj as) {
                
            }
        }
        else 
            return false;

    };
    */

    @Override
    public String toString() {
        return  "( " + key + ", " + value + " )";
    }
}
