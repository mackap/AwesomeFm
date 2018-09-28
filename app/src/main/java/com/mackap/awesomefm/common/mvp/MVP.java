package com.mackap.awesomefm.common.mvp;


import com.mackap.awesomefm.common.ScreenState;

/**
 * Created by Makarov Mikhail on 05.09.2018.
 */
public interface MVP {

  interface IView{

    void showProgress();

    void hideProgress();

    void showError();

    void showData();

  }

  interface IPresenter<T extends IView> {

    void attachView(T view);

    void detachView();

    ScreenState getCurrentScreenState();

    void getData();
  }

  interface IModel {

  }
}
