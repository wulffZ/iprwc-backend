package com.iprwc.webshop.service;

import com.google.gson.Gson;
import com.iprwc.webshop.Permission;
import com.iprwc.webshop.dao.RoleDAO;
import com.iprwc.webshop.model.Role;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    private ArrayList<Role> standardRoles;
    private final RoleDAO roleDAO;

    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createRoles() {
        if(!this.roleDAO.index().isEmpty()) {
            standardRoles = this.roleDAO.index();
            return;
        }

        ArrayList<Role> defaultRoles = new ArrayList<>();

        defaultRoles.add(new Role("Admin", "The guy that can break it all",
                "%s".formatted(
                        Permission.ADMIN)));
        defaultRoles.add(new Role("Secretary", "Can plan reservations for others",
                "%s,%s,%s".formatted(
                        Permission.RESERVATION_WRITE_OTHER, Permission.RESERVATION_DELETE_OTHER, Permission.RESERVATION_UPDATE_OTHER)));
        defaultRoles.add(new Role("User", "The normal guy",
                "%s,%s,%s,%s,%s".formatted(
                        Permission.AUTHENTICATE, Permission.RESERVATION_READ, Permission.RESERVATION_WRITE,
                        Permission.RESERVATION_UPDATE, Permission.RESERVATION_DELETE)));
        defaultRoles.add(new Role("Disabled user", "Can't do anything",
                "%s".formatted(
                        Permission.NONE)));

        this.standardRoles = defaultRoles;
        for(Role role: standardRoles) {
            this.roleDAO.store(role);
        }
    }

    public List<Role> getRoles() {
        return standardRoles;
    }

    public String toJsonRoleString(List<Role> roles) {
        Gson gson = new Gson();
        return gson.toJson(roles);
    }

}
