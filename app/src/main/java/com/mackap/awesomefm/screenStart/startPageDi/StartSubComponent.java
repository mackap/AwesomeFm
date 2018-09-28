package com.mackap.awesomefm.screenStart.startPageDi;

import com.mackap.awesomefm.screenStart.StartPageFragment;
import dagger.Subcomponent;

/**
 * Created by Makarov Mikhail on 2018.
 */
@StartPageScope
@Subcomponent(modules = {StartPresenterModule.class, StartModelModule.class})
public interface StartSubComponent {
  void inject(StartPageFragment startPageFragment);
}
