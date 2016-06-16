package squaring.vitrox.mobi.Adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.ArrayList;
import java.util.List;
import squaring.vitrox.mobi.Model.Products;
import squaring.vitrox.mobi.R;
import squaring.vitrox.mobi.Support.Config;

/**
 * Created by miguelgomez on 6/8/16.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    List<Products> mItems;
    Context mContext;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Products item);
    }

    public ListAdapter(Context context, OnItemClickListener listener) {
        super();
        this.mContext=context;
        this.listener = listener;
        mItems = new ArrayList<>();
    }

    public void addData(Products dataObj) {
        mItems.add(dataObj);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(mItems.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumb;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.itemTitle);
            thumb = (ImageView) itemView.findViewById(R.id.rowThumbnail);
        }

        public void bind(final Products item, final OnItemClickListener listener) {
            title.setText(item.getName());
            String Urlimage= Config.BASEURL+item.getUrl();
            Glide.with(mContext).load(Urlimage)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .listener(requestListener)
                    .placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
                    .centerCrop().into(thumb);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

            //CAPTURE ERROR ON URL FOR SOME IMAGES THAT ARE NOT AVALIABLE
            Log.d("ImageErrorUrl: ",model);
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };

}