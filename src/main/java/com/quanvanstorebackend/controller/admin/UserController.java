package com.quanvanstorebackend.controller.admin;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.dto.UserDTO;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
public class UserController extends BaseController<UserService> {

    @GetMapping("/list-all")
    public ServerResponse getAllUser(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<UserDTO> userList = service.getListUser(pageable);
            return ServerResponse.success("Lấy danh sách người dùng thành công!", userList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Unknown exception get all user!");
        }
    }

    @DeleteMapping("/{userId}")
    public ServerResponse deleteUser(@PathVariable Long userId) {
        try {
            service.toggleDeleted(userId);
            return ServerResponse.success("Cập nhật trạng thái thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Unknown exception get all user!");
        }
    }
}
