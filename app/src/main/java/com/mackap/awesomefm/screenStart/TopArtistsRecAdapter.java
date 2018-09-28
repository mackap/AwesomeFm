package com.mackap.awesomefm.screenStart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.mackap.awesomefm.R;
import com.mackap.awesomefm.common.IRecItemClickListener;
import com.mackap.awesomefm.common.utils.UiUtils;
import com.mackap.awesomefm.pojo.Artist;
import com.mackap.awesomefm.screenStart.TopArtistsRecAdapter.TopArtistrsViewHolder;
import java.util.List;

/**
 * Created by Makarov Mikhail on 2018.
 */
public class TopArtistsRecAdapter extends Adapter<TopArtistrsViewHolder> {

  private List<Artist> mArtistList;
  private Context context;

IRecItemClickListener mCallback;
  public TopArtistsRecAdapter(IRecItemClickListener callback) {
    mCallback = callback;

  }

  @NonNull
  @Override
  public TopArtistrsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
      final int viewType) {
    context = parent.getContext();
    View view = LayoutInflater.from(context)
        .inflate(R.layout.item_artist_top_artists, null, false);
    TopArtistrsViewHolder holder = new TopArtistrsViewHolder(view);

    view.setOnClickListener(
        v -> mCallback.onItemClick(mArtistList.get(holder.getAdapterPosition()).getMbid()));
    return holder;

  }

  @Override
  public void onBindViewHolder(@NonNull final TopArtistrsViewHolder holder, final int position) {
    Artist artist = mArtistList.get(position);

    holder.tvArtistName.setText(artist.getName());

    Glide.with(context).applyDefaultRequestOptions(UiUtils.getRequestOptionsForGlide()).load(artist.getImage().get(2).getText()).into(holder.imageViewArtist);
  }

  public void updateArtists(List<Artist> artists) {
    mArtistList = artists;
   notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return mArtistList == null ? 0 : mArtistList.size();
  }

  public static class TopArtistrsViewHolder extends ViewHolder {

    @BindView(R.id.tv_artist_name)
    TextView tvArtistName;
    @BindView(R.id.image_artist)
    ImageView imageViewArtist;

    public TopArtistrsViewHolder(final View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
