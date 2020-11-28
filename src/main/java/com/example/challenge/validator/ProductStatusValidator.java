package com.example.challenge.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductStatusValidator implements ConstraintValidator<ProductStatus, Character> {

    public void initialize(ProductStatus productStatus) {
        // used only if your annotation has attributes
    }

    public boolean isValid(Character productStatus, ConstraintValidatorContext constraintContext) {
        // Bean Validation specification recommends to consider null values as
        // being valid. If null is not a valid value for an element, it should
        // be annotated with @NotNull explicitly.
        if (productStatus == null) {
            return true;
        }
        if (productStatus.equals('A') || productStatus.equals('I'))
            return true;
        else {
            return false;
        }
    }
}
