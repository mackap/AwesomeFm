package com.mackap.awesomefm.screenArtistDetails;

import static com.mackap.awesomefm.screenStart.StartPageFragment.ARTIST_ID_KEY;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.mackap.awesomefm.AwesomeFmApp;
import com.mackap.awesomefm.R;
import com.mackap.awesomefm.common.mvp.BaseFragment;
import com.mackap.awesomefm.common.utils.UiUtils;
import com.mackap.awesomefm.pojo.Artist;
import com.mackap.awesomefm.pojo.Image;
import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsMVPContract.IArtistDetailsPresenter;
import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsMVPContract.IArtistDetailsView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Makarov Mikhail on 2018.
 */
public class ArtistDetailsFragment extends BaseFragment implements
    IArtistDetailsView<IArtistDetailsPresenter> {

  public final static String TAG = "ArtistDetailsFragment";
  private final static int SMALL_IMAGE_INDEX = 0;
  private final static int MEDIUM_IMAGE_INDEX = 1;
  private final static int BIG_IMAGE_INDEX = 2;

  @BindView(R.id.tv_artist_details_title)
  TextView tvArtistTitle;
  @BindView(R.id.image_view_artist_details)
  ImageView ivArtist;
  @BindView(R.id.tv_artist_details_biography)
  TextView tvArtistBiography;
  @BindView(R.id.fr_layout_artist_detail)
  FrameLayout frProgress;
  @Inject
  IArtistDetailsPresenter mArtistDetailsPresenter;
  String mArtistMbid;

  @Override
  public void onAttach(final Context context) {
    super.onAttach(context);
    Bundle bundle = getArguments();
    mArtistMbid = bundle.getString(ARTIST_ID_KEY);
    ((AwesomeFmApp) getActivity().getApplication()).getArtistDetailsSubComponent().inject(this);
    super.mBasePresenter = mArtistDetailsPresenter;
    mArtistDetailsPresenter.setArtistMbid(mArtistMbid);
    mArtistDetailsPresenter.attachView(this);
  }

  @Override
  public int getLayoutResource() {
    return R.layout.fragment_arist_details;
  }


  @Override
  public void showProgress() {
    frProgress.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgress() {
    frProgress.setVisibility(View.GONE);
  }

  ///// todo maybe move realisation to BaseFragment ?
  @Override
  public void showError() {
    hideProgress();
    Toast.makeText(getContext(),
        mArtistDetailsPresenter.getCurrentErrorMessage() == null ? getString(R.string.default_error_message)
            : mArtistDetailsPresenter.getCurrentErrorMessage(), Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showData() {
    hideProgress();
    Artist artist = mArtistDetailsPresenter.getArtist();
    if (artist != null) {
      tvArtistTitle.setText(artist.getName());
      String imageUrl = getBigImage(artist);
      if (imageUrl != null) {
        Glide.with(getContext()).applyDefaultRequestOptions(UiUtils.getRequestOptionsForGlide())
            .load(getBigImage(artist)).into(ivArtist);
      }
      tvArtistBiography.setText(artist.getBio().getSummary());
    }
  }

  /**
   * choosing the most big image from existing
   * @return image url
   */
  private String getBigImage(final Artist artist) {
    List<Image> imageList = artist.getImage();
    if (imageList.size() > BIG_IMAGE_INDEX) {
      return imageList.get(BIG_IMAGE_INDEX).getText();
    }
    if (imageList.size() > MEDIUM_IMAGE_INDEX) {
      return imageList.get(MEDIUM_IMAGE_INDEX).getText();
    }
    if (imageList.size() > SMALL_IMAGE_INDEX) {
      return imageList.get(SMALL_IMAGE_INDEX).getText();
    }
    return null;
  }


}
