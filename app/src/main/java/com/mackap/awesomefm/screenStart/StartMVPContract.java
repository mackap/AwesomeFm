package com.mackap.awesomefm.screenStart;

import com.mackap.awesomefm.common.mvp.MVP;
import com.mackap.awesomefm.pojo.Artist;
import com.mackap.awesomefm.pojo.TopArtistsResponse;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by Makarov Mikhail on 2018.
 */
public interface StartMVPContract {

  interface IStartPresenter extends MVP.IPresenter {

    List<Artist> getListTopArtists();

    String getCurrentErrorMessage();
  }

  interface IStartView<T> extends MVP.IView {

  }

  interface IStartModel extends MVP.IModel {

    Single<TopArtistsResponse> loadTopArtists();

    void setCurrentErrorMessage(String errorMessage);

    String getCureentErrorMessage();

    void setChartArtistsList(List<Artist> topArtistsResponse);

    List<Artist> getChartArtistsList();
  }
}
