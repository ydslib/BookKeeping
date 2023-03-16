/**
 * Created by : yds
 * Time: 2022-09-19 14:03
 */
package com.crystallake.login;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface ASMTest {
}
