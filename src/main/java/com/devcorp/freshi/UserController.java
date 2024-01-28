
package com.devcorp.freshi;

import com.devcorp.freshi.models.User;
import com.devcorp.freshi.service.UserService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/vi/users"})
public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {
    }

    @GetMapping({"/allUser"})
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping({"/{email}"})
    public ResponseEntity<Optional<User>> getUserWithEmail(@PathVariable String email) {
        return new ResponseEntity(this.userService.getSingleUser(email), HttpStatus.OK);
    }

    @PostMapping({"/addUser"})
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity(this.userService.addUser(user), HttpStatus.OK);
    }
    @PostMapping({"/logIn"})
    public ResponseEntity<String> logIn(@RequestBody Map<String, String> request) {
        String email = (String)request.get("email");
        String password = (String)request.get("password");

        try {
            Optional<User> user = this.userService.getSingleUser(email);
            return user.isPresent() && ((User)user.get()).getPassword().equals(password) ? new ResponseEntity("success", HttpStatus.OK) : new ResponseEntity("Wrong Credentials", HttpStatus.UNAUTHORIZED);
        } catch (Exception var5) {
            return new ResponseEntity("Unable to login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
