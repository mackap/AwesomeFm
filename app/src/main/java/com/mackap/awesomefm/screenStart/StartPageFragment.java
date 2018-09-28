package com.mackap.awesomefm.screenStart;

import android.content.Context;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.mackap.awesomefm.common.utils.ActivityUtils;
import com.mackap.awesomefm.AwesomeFmApp;
import com.mackap.awesomefm.R;
import com.mackap.awesomefm.common.IRecItemClickListener;
import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsFragment;
import com.mackap.awesomefm.screenStart.StartMVPContract.IStartPresenter;
import com.mackap.awesomefm.screenStart.StartMVPContract.IStartView;
import com.mackap.awesomefm.common.mvp.BaseFragment;
import javax.inject.Inject;

/**
 * Created by Makarov Mikhail on 2018.
 */
public class StartPageFragment extends BaseFragment<IStartPresenter> implements
    IStartView<IStartPresenter>, IRecItemClickListener {
public static final String ARTIST_ID_KEY = "artist_key";
  public static final String TAG = "StartPageFragment";
  private static final int SPAN_COUNT = 3;
  @Inject
  public IStartPresenter mPresenter;
  @BindView(R.id.rec_view_top_artists)
  RecyclerView mRecyclerViewTopArtists;
  @BindView(R.id.rec_view_top_tracks)
  RecyclerView mRecyclerViewTopTracks;
  @BindView(R.id.tv_start_fr_artists_title)
  TextView tvArtistChartTitle;
  @BindView(R.id.fr_layout_progress)
  FrameLayout frLayuoutProgress;


  TopArtistsRecAdapter mTopArtistsRecAdapter;
  GridLayoutManager mGridLayoutManager;

  @Override
  public void onAttach(final Context context) {
    super.onAttach(context);
    ((AwesomeFmApp) getActivity().getApplication()).getStartSubComponent().inject(this);
    super.mBasePresenter = mPresenter;
    mPresenter.attachView(this);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater,
      @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);

    mGridLayoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
    mRecyclerViewTopArtists.getViewTreeObserver().addOnGlobalLayoutListener(
        new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override
          public void onGlobalLayout() {
            mRecyclerViewTopArtists.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            int viewWidth = mRecyclerViewTopArtists.getMeasuredWidth();
            float cardViewWidth = getActivity().getResources()
                .getDimension(R.dimen.artist_image_size);
            int newSpanCount = (int) Math.floor(viewWidth / cardViewWidth);
            mGridLayoutManager.setSpanCount(newSpanCount);
            mGridLayoutManager.requestLayout();
          }
        });
    mRecyclerViewTopArtists.setLayoutManager(mGridLayoutManager);
    mTopArtistsRecAdapter = new TopArtistsRecAdapter(this);
    mRecyclerViewTopArtists.setAdapter(mTopArtistsRecAdapter);

    return view;
  }

  @Override
  public int getLayoutResource() {
    return R.layout.fragment_start_page;
  }

  @Override
  public void showProgress() {
    frLayuoutProgress.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgress() {
    frLayuoutProgress.setVisibility(View.GONE);
  }

  @Override
  public void showError() {
    hideProgress();
    tvArtistChartTitle.setVisibility(View.GONE);
    Toast.makeText(getContext(),
        mPresenter.getCurrentErrorMessage() == null ? getString(R.string.default_error_message)
            : mPresenter.getCurrentErrorMessage(), Toast.LENGTH_SHORT).show();
  }


  @Override
  public void showData() {
    hideProgress();
    tvArtistChartTitle.setVisibility(View.VISIBLE);
    mTopArtistsRecAdapter.updateArtists(mPresenter.getListTopArtists());

    //   mPresenter.getListTopTraks();
  }

  @Override
  public void onItemClick(final String itemId) {
    Fragment fragment = getFragmentManager().findFragmentByTag(ArtistDetailsFragment.TAG);
    if(fragment == null){
      fragment = new ArtistDetailsFragment();
    }
    Bundle bundle = new Bundle();
    bundle.putString(ARTIST_ID_KEY, itemId);
    fragment.setArguments(bundle);
    ActivityUtils.replaceFragment(getFragmentManager(), fragment , R.id.rl_content_main, ArtistDetailsFragment.TAG);
  }
}
