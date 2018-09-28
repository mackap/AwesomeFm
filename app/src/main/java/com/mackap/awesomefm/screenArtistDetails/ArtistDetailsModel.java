package com.mackap.awesomefm.screenArtistDetails;

import com.mackap.awesomefm.net.ApiService;
import com.mackap.awesomefm.net.RestClient;
import com.mackap.awesomefm.pojo.Artist;
import com.mackap.awesomefm.pojo.ArtistInfoResponse;
import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsMVPContract.IArtistDetailsModel;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * Created by Makarov Mikhail on 2018.
 */
public class ArtistDetailsModel implements IArtistDetailsModel {

  private Artist mArtist;
  private String mArtistMbid;
  private ApiService mApiService;

  @Inject
  public ArtistDetailsModel(ApiService apiService){
    mApiService = apiService;
  }
  @Override
  public Single<Artist> getArtistInfoObservable() {
    return mApiService.getArtistInfo(mArtistMbid)
        .subscribeOn(Schedulers.io())
        .map(ArtistInfoResponse::getArtist);
  }

  @Override
  public void setCurrentErrorMessage(final String errorMessage) {

  }

  @Override
  public String getCureentErrorMessage() {
    return null;
  }

  @Override
  public Artist getArtist() {
    return mArtist;
  }

  @Override
  public boolean isChangeArtistMbid() {
    if (mArtist == null) {
      return true;
    }
    if (mArtist.getMbid().equals(mArtistMbid)) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public void setArtist(final Artist artist) {
    mArtist = artist;
  }

  @Override
  public void setArtistMbid(final String artistMbid) {
    mArtistMbid = artistMbid;

  }
}
