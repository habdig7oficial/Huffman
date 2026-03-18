package lib;

import java.util.ArrayList;

public class Table<Generic extends Comparable<Generic>> {
    ArrayList<Generic> table = new ArrayList<Generic>(260);

    public void insert(Generic element){
        int mid = table.size();

        for (int i = 0; i < table.size(); i++) {
            int e = table.get(i).compareTo(element);

            if (e == 0) 
                break;
            else if (e < 0)
                mid +=  mid / 2;
            else 
                mid /= 2;
        }
        table.add(mid, element);
    }
}
