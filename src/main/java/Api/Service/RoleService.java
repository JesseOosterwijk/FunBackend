package Api.Service;

import Api.Entity.Role;
import Api.JpaRepository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createOrUpdateRole(Role role) {
        try {
            roleRepository.save(role);
        } catch(Exception e) {
            throw e;
        }
    }

    public void deleteRole(Role role) {
        try {
            roleRepository.delete(role);
        } catch(Exception e) {
            throw e;
        }
    }

    public Role findRoleById(int id) {
        try {
            return roleRepository.findRoleById(id);
        } catch(Exception e) {
            throw e;
        }
    }

}
