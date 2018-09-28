package com.mackap.awesomefm.common.utils;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.mackap.awesomefm.common.IRecItemClickListener;

/**
 * Created by Makarov Mikhail on 2018.
 */
public class UiUtils {

  private final static RequestOptions options = new RequestOptions()
      .centerCrop()
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .priority(Priority.HIGH)
      .dontAnimate()
      .dontTransform();;
  IRecItemClickListener mCallback;

  public static RequestOptions getRequestOptionsForGlide() {
    return options;
  }


}
