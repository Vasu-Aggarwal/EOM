package xyz.eo.manager.util;

import lombok.Getter;

@Getter
public final class ErrorMessage {

    public final static String RESOURCE_NOT_FOUND = "Resource not found";
    public final static String INTERNAL_SERVER_ERROR = "Internal Server Error";

    /**
     * Banquet Error Messages
     */
    public final static String NOT_AUTHORIZED_TO_ASSIGN_ADMINS = "You are not authorized to assign Admin(s)";
    public final static String NOT_AUTHORIZED_TO_ADD_BANQUET = "You are not authorized to create a banquet";
    public final static String NOT_AUTHORIZED_TO_UPDATE_BANQUET = "You are not authorized to update this banquet";
    public final static String BANQUET_NOT_ACTIVE = "Banquet is not in Active state";
    public final static String LINK_ADMIN_ALREADY_PRESENT_INACTIVE_DELETED = "Admin is already present either in inactive or deleted state. Try again after updating the user state to active!";
    public final static String USER_ALREADY_ACTIVE = "User already in active state";
}
