package com.focustech.oss2008.web.validator;

import org.springframework.validation.Errors;

import com.focustech.oss2008.web.AbstractValidator;

public class SgsPaymentConfigValidator extends AbstractValidator {

    public SgsPaymentConfigValidator(Object form, Errors error) {
        super(form, error);
    }

    public void validate() {
        super.checkEmputyObj("product");
        super.checkIsDouble("paymentValue");
        super.checkMoney("standardProdMoney");
    }
}
