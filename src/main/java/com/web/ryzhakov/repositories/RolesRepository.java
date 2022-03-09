package com.web.ryzhakov.repositories;

import com.web.ryzhakov.models.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface RolesRepository  {

    Role getRoleByName(String name);

    Role getRoleById(int id);

    List<Role> allRoles();

    HashSet<Role> getSetOfRoles(String[] roleSet);

    Set<Role> setRoleByName(String name, String[] rolesName);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRole(int id);
}



