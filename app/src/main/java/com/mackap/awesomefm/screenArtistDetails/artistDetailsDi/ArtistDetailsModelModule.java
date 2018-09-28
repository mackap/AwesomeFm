package com.mackap.awesomefm.screenArtistDetails.artistDetailsDi;

import com.mackap.awesomefm.net.ApiService;
import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsMVPContract.IArtistDetailsModel;
import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Makarov Mikhail on 2018.
 */
@Module
public class ArtistDetailsModelModule {

  @Provides
  @ArtistDetailsScope
  IArtistDetailsModel getArtistModel(ApiService apiService) {
    return new ArtistDetailsModel(apiService);
  }
}
