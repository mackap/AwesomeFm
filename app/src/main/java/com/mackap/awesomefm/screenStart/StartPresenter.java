package com.mackap.awesomefm.screenStart;

import static com.mackap.awesomefm.screenStart.StartMVPContract.IStartPresenter;

import android.annotation.SuppressLint;
import android.util.Log;
import com.mackap.awesomefm.common.mvp.BasePresenter;
import com.mackap.awesomefm.common.ScreenState;
import com.mackap.awesomefm.pojo.Artist;
import com.mackap.awesomefm.screenStart.StartMVPContract.IStartModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Makarov Mikhail on 2018.
 */
public class StartPresenter extends BasePresenter implements IStartPresenter {

  private static final String TAG = "StartPresenter";

  private IStartModel mStartModel;
  private String errorMessage;


  @Inject
  public StartPresenter(IStartModel startModel) {
    mStartModel = startModel;

  }

  @SuppressLint("CheckResult")
  @Override
  public void getData() {
    if (getListTopArtists() != null) {
      mView.showData();
      return;
    }

    currentScreenState = ScreenState.SHOW_PROGRESS;
    mView.showProgress();

    mStartModel.loadTopArtists()
        .subscribeOn(Schedulers.io())
        .map(topArtistsResponse -> topArtistsResponse.getArtists().getArtist())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(topArtistsResponse -> {
          mStartModel.setChartArtistsList(topArtistsResponse);
          currentScreenState = ScreenState.SHOW_DATA;
          mView.showData();
        }, throwable -> {
          errorMessage = throwable.getMessage();
          Log.d(TAG, "onError, e=" + errorMessage);
          mStartModel.setCurrentErrorMessage(errorMessage);
          currentScreenState = ScreenState.SHOW_ERROR;
          mView.showError();
        });

  }

  @Override
  public String getCurrentErrorMessage() {
    return mStartModel.getCureentErrorMessage();
  }

  @Override
  public List<Artist> getListTopArtists() {
    return mStartModel.getChartArtistsList();
  }


}
