package com.mackap.awesomefm.screenStart.startPageDi;

import com.mackap.awesomefm.net.ApiService;
import com.mackap.awesomefm.screenStart.StartMVPContract.IStartModel;
import com.mackap.awesomefm.screenStart.StartModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Makarov Mikhail on 05.09.2018.
 */

@Module
public class StartModelModule {

  @StartPageScope
  @Provides
  IStartModel getStartModel(ApiService apiService) {
    return new StartModel(apiService);
  }
  
}
