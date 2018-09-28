package com.mackap.awesomefm.screenArtistDetails.artistDetailsDi;

import com.mackap.awesomefm.screenArtistDetails.ArtistDetailsFragment;
import dagger.Subcomponent;

@ArtistDetailsScope
@Subcomponent(modules = {ArtistDetailsPresenterModule.class, ArtistDetailsModelModule.class})
public interface ArtistDetailsSubComponent {
void inject(ArtistDetailsFragment artistDetailsFragment);
}
