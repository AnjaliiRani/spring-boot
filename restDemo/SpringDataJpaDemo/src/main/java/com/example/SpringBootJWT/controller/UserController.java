package com.example.SpringBootJWT.controller;

import com.example.SpringBootJWT.service.UserService;
import dto.CreateUserDto;
import dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public  ResponseEntity<List<UserDto>> getusers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
    @GetMapping("/paginated")
    public  ResponseEntity<List<UserDto>> getusersPaginated(@RequestParam int page, @RequestParam int pageSize,
                                                            @RequestParam(defaultValue = "asc") String direction,
                                                            @RequestParam(defaultValue = "name") String sortBy ){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersPaginated(page,pageSize,direction,sortBy));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> patchUser( @PathVariable Long id, @RequestBody CreateUserDto patchUserDto){
         return ResponseEntity.status(HttpStatus.OK).body(userService.patchUser(id, patchUserDto));

    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody CreateUserDto updateUserDto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(updateUserDto, id));

    }
   // public ResponseEntity<>


}
