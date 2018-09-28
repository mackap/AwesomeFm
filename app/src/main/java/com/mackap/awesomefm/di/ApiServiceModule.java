package com.mackap.awesomefm.di;

import com.mackap.awesomefm.net.ApiService;
import com.mackap.awesomefm.net.RestClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Makarov Mikhail on 2018.
 */
@Module

public class ApiServiceModule {
@Provides
@Singleton
ApiService getRestClient(){
  return RestClient.getApiService();
}
}
