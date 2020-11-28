package com.example.challenge.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DiscountValidator implements ConstraintValidator<Discount, Float> {

    public void initialize(Discount discount) {
        // used only if your annotation has attributes
    }

    public boolean isValid(Float discount, ConstraintValidatorContext constraintContext) {
        // Bean Validation specification recommends to consider null values as
        // being valid. If null is not a valid value for an element, it should
        // be annotated with @NotNull explicitly.
        if (discount == null) {
            return true;
        }
        if (discount <= 0.05F) {
            return true;
        }else {
            return false;
        }
    }
}
