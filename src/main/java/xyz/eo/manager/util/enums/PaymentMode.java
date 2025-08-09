package xyz.eo.manager.util.enums;

import lombok.Getter;

@Getter
public enum PaymentMode {
    CASH(1),
    UPI(2),
    NEFT(3),
    RTGS(4),
    CHEQUE(5);

    private final Integer paymentMode;

    PaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
    }
}
