package com.na.coder_compiler;

import com.google.auto.service.AutoService;
import com.wind.coder.annotations.Api;
import com.wind.coder.annotations.Subscriber;
import com.wind.coder.annotations.Usecase;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class CoderProcessor extends AbstractProcessor {

    private Filer filer;
    private Elements mElementUtils;
    private Types mTypeUtils;
    private Messager mMessager;

    private Map<TypeElement, CoderItem> mCoderMap = new LinkedHashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        filer = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
        mTypeUtils = processingEnvironment.getTypeUtils();
        mMessager = processingEnvironment.getMessager();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {

        Set<String> types = new LinkedHashSet<>();
        for (Class clz : getSupportAnnotations()) {
            types.add(clz.getCanonicalName());
        }
        return types;
    }


    public Set<Class> getSupportAnnotations() {
        Set<Class> annotations = new LinkedHashSet<>();
        annotations.add(Usecase.class);
        annotations.add(Api.class);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {


        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(Api.class)) {
            if (isValid(annotatedElement)) {
                TypeElement typeAnnotatedElement = (TypeElement) annotatedElement;
                PackageElement packageElement = mElementUtils.getPackageOf(typeAnnotatedElement);
                String packageName=packageElement.isUnnamed()?null:packageElement.getQualifiedName().toString();
                CoderItem item = mCoderMap.get(annotatedElement);
                if (item == null) {
                    item = new CoderItem();
                    item.setPackageName(packageName);
                    mCoderMap.put(typeAnnotatedElement, item);
                }
                ApiPart apiPart = new ApiPart(typeAnnotatedElement);
                item.setApi(apiPart);

            }
        }
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(Usecase.class)) {
            if (isValid(annotatedElement)) {
                TypeElement typeAnnotatedElement = (TypeElement) annotatedElement;
                PackageElement packageElement = mElementUtils.getPackageOf(typeAnnotatedElement);
                String packageName=packageElement.isUnnamed()?null:packageElement.getQualifiedName().toString();
                CoderItem item = mCoderMap.get(annotatedElement);
                if (item == null) {
                    item = new CoderItem();
                    item.setPackageName(packageName);
                    mCoderMap.put(typeAnnotatedElement, item);
                }
                UsecasePart usecasePart=new UsecasePart(typeAnnotatedElement);
                item.setUsecase(usecasePart);

            }
        }
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(Subscriber.class)) {
            if (isValid(annotatedElement)) {
                TypeElement typeAnnotatedElement = (TypeElement) annotatedElement;
                PackageElement packageElement = mElementUtils.getPackageOf(typeAnnotatedElement);
                String packageName=packageElement.isUnnamed()?null:packageElement.getQualifiedName().toString();
                CoderItem item = mCoderMap.get(annotatedElement);
                if (item == null) {
                    item = new CoderItem();
                    item.setPackageName(packageName);
                    mCoderMap.put(typeAnnotatedElement, item);
                }
                SubscriberPart subscriberPart=new SubscriberPart(typeAnnotatedElement);

                item.setSubscriber(subscriberPart);

            }
        }
        for (CoderItem item : mCoderMap.values()) {
            try {
                item.brewJava(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mCoderMap.clear();
        return true;
    }

    private boolean isValid(Element annotatedElement) {
        if (annotatedElement.getKind() != ElementKind.CLASS ) {
            error(annotatedElement, "annotation Api must be used on class or interface");
            return false;
        }
        return true;
    }

    private void error(Element annotatedElement, String msg) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, msg, annotatedElement);
    }
}
