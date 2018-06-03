package com.na.coder_compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import retrofit2.Retrofit;

import static com.na.coder_compiler.Utils.getApiClassName;
import static com.na.coder_compiler.Utils.getInjectClassName;
import static com.na.coder_compiler.Utils.getRequestClassName;
import static com.na.coder_compiler.Utils.getResponseClassName;


/**
 * Created by wind on 2018/6/3.
 */

public class UsecasePart {
    private static final String USECASE_SUFFIX="Usecase";
    private static final ClassName SUPERCLASS_TYPENAME=
            ClassName.get("com.wind.base.usecase","RetrofitUsecase");

    private static final ClassName RX_OBSERVABLE_TYPENAME=
            ClassName.get("rx","Observable");


    private String annotatedClassSimpleName;
    private ApiPart apiPart;
    public UsecasePart(TypeElement annotatedElement){
        annotatedClassSimpleName=annotatedElement.getSimpleName().toString();

    }

    public void brewJava(Filer filer, String packageName) throws IOException {

        ParameterizedTypeName returnTypeName=ParameterizedTypeName.get(RX_OBSERVABLE_TYPENAME,
                getResponseClassName(packageName,annotatedClassSimpleName));

        MethodSpec.Builder method=MethodSpec.methodBuilder("buildUsecaseObservable")
                .addParameter(getRequestClassName(packageName,annotatedClassSimpleName),"request")
                .addModifiers(Modifier.PUBLIC)
                .returns(returnTypeName);
        ClassName apiClassName=getApiClassName(packageName,annotatedClassSimpleName);
        method.addStatement("$T api = mRetrofit.create("+apiClassName+".class)",apiClassName);
        String apiMethodName=apiPart.getMethod().getName();

        ClassName mapTransformerClassName=ClassName.get("com.wind.base.utils","MapTransformer");
        method.addStatement("return api."+apiMethodName+"($T.transformObject2Map(request)"+")",mapTransformerClassName);


        ClassName retrofitClassName=ClassName.get(Retrofit.class);
        ClassName injectClassName=getInjectClassName();
        MethodSpec.Builder constructor=MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(injectClassName)
                .addParameter(retrofitClassName,"retrofit")
                .addStatement(" super(retrofit)");



        String className=annotatedClassSimpleName+ USECASE_SUFFIX;

        ParameterizedTypeName parameterizedTypeName=ParameterizedTypeName.get(SUPERCLASS_TYPENAME,
                getRequestClassName(packageName,annotatedClassSimpleName),getResponseClassName(packageName,annotatedClassSimpleName));
        TypeSpec typeSpec=TypeSpec.classBuilder(className)
                .superclass(parameterizedTypeName)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(method.build())
                .addMethod(constructor.build())
                .build();
        JavaFile.builder(packageName,typeSpec).build().writeTo(filer);
    }





    public void setAssociatedApi(ApiPart api){
        this.apiPart=api;
    }
}
