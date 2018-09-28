package com.mackap.awesomefm.screenArtistDetails;

import com.mackap.awesomefm.common.mvp.BasePresenter;
import com.mackap.awesomefm.common.mvp.MVP.IView;
import com.mackap.awesomefm.common.ScreenState;
import com.mackap.awesomefm.pojo.Artist;
import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsMVPContract.IArtistDetailsModel;
import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsMVPContract.IArtistDetailsPresenter;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

/**
 * Created by Makarov Mikhail on 2018.
 */
public class ArtistDetailsPresenter extends BasePresenter implements IArtistDetailsPresenter {

  private final String TAG = getClass().getSimpleName();

  private IArtistDetailsModel mArtistDetailsModel;
  private Disposable mDisposable;

  @Inject
  public ArtistDetailsPresenter(IArtistDetailsModel artistDetailsModel) {
    mArtistDetailsModel = artistDetailsModel;
  }

  @Override
  public String getCurrentErrorMessage() {
    return mArtistDetailsModel.getCureentErrorMessage();
  }

  @Override
  public void attachView(final IView view) {
    mView = view;
  }

  @Override
  public void detachView() {
    mView = null;
  }

  @Override
  public ScreenState getCurrentScreenState() {
    return currentScreenState;
  }

  @Override
  public void getData() {
    if (mArtistDetailsModel.getArtist() != null && !mArtistDetailsModel.isChangeArtistMbid()) {
      mView.showData();
      return;
    }
    if (mDisposable != null && !mDisposable.isDisposed()) {
      mDisposable.dispose();
    }
    mArtistDetailsModel.getArtistInfoObservable().observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            new SingleObserver<Artist>() {
              @Override
              public void onSubscribe(final Disposable disposable) {
                mDisposable = disposable;
              }

              @Override
              public void onSuccess(final Artist artist) {
                if (mView != null) {
                  mArtistDetailsModel.setArtist(artist);
                  mView.showData();
                }
              }

              @Override
              public void onError(final Throwable e) {
                mArtistDetailsModel.setCurrentErrorMessage(e.getMessage());
                mView.showError();
              }
            });
  }

  @Override
  public Artist getArtist() {
    return mArtistDetailsModel.getArtist();
  }

  @Override
  public void setArtistMbid(final String artistMbid) {
    mArtistDetailsModel.setArtistMbid(artistMbid);
  }
}
