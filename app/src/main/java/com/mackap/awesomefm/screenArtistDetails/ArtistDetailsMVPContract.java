package com.mackap.awesomefm.screenArtistDetails;

import com.mackap.awesomefm.common.mvp.MVP;
import com.mackap.awesomefm.pojo.Artist;
import io.reactivex.Single;

/**
 * Created by Makarov Mikhail on 2018.
 */
public interface ArtistDetailsMVPContract {

  interface IArtistDetailsPresenter extends MVP.IPresenter {

    String getCurrentErrorMessage();

    Artist getArtist();

    void setArtistMbid(String artistMbid);
  }

  interface IArtistDetailsView<T> extends MVP.IView {

  }

  interface IArtistDetailsModel extends MVP.IModel {

    void setCurrentErrorMessage(String errorMessage);

    boolean isChangeArtistMbid();

    Artist getArtist();

    Single<Artist> getArtistInfoObservable();

    String getCureentErrorMessage();

    void setArtistMbid(String artistMbid);

    void setArtist(Artist artist);
  }
}
