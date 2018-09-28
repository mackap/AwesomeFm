package com.mackap.awesomefm.screenStart.startPageDi;

import com.mackap.awesomefm.screenStart.StartMVPContract.IStartModel;
import com.mackap.awesomefm.screenStart.StartMVPContract.IStartPresenter;
import com.mackap.awesomefm.screenStart.StartPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Makarov Mikhail on 2018.
 */

@Module
public class StartPresenterModule {
  @StartPageScope
  @Provides
  IStartPresenter getStartPresenter(IStartModel startModel) {
    IStartPresenter presenter = new StartPresenter(startModel);

    return presenter;
  }
}
