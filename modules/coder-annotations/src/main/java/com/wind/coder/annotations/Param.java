package com.wind.coder.annotations;

public @interface Param {

    String viewCanonicalName();
    String responseCanonicalName();
    String requestCanonicalName();

    String baseName() default "";
    String basePackage() default "";
}
