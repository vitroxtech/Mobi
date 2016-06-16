package squaring.vitrox.mobi.Network;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;
import squaring.vitrox.mobi.Model.mobiCategories;

/**
 * Created by miguelgomez on 6/7/16.
 */
public interface ApiService {

    @GET("/")
    Observable<List<mobiCategories>> getJsonCat();

}
