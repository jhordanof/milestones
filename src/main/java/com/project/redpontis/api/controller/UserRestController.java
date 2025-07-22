package com.project.redpontis.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.project.redpontis.api.interfaces.IUserRestController;
import com.project.redpontis.dto.UserDTO;
import com.project.redpontis.mapper.DtoMapper;
import com.project.redpontis.service.UserService;

@RestController
public class UserRestController implements IUserRestController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private UserService userService;

    @Autowired
    private DtoMapper dtoMapper;

    @Override
    public ResponseEntity<UserDTO> register(UserDTO dto) {
        var user = userService.register(dto);
        return ResponseEntity.ok(dtoMapper.toUserDTO(user));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(
                userService.getAllUsers().stream().map(dtoMapper::toUserDTO).toList()
        );
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(Long id) {
        return userService.getUserById(id)
                .map(dtoMapper::toUserDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(Long id, UserDTO dto) {
        var updated = new com.project.redpontis.entity.User();
        updated.setUsername(dto.getUsername());
        updated.setPassword(dto.getPassword());
        updated.setRole(Enum.valueOf(com.project.redpontis.entity.Role.class, dto.getRole().toUpperCase()));
        return userService.updateUser(id, updated)
                .map(dtoMapper::toUserDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        /*return userService.deleteUser(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();*/
    	log.info("[Eliminar Usuario] -> ID {}", id);
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
        	log.info("Usuario Eliminado");
            return ResponseEntity.noContent().build();
        } else {
        	log.warn("No se pudo eliminar usuario con ID {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}