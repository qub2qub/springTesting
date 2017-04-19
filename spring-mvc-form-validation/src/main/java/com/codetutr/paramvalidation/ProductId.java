package com.codetutr.paramvalidation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@NotBlank
@Size(min = 5, max = 5)
@Digits(integer = 5, fraction = 0)
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, LOCAL_VARIABLE })
// specifies where this validation can be used (Field, Method, Parameter etc)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@ReportAsSingleViolation
// specifies if any of the validation fails, it will be reported as single validation
public @interface ProductId {
 
    /**
     * This is the key to message will that will be looked in validation.properties for validation
     * errors
     * 
     * @return the string
     */
    String message() default "{invalid.product.id}";
 
    Class[] groups() default {};
 
    Class[] payload() default {};
}