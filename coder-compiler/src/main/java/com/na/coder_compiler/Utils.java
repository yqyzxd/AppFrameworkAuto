package com.na.coder_compiler;

import com.squareup.javapoet.ClassName;

/**
 * Created by wind on 2018/6/3.
 */

public class Utils {
    private static final String REQUEST_SUFFIX="Request";
    private static final String RESPONSE_SUFFIX="Response";
    public static final String VIEW_SUFFIX="View";
    public static ClassName getApiClassName(String packageName,String simpleName) {
        String apiSimpleName=simpleName+ApiPart.API_SUFFIX;
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


    public static ClassName getInjectClassName(){
        ClassName injectClassName=ClassName.get("javax.inject","Inject");
        return injectClassName;
    }

    public static ClassName getNextObservableClassName(){
        ClassName nextObservableClassName=ClassName.get("com.wind.base.subscriber",
                "NextObserver");
        return nextObservableClassName;
    }
}
