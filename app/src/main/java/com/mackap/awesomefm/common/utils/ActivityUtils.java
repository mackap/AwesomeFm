package com.mackap.awesomefm.common.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import javax.inject.Inject;

/**
 * Created by Makarov Mikhail on 04.09.2018.
 */
public class ActivityUtils {

  public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment,
      int container, String tag) {

    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(container, fragment, tag);

    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();

  }

}
