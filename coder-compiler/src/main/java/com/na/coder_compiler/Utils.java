package com.na.coder_compiler;

import com.squareup.javapoet.ClassName;

/**
 * Created by wind on 2018/6/3.
 */

public class Utils {
    private static final String API_SUFFIX="Api";
    private static final String REQUEST_SUFFIX="Request";
    private static final String RESPONSE_SUFFIX="Response";
    private static final String VIEW_SUFFIX="View";
    private static final String USECASE_SUFFIX="Usecase";
    private static final String SUBSCRIBER_SUFFIX="Subscriber";

    public static ClassName getApiClassName(String packageName,String simpleName) {
        String apiSimpleName=simpleName+ API_SUFFIX;
        return ClassName.get(packageName,apiSimpleName);
    }

    public static ClassName getResponseClassName(String packageName,String simpleName){
        String responseSimpleName=simpleName+RESPONSE_SUFFIX;
        ClassName responseClassName=ClassName.get(packageName,responseSimpleName);
        return responseClassName;
    }
    public static ClassName getRequestClassName(String packageName,String simpleName){
        String requestSimpleName=simpleName+REQUEST_SUFFIX;
        ClassName requestClassName=ClassName.get(packageName,requestSimpleName);
        return requestClassName;
    }
    public static ClassName getViewClassName(String packageName,String simpleName){
        ClassName viewClassName=ClassName.get(packageName,simpleName+VIEW_SUFFIX);
        return viewClassName;
    }

    public static ClassName getSubscriberClassName(String packageName,String simpleName){
        ClassName subscriberClassName=ClassName.get(packageName,simpleName+SUBSCRIBER_SUFFIX);
        return subscriberClassName;
    }
    public static ClassName getUsecaseClassName(String packageName,String simpleName){
        ClassName viewClassName=ClassName.get(packageName,simpleName+USECASE_SUFFIX);
        return viewClassName;
    }
    public static ClassName getUsecaseComponentClassName(){
        ClassName className=ClassName.get(" com.wind.base.usecase",
                "UsecaseCompoment");
        return className;

    }

    public static ClassName getInjectClassName(){
        ClassName injectClassName=ClassName.get("javax.inject","Inject");
        return injectClassName;
    }

    public static ClassName getNextObservableClassName(){
        ClassName nextObservableClassName=ClassName.get("com.wind.base.subscriber",
                "NextObserver");
        return nextObservableClassName;
    }

    public static ClassName getExecutePresenterClassName(){
        ClassName executePresenterClassName=ClassName.get("com.wind.base.mvp.presenter",
                "ExecutePresenter");
        return executePresenterClassName;
    }
}
