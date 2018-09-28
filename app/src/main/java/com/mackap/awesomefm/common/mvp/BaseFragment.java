package com.mackap.awesomefm.common.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.mackap.awesomefm.common.mvp.MVP.IPresenter;

/**
 * Created by Makarov Mikhail 2018.
 */
public abstract class BaseFragment<P extends  IPresenter> extends Fragment {

  public P mBasePresenter;
  public Unbinder mUnbinder;

  @Nullable
  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater,
      @Nullable final ViewGroup container,
      @Nullable final Bundle savedInstanceState) {

    View view = inflater.inflate(getLayoutResource(), container, false);
    mUnbinder = ButterKnife.bind(this, view);
    return view;
  }

  public abstract int getLayoutResource();

  @Override
  public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

  }
  @Override
  public void onResume() {
    super.onResume();
    showCurrentState();
  }

  public void showCurrentState() {
     switch (mBasePresenter.getCurrentScreenState()) {
       case START_SCREEN:
         loadData();
         break;
      case SHOW_DATA:
        showData();
        break;
      case SHOW_ERROR:
        showError();
        break;
      case SHOW_PROGRESS:
        showProgress();
        break;
    }
  }

  private void loadData() {
    mBasePresenter.getData();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mBasePresenter.detachView();
    if (mUnbinder != null) {
      mUnbinder.unbind();
    }
  }

  protected abstract void showProgress();

  protected abstract void showError();

  protected abstract void showData();

}
