package uz.pdp.app_new_site.entity.enums;

public enum Permission {


    // ADMIN
    ADD_USER,
    EDIT_USER,
    DELETE_USER,
    VIEW_USERS,

    // ADMIN
    EDIT_ROLE,
    DELETE_ROLE,
    VIEW_ROLES,

    // USER PERMISSION
    ADD_POST,     // ADMIN
    EDIT_POST,    // ADMIN
    DELETE_POST,  // ADMIN


    ADD_COMMENT,
    EDIT_COMMENT,
    DELETE_MY_COMMENT,

    // RIGHTS OF USER SAVED AS ADMIN OR EDITOR
    DELETE_COMMENT,

}
