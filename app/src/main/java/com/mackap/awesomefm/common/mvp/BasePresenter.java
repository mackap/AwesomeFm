package com.mackap.awesomefm.common.mvp;

import com.mackap.awesomefm.common.ScreenState;
import com.mackap.awesomefm.common.mvp.MVP.IPresenter;
import com.mackap.awesomefm.common.mvp.MVP.IView;

/**
 * Created by Makarov Mikhail on 2018.
 */
public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

 public T mView;
// public CompositeDisposable mCompositeDisposable;


  public ScreenState currentScreenState = ScreenState.START_SCREEN;

  @Override
  public void attachView(final T view) {
    mView = view;
  }

  @Override
  public void detachView() {
    mView = null;
    /**
    if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.clear();
    }
     */
  }

  @Override
  public ScreenState getCurrentScreenState() {
    return currentScreenState;
  }
}
