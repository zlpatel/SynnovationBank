package edu.asu.secure.SynnovationBank.Dao;

import edu.asu.secure.SynnovationBank.DTO.Role;

public interface RoleDAO {

	public long insertRole(Role role);
	public Role fetchRole(String roleName);
}
