package squaring.vitrox.mobi.Network;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import squaring.vitrox.mobi.DependencyInjection.Module.ApplicationModule;
import squaring.vitrox.mobi.Support.Config;

/**
 * Created by miguelgomez on 6/7/16.
 */
@Module(includes = ApplicationModule.class)
public class ServiceModule {

    @Provides
    @Singleton
    public ApiService apiService(OkHttpClient client) {

        String demoUrl = Config.BASEURL;
        //Jackson Mapper and deserializer
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new Retrofit.Builder()
                .baseUrl(demoUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(client)
                .build()
                .create(ApiService.class);
    }

    @Provides
    @Singleton
    public OkHttpClient okHttpClient() {

        OkHttpClient okHttpClient = new OkHttpClient();
        return okHttpClient;
    }



}
