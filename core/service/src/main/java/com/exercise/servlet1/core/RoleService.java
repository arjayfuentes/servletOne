package com.exercise.servlet1.core;

import java.util.*;

public class RoleService {

    Validation check = new Validation();
    RoleDao roleDao = new RoleDao();

    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    public Role getRole(long roleId) {
        return roleDao.getRole(roleId);
    }

    public boolean checkRoleIfExist(long roleId) {
        if (roleDao.getRole(roleId) == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean checkRoleNameIfExist(String roleName){
    	boolean exist= false;
    	List<Role> roles = getRoles();
    	for(Role r : roles){
    		if(r.getRoleName().equalsIgnoreCase(roleName)){
    			exist=true;
    			break;
    		}
    	}
    	return exist;
    }

    public Set<Role> getPersonRoles(String personId) {
        return roleDao.getPersonRoles(personId);
    }

    public Role getPersonRole(String personId, long roleId) {
        return roleDao.getPersonRole(personId, roleId);
    }

    public boolean checkPersonRoleIfExist(String personId, long roleId) {
        if (roleDao.getPersonRole(personId, roleId) == null) {
            return false;
        } else {
            return true;
        }
    }

    //option 2 done
    public void addPersonRole(String personId, Role role) {
        roleDao.addPersonRole(personId, role);
    }

    //option 3 done
    public void deletePersonRole(String personId, long roleId) {
        roleDao.deletePersonRole(personId, roleId);
    }

    //option 5 done
    public void addRole(String roleName) {
        Role role = new Role(roleName);
        roleDao.addRole(role);
    }

    //option 6 done

    public void updateRole(long roleId, String updatedRole) {
        roleDao.updateRole(roleId, updatedRole);
    }

    public long checkInputRole(String message){
        long id = 0;
        do{
            id = check.inputIdNumber(message);
            if(checkRoleIfExist(id)==false){
                System.out.print(" Id number not exist! ");
            }
        }while(checkRoleIfExist(id)==false);
        return id;
    }

    public long checkInputRolePerson(String message, String personId){
        long roleId = 0;
        do{
            roleId = check.inputIdNumber(message);
            if(checkPersonRoleIfExist(personId,roleId)==false){
                System.out.print(" role for this person not exist! ");
            }
        }while(checkPersonRoleIfExist(personId,roleId)==false);
        return roleId;
    }
    
    public void deleteRole(long roleId){
    	roleDao.deleteRole(roleId);
    }

}
