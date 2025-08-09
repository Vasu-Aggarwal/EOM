package xyz.eo.manager.util.enums;

import lombok.Getter;

@Getter
public enum BanquetStatus {
    ACTIVE(1),
    INACTIVE(2),
    DELETED(3),
    IN_PROCESS(4),
    ;

    private final Integer status;
    BanquetStatus(Integer status){
        this.status = status;
    }
}
