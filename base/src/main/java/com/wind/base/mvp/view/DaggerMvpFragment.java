package com.wind.base.mvp.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.wind.base.di.DaggerComponent;
import com.wind.base.di.HasComponent;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by wind on 16/9/12.
 */

public abstract class DaggerMvpFragment<V extends MvpView,P extends MvpPresenter<V>,C extends DaggerComponent> extends
        MvpFragment<V,P>{


    private C mComponent;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();

        //FragmentArgs.inject(this);
    }



    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Icepick.restoreInstanceState(this, savedInstanceState);
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    private void injectDependencies() {
        if (getActivity() instanceof HasComponent){
            mComponent = ((HasComponent<C>) getActivity()).getComponent();
        }else {
            mComponent=createComponent();
        }
        if (mComponent==null){
            throw new NullPointerException("Component can not be null! you should override createComponent method");
        }
        inject();
    }

    protected abstract void inject();

    /**
     * 如果activity不是HasComponent类型，那么fragment需要自己createComponent();
     * @return
     */
    protected  C createComponent(){
        return null;
    }

    public C getComponent() {
        return mComponent;
    }
    @LayoutRes
    protected abstract int getLayoutRes();

    @Override public void onDestroyView() {
        super.onDestroyView();
        //ButterKnife.unbind(this);
    }


}
