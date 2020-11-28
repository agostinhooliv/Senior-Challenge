package com.example.challenge.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductTypeValidator implements ConstraintValidator<ProductType, Character> {

    public void initialize(ProductType productType) {
        // used only if your annotation has attributes
    }

    public boolean isValid(Character productType, ConstraintValidatorContext constraintContext) {
        // Bean Validation specification recommends to consider null values as
        // being valid. If null is not a valid value for an element, it should
        // be annotated with @NotNull explicitly.
        if (productType == null) {
            return true;
        }
        if (productType.equals('P') || productType.equals('S'))
            return true;
        else {
            return false;
        }

    }
}
