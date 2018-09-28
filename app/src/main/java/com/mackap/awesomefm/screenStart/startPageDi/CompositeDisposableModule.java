package com.mackap.awesomefm.screenStart.startPageDi;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Makarov Mikhail on 14.09.2018.
 */
@Module
public class CompositeDisposableModule {

  @Provides
  CompositeDisposable provideCompositDisposable() {
    return new CompositeDisposable();
  }
}
