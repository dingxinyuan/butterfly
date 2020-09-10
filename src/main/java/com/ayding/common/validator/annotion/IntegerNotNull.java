package com.ayding.common.validator.annotion;

import com.ayding.common.validator.Messages;
import com.ayding.common.validator.constraint.IntegerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author: ayding
 * @date: 2019/9/30 21:36
 */
@Target({TYPE, ANNOTATION_TYPE,FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IntegerValidator.class})
public @interface  IntegerNotNull {

    String message() default Messages.CK_NUMERIC_DEFAULT;

    String value() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
