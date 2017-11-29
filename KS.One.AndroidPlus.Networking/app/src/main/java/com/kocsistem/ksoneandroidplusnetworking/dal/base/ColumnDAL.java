package com.kocsistem.ksoneandroidplusnetworking.dal.base;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) //on class level
public @interface ColumnDAL {
    String tag() default "";
    boolean isNull() default true;

}