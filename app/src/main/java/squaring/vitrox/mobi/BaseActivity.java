package squaring.vitrox.mobi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import squaring.vitrox.mobi.DependencyInjection.Component.ActivityComponent;
import squaring.vitrox.mobi.DependencyInjection.Component.DaggerActivityComponent;

/**
 * Created by altran_miguel on 09/06/16.
 */
public class BaseActivity extends AppCompatActivity {
    private ActivityComponent mComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mComponent = DaggerActivityComponent.builder()
                .appComponent(getApp().getAppComponent()).build();
    }

    protected App getApp() {
        return (App) getApplicationContext();
    }

    protected ActivityComponent getComponent() {
        return mComponent;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}