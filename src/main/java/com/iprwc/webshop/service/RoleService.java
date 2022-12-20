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

        defaultRoles.add(new Role("Admin", "Super user that has all capabilities available in the application.",
                "%s".formatted(
                        Permission.ADMIN)));
        defaultRoles.add(new Role("User", "Regular user that can view items, and buy items in the application.",
                "%s,%s".formatted(
                        Permission.AUTHENTICATE, Permission.SHOP)));

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
