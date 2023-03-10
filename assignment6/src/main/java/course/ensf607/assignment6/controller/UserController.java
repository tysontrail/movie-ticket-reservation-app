package course.ensf607.assignment6.controller;

import course.ensf607.assignment6.entity.User;
import course.ensf607.assignment6.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** Rest controller and api endpoint for user stuff. */
@RestController
@CrossOrigin({"*"})
public class UserController {

  /** Aggregate the user service functions with the API */
  @Autowired private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * In case user decides to access the root of API.
   *
   * @return welcome message.
   */
  @GetMapping({"/"})
  public String index() {
    return "Welcome to the movies! Login or sign in as guest to buy a ticket.";
  }

  /**
   * AddUser communicator to frontend. Sent as JSON body in postman and then take what we need to
   * create the user.
   *
   * @param user is the user that wants to register.
   */
  @PostMapping({"/api/v1/addregistereduser"})
  public void addUser(@RequestBody User user) {
    this.userService.addUser(
        user.getFirstName(),
        user.getLastName(),
        user.getUserName(),
        user.getEmail(),
        user.getPassword(),
        user.getCreditCard(),
        user.getCvcNumber(),
        user.getExpiryDate());
  }

  // For the admin just in case.
  @PostMapping({"/api/v1/deleteregistereduser"})
  public void deleteUser(long id) {
    this.userService.deleteUserByID(id);
  }

  // Delete this later. There's a problem with tickets but we haven't gotten to that yet.
  @GetMapping({"api/v1/viewallusers"})
  public Iterable<User> viewAllUsers() {
    return this.userService.getAllUsers();
  }

  // Find user by first and last name, sent by form data
  @GetMapping({"api/v1/finduserbyfirstandlastname"})
  public Iterable<User> findUserByFirstNameAndLastName(
      @RequestParam String firstName, @RequestParam String lastName) {
    return this.userService.searchUserByFirstNameAndLastName(firstName, lastName);
  }

  // Find user by first and last name, another problem with tickets.
  @GetMapping({"api/v1/finduserbyusername"})
  public Optional<User> findUserByUserName(String userName) {
    return this.userService.searchUserByUserName(userName);
  }

  @PostMapping({"api/v1/addusertoticket"})
  public void addUserToTicket(@RequestParam long userId, @RequestParam long ticketId){
    this.userService.addTicketToUser(userId, ticketId);
  }
}
