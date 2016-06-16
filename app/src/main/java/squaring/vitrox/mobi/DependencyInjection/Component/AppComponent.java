package squaring.vitrox.mobi.DependencyInjection.Component;

import android.content.Context;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import javax.inject.Singleton;
import dagger.Component;
import squaring.vitrox.mobi.Adapters.ListAdapter;
import squaring.vitrox.mobi.Adapters.SectionedListAdapter;
import squaring.vitrox.mobi.DependencyInjection.Module.ApplicationModule;
import squaring.vitrox.mobi.Network.ApiService;
import squaring.vitrox.mobi.Network.ServiceModule;

/**
 * Created by miguelgomez on 6/7/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ServiceModule.class})
public interface AppComponent {

    Context appContext();

    ApiService apiService();

    OkHttpClient okHttpClient();

    List<SectionedListAdapter.Section> getSectionList();
}
