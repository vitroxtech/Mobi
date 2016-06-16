package squaring.vitrox.mobi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.List;
import javax.inject.Inject;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import squaring.vitrox.mobi.Adapters.ListAdapter;
import squaring.vitrox.mobi.Adapters.SectionedListAdapter;
import squaring.vitrox.mobi.Model.Products;
import squaring.vitrox.mobi.Model.mobiCategories;
import squaring.vitrox.mobi.Network.ApiService;

/**
 * Created by miguelgomez on 6/8/16.
 */
public class MainActivity extends BaseActivity {

    @Inject
    ApiService service;
    @Inject
    List<SectionedListAdapter.Section> sections;

    String TAG="MainActivity";
    ListAdapter itemListAdapter;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //injectComponent
        getComponent().inject(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemListAdapter = new ListAdapter(this,ItemClicked);

        Button bFetch = (Button) findViewById(R.id.button_fetch);
        if(bFetch!=null)
            bFetch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Being an RX observable encapsulated it will capture also the errors coming from the Client and Observer Itself
                    // once happen onComplete and onError the thread is released automatically
                    service.getJsonCat()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(new Observer<List<mobiCategories>>() {
                                @Override
                                public void onCompleted() {
                                    Log.d(TAG,"Finished");
                                }

                                @Override
                                public void onError(Throwable e) {
                                    SendErrorMessage(e.getMessage());
                                }

                                @Override
                                public void onNext(List<mobiCategories> dataObjList) {
                                    OrderList(dataObjList);
                                }
                            });
                }
            });

    }

    private ListAdapter.OnItemClickListener ItemClicked= new ListAdapter.OnItemClickListener() {
        @Override public void onItemClick(Products item) {
            getApp().setProduct(item);
            GotoDetails();
        }
    };

    private void SendErrorMessage(final String txt) {
        //Show errors with Snackbar "nice!"
        this.runOnUiThread(new Runnable() {
            public void run() {

                Snackbar.make(findViewById(android.R.id.content),txt,Snackbar.LENGTH_LONG).show();

            }
        });
    }


    private void GotoDetails()
    {
        Intent intent= new Intent(this,DetailActivity.class);
        startActivity(intent);
    }

    public void OrderList(List<mobiCategories> dataObjList)
    {
        itemListAdapter.clear();
        sections.clear();
        int totalrows=0;
        for(int i=0; i<dataObjList.size();i++)
        {
            sections.add(new SectionedListAdapter.Section(i+totalrows,dataObjList.get(i).getName()));
            List<Products> productses=dataObjList.get(i).getProducts();
            for(int j=0; j<productses.size();j++)
            {
                itemListAdapter.addData(productses.get(j));
                totalrows++;
            }
            //balance the value because the j starts in 0
            totalrows--;
        }

        SectionedListAdapter.Section[] dumb = new SectionedListAdapter.Section[sections.size()];

        SectionedListAdapter mSectionedAdapter = new
                SectionedListAdapter(this,R.layout.item_header,R.id.headerTitle,itemListAdapter);
        mSectionedAdapter.setSections(sections.toArray(dumb));

        //Apply this adapter to the RecyclerView then SHOW! :)
        mRecyclerView.setAdapter(mSectionedAdapter);

    }


}
