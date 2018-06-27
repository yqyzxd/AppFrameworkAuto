package com.na.coder_compiler.part;

import com.na.coder_compiler.Utils;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.wind.coder.annotations.Subscriber;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import static com.na.coder_compiler.Utils.getNextObservableClassName;
import static com.na.coder_compiler.Utils.getResponseClassName;
import static com.na.coder_compiler.Utils.getViewClassName;

/**
 * Created by wind on 2018/6/3.
 */

public class SubscriberPart {
    public static final String SUBSCRIBER_SUFFIX="Subscriber";

    private String annotatedClassSimpleName;
    private String packageName;
    public SubscriberPart(TypeElement annotatedElement) {
        annotatedClassSimpleName=annotatedElement.getSimpleName().toString();

        Subscriber subscriber=annotatedElement.getAnnotation(Subscriber.class);
        packageName=subscriber.packageName();
        if ("".equals(packageName)|| null==packageName){
            packageName= Utils.getPackageElement(annotatedElement).getQualifiedName().toString();
        }
    }


    public void brewJava(Filer filer) throws IOException {

        MethodSpec.Builder constructor=MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(getViewClassName(packageName,annotatedClassSimpleName),"mvpView")
                .addStatement(" super(mvpView)");

        String subscriberSimpleClassName=annotatedClassSimpleName+SUBSCRIBER_SUFFIX;
        ParameterizedTypeName parameterizedTypeName=ParameterizedTypeName.get(getNextObservableClassName(),
                getViewClassName(packageName,annotatedClassSimpleName),getResponseClassName(packageName,annotatedClassSimpleName));
        TypeSpec.Builder typeSpec=TypeSpec.classBuilder(subscriberSimpleClassName)
                .superclass(parameterizedTypeName)
                .addMethod(constructor.build());

        JavaFile.builder(packageName,typeSpec.build()).build().writeTo(filer);
    }


}
