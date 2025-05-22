package xyz.eo.manager.util.enums;

import lombok.Getter;

@Getter
public enum UserBanquetStatus {
    ACTIVE(1),
    INACTIVE(2),
    DELETED(3),
    ;

    private final Integer status;
    UserBanquetStatus(Integer status){
        this.status = status;
    }
}
