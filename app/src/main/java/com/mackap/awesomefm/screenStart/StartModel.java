package com.mackap.awesomefm.screenStart;


import com.mackap.awesomefm.net.ApiService;
import com.mackap.awesomefm.net.RestClient;
import com.mackap.awesomefm.pojo.Artist;
import com.mackap.awesomefm.pojo.TopArtistsResponse;
import com.mackap.awesomefm.screenStart.StartMVPContract.IStartModel;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;


/**
 * Created by Makarov Mikhail on 2018.
 */
public class StartModel implements IStartModel {

  private static final String TAG = "StartModel";
  private String mCurrentErrorMessage;
  private List<Artist> mChartArtistsList;
  private ApiService mApiService;

  public StartModel(final ApiService apiService) {
    mApiService = apiService;
  }

  @Override

  public Single<TopArtistsResponse> loadTopArtists() {
    return mApiService.getTopArtist();
  }

  @Override
  public void setCurrentErrorMessage(final String errorMessage) {
    mCurrentErrorMessage = errorMessage;
  }

  @Override
  public String getCureentErrorMessage() {
    return mCurrentErrorMessage;
  }

  @Override
  public void setChartArtistsList(final List<Artist> chartArtistsList) {
    mChartArtistsList = chartArtistsList;
  }

  @Override
  public List<Artist> getChartArtistsList() {
    return mChartArtistsList;
  }
}
