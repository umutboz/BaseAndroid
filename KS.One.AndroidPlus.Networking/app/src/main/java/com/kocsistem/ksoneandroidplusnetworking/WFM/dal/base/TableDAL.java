package com.kocsistem.ksoneandroidplusnetworking.WFM.dal.base;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
public @interface TableDAL {

        String tag() default "";
        String alter_columns() default  "";

}

