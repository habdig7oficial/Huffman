package lib;

public class Tuple <Generic1, Generic2> {
    private Generic1 v1;
    private Generic2 v2;

    Tuple(Generic1 v1, Generic2 v2){
        this.v1 = v1;
        this.v2 = v2;
    }
    public Generic1 getV1() {
        return v1;
    }
    public Generic2 getV2() {
        return v2;
    }
}
