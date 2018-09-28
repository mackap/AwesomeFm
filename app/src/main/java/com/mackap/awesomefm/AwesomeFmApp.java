package com.mackap.awesomefm;

import android.app.Application;

import com.mackap.awesomefm.di.DaggerBaseComponent;
import com.mackap.awesomefm.screenArtistDetails.artistDetailsDi.ArtistDetailsSubComponent;
import com.mackap.awesomefm.di.BaseComponent;
import com.mackap.awesomefm.screenStart.startPageDi.StartSubComponent;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Makarov Mikhail on 2018.
 */
public class AwesomeFmApp extends Application {

  BaseComponent mBaseComponent;
  StartSubComponent mStartSubComponent;
  ArtistDetailsSubComponent mArtistDetailsSubComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return;
    }
    LeakCanary.install(this);

    initComponent();

  }

  private void initComponent() {
    mBaseComponent = DaggerBaseComponent.builder().build();
  }

  public StartSubComponent getStartSubComponent() {
    if (mStartSubComponent == null) {
      mStartSubComponent = mBaseComponent.plusStartSubComponent();
    }
    return mStartSubComponent;
  }

  public ArtistDetailsSubComponent getArtistDetailsSubComponent() {
    if (mArtistDetailsSubComponent == null) {
      mArtistDetailsSubComponent = mBaseComponent.plusArtistSubComponent();
    }
    return mArtistDetailsSubComponent;
  }
}
