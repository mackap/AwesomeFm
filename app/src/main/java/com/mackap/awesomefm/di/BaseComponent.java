package com.mackap.awesomefm.di;

import com.mackap.awesomefm.screenArtistDetails.artistDetailsDi.ArtistDetailsSubComponent;
import com.mackap.awesomefm.screenStart.startPageDi.StartSubComponent;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Makarov Mikhail on 2018.
 */
@Component(modules = {ApiServiceModule.class})
@Singleton
public interface BaseComponent {
  StartSubComponent plusStartSubComponent();
  ArtistDetailsSubComponent plusArtistSubComponent();
}
