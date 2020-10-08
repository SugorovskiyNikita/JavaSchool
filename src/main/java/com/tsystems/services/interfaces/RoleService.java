package com.tsystems.services.interfaces;

import com.tsystems.dto.RoleDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by nikita on 08.10.2020.
 */
@Service
public interface RoleService<T> extends Serializable {
    RoleDto getRoleById(Integer id);
}
