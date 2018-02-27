package com.wind.base.usecase;

import com.wind.base.response.PageResponse;
import com.wind.base.http.loader.PageLoader;
import com.wind.base.request.PageRequest;

import rx.Observable;

/**
 * Created by wind on 2017/2/27.
 */

public abstract class PageUsecase<Q extends PageRequest,R extends PageResponse> extends
        Usecase<Q,R> {

    private PageLoader mPageLoader;
    public PageUsecase(PageLoader pageLoader){
        this.mPageLoader=pageLoader;
    }
    @Override
    protected Observable<R> buildUsecaseObservable(Q request) {
        return mPageLoader.load(request);
    }
}
