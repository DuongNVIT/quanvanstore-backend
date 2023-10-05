package com.quanvanstorebackend.service;

import com.quanvanstorebackend.dto.UserDTO;
import com.quanvanstorebackend.entity.UserEntity;
import com.quanvanstorebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<UserRepository, UserEntity> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserDTO userToCreate) {
        UserEntity entityToCreate = new UserEntity(userToCreate);
        entityToCreate.setPassword(passwordEncoder.encode(entityToCreate.getPassword()));
        entityToCreate.setCreatedDate(LocalDateTime.now());
        UserEntity createdUser = save(entityToCreate);
        return new UserDTO(createdUser);
    }

    public UserDTO getUserInfor(String userCode) {
        UserEntity userEntity = findByCode(userCode);
        return new UserDTO(userEntity);
    }

    public void updateUser(UserDTO userToUpdate) {
        UserEntity entityToUpdate = findByCode(userToUpdate.getCode());
        // TODO update user information

        entityToUpdate.setFullname(userToUpdate.getFullname());
        update(entityToUpdate);
    }

    public void toggleDeleted(String userCode) {
        UserEntity userToToggle = repository.findByCode(userCode);
        userToToggle.setDeleted(!userToToggle.getDeleted());
        update(userToToggle);
    }

    public void toggleDeleted(Long userId) {
        UserEntity userToToggle = repository.findById(userId).get();
        userToToggle.setDeleted(!userToToggle.getDeleted());
        update(userToToggle);
    }

    public List<UserDTO> getListUser(Pageable pageable) {
        List<UserEntity> userEntities = repository.findByRoleId(2l);
        return userEntities.stream().map(UserDTO::new).collect(Collectors.toList());
    }


}
