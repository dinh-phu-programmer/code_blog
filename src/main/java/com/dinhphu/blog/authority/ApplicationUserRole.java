package com.dinhphu.blog.authority;


import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dinhphu.blog.authority.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    NORMAL_USER(new String[]{USER_READ.getPermission()}),
    ADMIN_USER(new String[]{USER_CREATE.getPermission(),USER_DELETE.getPermission(),USER_WRITE.getPermission(),USER_READ.getPermission()});
//    ADMIN_USER(Sets.newHashSet(USER_CREATE,USER_DELETE,USER_WRITE,USER_READ));

    private String[] permissions;
    ApplicationUserRole(String[] permissions){
        this.permissions=permissions;
    }

    public String[] getRoles(){
        return this.permissions;
    }


}
