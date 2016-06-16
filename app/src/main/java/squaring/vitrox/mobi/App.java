package squaring.vitrox.mobi;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import squaring.vitrox.mobi.DependencyInjection.Component.AppComponent;
import squaring.vitrox.mobi.DependencyInjection.Component.DaggerAppComponent;
import squaring.vitrox.mobi.DependencyInjection.Module.ApplicationModule;
import squaring.vitrox.mobi.Model.Products;

/**
 * Created by miguelgomez on 6/7/16.
 */
public class App extends Application {
    private AppComponent mAppComponent;
    private Products myproduct;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    protected Products getProduct(){return myproduct;}
    protected void setProduct(Products product){myproduct= product;}

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }
}
