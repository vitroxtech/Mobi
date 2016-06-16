package squaring.vitrox.mobi.DependencyInjection.Module;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import squaring.vitrox.mobi.Adapters.SectionedListAdapter;
import squaring.vitrox.mobi.App;

/**
 * Created by miguelgomez on 6/7/16.
 */
@Module
public class ApplicationModule {

    private final App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public Context appContext() {
        return mApp;
    }

    @Provides
    public List<SectionedListAdapter.Section> getSectionList(){return new ArrayList<SectionedListAdapter.Section>();}
}
