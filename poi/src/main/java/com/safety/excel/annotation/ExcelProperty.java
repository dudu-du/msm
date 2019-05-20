package com.safety.excel.annotation;

import java.lang.annotation.*;

/**
 * @author jipengfei
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelProperty {

     /**
      * @return
      */
     String[] value() default {""};


     /**
      * @return
      */
     int index() default 99999;

     /**
      *
      * default @see com.safety.excel.util.TypeUtil
      * if default is not  meet you can set format
      *
      * @return
      */
     String format() default "";
}
