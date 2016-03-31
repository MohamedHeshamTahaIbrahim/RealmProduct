package Model;

import io.realm.RealmObject;

/**
 * Created by محمد on 31/03/2016.
 */
public class Products extends RealmObject {
    private String name;
    private int cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
