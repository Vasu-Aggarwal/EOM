package xyz.eo.manager.util.enums;

import lombok.Getter;

@Getter
public enum FunctionType {
    BIRTHDAY(1),
    WEDDING(2),
    RING_CEREMONY(3),
    ROKA(4),
    OTHER(5);

    private final Integer eventType;
    FunctionType(Integer eventType){
        this.eventType = eventType;
    }
}
