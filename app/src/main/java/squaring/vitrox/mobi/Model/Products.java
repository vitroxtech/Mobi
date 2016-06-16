package squaring.vitrox.mobi.Model;

/**
 * Created by miguelgomez on 6/7/16.
 */
public class Products {

    private String name;

    private String categoryId;

    private SalePrice salePrice;

    private String url;


    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCategoryId ()
    {
        return categoryId;
    }

    public void setCategoryId (String categoryId)
    {
        this.categoryId = categoryId;
    }

    public SalePrice getSalePrice ()
    {
        return salePrice;
    }

    public void setSalePrice (SalePrice salePrice)
    {
        this.salePrice = salePrice;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

}
