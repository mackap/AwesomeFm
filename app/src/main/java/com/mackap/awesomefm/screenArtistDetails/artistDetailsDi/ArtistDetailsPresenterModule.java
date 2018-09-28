package com.mackap.awesomefm.screenArtistDetails.artistDetailsDi;

import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsMVPContract.IArtistDetailsModel;
import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsMVPContract.IArtistDetailsPresenter;
import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Makarov Mikhail on 2018.
 */
@Module
public class ArtistDetailsPresenterModule {

  @Provides
  @ArtistDetailsScope
  IArtistDetailsPresenter getArtistDetailsPresenter(IArtistDetailsModel artistDetailsModel){
    return new ArtistDetailsPresenter(artistDetailsModel);
  }
}
