package Model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by محمد on 31/03/2016.
 */
public class Person extends RealmObject {
    private String name;
    private RealmList<Products>productses;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Products> getProductses() {
        return productses;
    }

    public void setProductses(RealmList<Products> productses) {
        this.productses = productses;
    }
}
