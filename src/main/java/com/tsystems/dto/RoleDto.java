package com.tsystems.dto;

import com.tsystems.entities.Role;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

/**
 * Created by nikita on 03.10.2020.
 */
@Data
public class RoleDto implements DtoMapper<Role>, Comparable<RoleDto> {

    private int id;
    private String roleName;

    @Override
    public String toString() {
        return roleName;
    }

    @Override
    public Role convertToEntity() {
        Role role = new Role();
        role.setId(id);
        role.setRoleName(roleName);
        return role;
    }

    @Override
    public void convertToDto(Role entity) {
        this.id = entity.getId();
        this.roleName = entity.getRoleName();

    }

    @Override
    public DtoMapper addDependencies(Role entity) {
        return null;
    }

    @Override
    public int compareTo(RoleDto o) {
        return ObjectUtils.compare(this.id, o.getId());
    }
}
