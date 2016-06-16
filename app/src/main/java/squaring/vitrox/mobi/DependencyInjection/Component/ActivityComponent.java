package squaring.vitrox.mobi.DependencyInjection.Component;

import dagger.Component;
import squaring.vitrox.mobi.DependencyInjection.ActivityScope;
import squaring.vitrox.mobi.MainActivity;

/**
 * Created by miguelgomez on 6/7/16.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent extends AppComponent{

    void inject(MainActivity activity);

}