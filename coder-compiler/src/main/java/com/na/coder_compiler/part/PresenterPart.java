package com.na.coder_compiler.part;

import com.na.coder_compiler.Utils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import static com.na.coder_compiler.Utils.getExecutePresenterClassName;
import static com.na.coder_compiler.Utils.getInjectClassName;
import static com.na.coder_compiler.Utils.getSubscriberClassName;
import static com.na.coder_compiler.Utils.getUsecaseClassName;
import static com.na.coder_compiler.Utils.getViewClassName;

/**
 * Created by wind on 2018/6/3.
 */

public class PresenterPart {
    public static final String PRESENTER_SUFFIX="Presenter";

    private String annotatedClassSimpleName;
    public PresenterPart(TypeElement typeAnnotatedElement) {
        annotatedClassSimpleName=typeAnnotatedElement.getSimpleName().toString();
    }


    public void brewJava(Filer filer, String packageName) throws IOException {

        ClassName usecaseClassName=getUsecaseClassName(packageName,annotatedClassSimpleName);
        String simpleName=usecaseClassName.simpleName();
        String fieldName="m"+simpleName;

        String paramName=simpleName.substring(0,1).toLowerCase()+simpleName.substring(1,simpleName.length());
        MethodSpec.Builder constructor=MethodSpec.constructorBuilder()
                .addAnnotation(getInjectClassName())
                .addModifiers(Modifier.PUBLIC)
                .addParameter(usecaseClassName,paramName)
                .addStatement("this."+fieldName+"="+paramName);

        String subscriberSimpleClassName=annotatedClassSimpleName+PRESENTER_SUFFIX;
        ParameterizedTypeName parameterizedTypeName=ParameterizedTypeName.get(getExecutePresenterClassName(),
                getViewClassName(packageName,annotatedClassSimpleName));


        MethodSpec.Builder attachViewMethod=MethodSpec.methodBuilder("attachView")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(getViewClassName(packageName,annotatedClassSimpleName),"view")
                .addStatement("super.attachView(view)")
                .addStatement("$T subscriber=new $T(view)",
                        getSubscriberClassName(packageName,annotatedClassSimpleName),
                        getSubscriberClassName(packageName,annotatedClassSimpleName))
                .addStatement("$T component=new $T(subscriber,"+fieldName+")", Utils.getUsecaseComponentClassName(),
                        Utils.getUsecaseComponentClassName())
                .addStatement("manager.addUsecaseCompoment(component)");



        FieldSpec.Builder field=FieldSpec.builder(usecaseClassName,fieldName,Modifier.PRIVATE);
        TypeSpec.Builder typeSpec=TypeSpec.classBuilder(subscriberSimpleClassName)
                .superclass(parameterizedTypeName)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(constructor.build())
                .addMethod(attachViewMethod.build())
                .addField(field.build());



        JavaFile.builder(packageName,typeSpec.build()).build().writeTo(filer);
    }


}
