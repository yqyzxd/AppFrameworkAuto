package com.na.coder_compiler;

import java.io.IOException;

import javax.annotation.processing.Filer;

/**
 * Created by wind on 2018/6/3.
 */

public class CoderItem {



    private ApiPart api;
    private UsecasePart usecase;
    private String packageName;
    private SubscriberPart subscriber;


    public void setApi(ApiPart api) {
        this.api = api;
    }

    public void brewJava(Filer filer) throws IOException {
        api.brewJava(filer,packageName);
        usecase.brewJava(filer,packageName);
        if (subscriber!=null){
            subscriber.brewJava(filer,packageName);
        }
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setUsecase(UsecasePart usecase) {
        this.usecase = usecase;
        this.usecase.setAssociatedApi(api);
    }

    public void setSubscriber(SubscriberPart subscriber) {
        this.subscriber = subscriber;
    }
}
