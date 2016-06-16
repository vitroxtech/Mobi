package squaring.vitrox.mobi.Model;

import java.util.List;

/**
 * Created by miguelgomez on 6/7/16.
 */
public class mobiCategories {

    private String id;

    private String name;

    private List<Products> products;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public List<Products> getProducts ()
    {
        return products;
    }

    public void setProducts (List<Products> products)
    {
        this.products = products;
    }
}
