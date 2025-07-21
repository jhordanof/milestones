package com.project.redpontis.api.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.project.redpontis.api.interfaces.ILoginRestController;
import com.project.redpontis.dto.LoginDTO;
import com.project.redpontis.dto.UserDTO;
import com.project.redpontis.entity.User;
import com.project.redpontis.mapper.DtoMapper;
import com.project.redpontis.security.JwtUtils;
import com.project.redpontis.service.AuthService;
import com.project.redpontis.service.UserService;

@RestController
public class LoginRestController implements ILoginRestController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private DtoMapper dtoMapper;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Override
    public ResponseEntity<?> login(LoginDTO loginDTO) {
    	/*Optional<User> userOpt = authService.login(loginDTO.getUsername(), loginDTO.getPassword());

    	if (userOpt.isPresent()) {
    	    User user = userOpt.get();
    	    UserDTO dto = new UserDTO();
    	    dto.setId(user.getId());
    	    dto.setUsername(user.getUsername());
    	    dto.setRole(user.getRole().name());
    	    return ResponseEntity.ok(dto);
    	} else {
    	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o password invalidas");
    	}*/
        Optional<User> userOpt = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if (userOpt.isPresent()) {
            String token = jwtUtils.generateToken(userOpt.get());
            return ResponseEntity.ok(Map.of(
                "token", token,
                "username", userOpt.get().getUsername(),
                "role", userOpt.get().getRole().name()
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
    
    @Override
    public ResponseEntity<UserDTO> register(UserDTO dto) {
        User user = userService.register(dto);
        return ResponseEntity.ok(dtoMapper.toUserDTO(user));
    }
}