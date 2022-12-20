package com.iprwc.webshop.security;

import com.iprwc.webshop.Permission;
import com.iprwc.webshop.dao.UserDAO;
import com.iprwc.webshop.model.Role;
import com.iprwc.webshop.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class RoleFilter extends OncePerRequestFilter {

    private final UserDAO userDAO;

    private final LinkedHashMap<String, String[]> routePermissions;

    public RoleFilter(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.routePermissions = new LinkedHashMap<String, String[]>();

        this.routePermissions.put("GET /api/car",
                new String[]{
                        String.valueOf(Permission.AUTHENTICATE)});
        this.routePermissions.put("POST /api/car",
                new String[]{
                        String.valueOf(Permission.AUTHENTICATE)});
        this.routePermissions.put("GET /api/car/*",
                new String[]{
                        String.valueOf(Permission.AUTHENTICATE)});
        this.routePermissions.put("GET /api/user/info",
                new String[]{
                        String.valueOf(Permission.AUTHENTICATE)});
        this.routePermissions.put("PUT /api/user/*",
                new String[]{
                        String.valueOf(Permission.ADMIN)});
        this.routePermissions.put("GET /api/user/role",
                new String[]{
                        String.valueOf(Permission.ADMIN)});
        this.routePermissions.put("GET /api/user/role/*",
                new String[]{
                        String.valueOf(Permission.ADMIN)});
        this.routePermissions.put("POST /api/user/role",
                new String[]{
                        String.valueOf(Permission.ILLEGAL)});
        this.routePermissions.put("PUT /api/user/role/*",
                new String[]{
                        String.valueOf(Permission.ILLEGAL)});
        this.routePermissions.put("DELETE /api/user/role/*",
                new String[]{
                        String.valueOf(Permission.ILLEGAL)});
    }

    @Override
    public void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String key = request.getMethod() + " " + request.getRequestURI();

        List<Integer> pathVariables = new ArrayList<>();
        for(String value: key.split("/")) {
            try {
                pathVariables.add(Integer.parseInt(value));
            } catch (NumberFormatException e) {
            }
        }

        key = key.replaceAll("\\d+","*");

        boolean access = false;

        if(routePermissions.containsKey(key)) {
            User user = userDAO.getUserDetails();
            String[] needed = routePermissions.get(key);
            List<String> userPermissions = new ArrayList<>();

            for(Role role: user.getRoles()) {
                userPermissions.addAll(Arrays.asList(role.getPermissions()));
            }

            for(String permission: needed) {
                if(userPermissions.contains(permission)) {
                    access = true;
                } else {
                    access = false;
                    break;
                }
            }

            if(!access) {
                response.sendError(
                        HttpServletResponse.SC_FORBIDDEN,
                        String.format("User is unauthorized to use %s on endpoint %s&n", request.getMethod(), request.getRequestURI())
                );
            }
        }

        filterChain.doFilter(request, response);
    }
}
