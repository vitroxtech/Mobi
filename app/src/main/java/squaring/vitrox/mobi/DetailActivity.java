package squaring.vitrox.mobi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import squaring.vitrox.mobi.Model.Products;
import squaring.vitrox.mobi.Support.Config;
/**
 * Created by miguelgomez on 6/8/16.
 */
public class DetailActivity extends BaseActivity {

    @SuppressLint("SetText")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView NameDetail= (TextView)findViewById(R.id.nameDetail);
        TextView Price= (TextView)findViewById(R.id.priceDetail);
        ImageView imageDetail =(ImageView) findViewById(R.id.imageViewDetail);

        Products myProduct= getApp().getProduct();
        String priceval=myProduct.getSalePrice().getAmount()+" "+myProduct.getSalePrice().getCurrency();
        assert NameDetail != null;
        NameDetail.setText(myProduct.getName());
        assert Price != null;
        String Imageurl= Config.BASEURL+myProduct.getUrl();
        Price.setText(priceval);

        //Glide allows to cache the images on low_res with this strategy
        //on error and while load we have the placeholder image
        Glide.with(this).load(Imageurl)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
                .centerCrop().into(imageDetail);

    }

}
