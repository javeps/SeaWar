package com.toosNets.netty.byteInput;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bowlong.netty.bio2g.B2G;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface B2Method {
	String type() default B2G.SERVER;
	boolean isAsynchronous() default false;
	String[] params();

	String remark() default "";
}
