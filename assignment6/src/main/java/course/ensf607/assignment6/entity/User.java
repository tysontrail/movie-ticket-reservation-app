package course.ensf607.assignment6.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {

  private static User onlyInstance = null;
  private static boolean loggedIn = false;

  public static boolean isLoggedIn() {
    return loggedIn;
  }

  public static void setLoggedIn(boolean loggedIn) {
    User.loggedIn = loggedIn;
  }

  public static User getInstance() {
    return onlyInstance;
  }

  public static void setInstance(User user) {
    User.onlyInstance = user;
    setLoggedIn(true);
  }

  public static void setInstanceNull() {
    User.onlyInstance = null;
    setLoggedIn(false);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @NotEmpty(message = "Username cannot be empty.")
  private String userName;

  @NotEmpty(message = "First name cannot be empty.")
  private String firstName;

  @NotEmpty(message = "Last name cannot be empty.")
  private String lastName;

  @NotEmpty(message = "Email cannot be empty.")
  private String email;

  @NotEmpty(message = "Password cannot be empty.")
  private String password;

  // @NotNull(message = "Credit Card cannot be empty.")
  // @Range(min = 16, max = 16, message = "Credit Card should be 16 digits.")
  private long creditCard;

  // @NotNull(message = "CVC cannot be empty.")
  // @Range(min = 3, max = 3, message = "CVC should be 3 digits.")
  private int cvcNumber;

  // @NotNull(message = "Expiry Date cannot be empty.")
  // @Range(min = 4, max = 4, message = "Expiry Date should be 4 digits (MMYY)")
  private int expiryDate;

  private LocalDate annualRenewalDate;

  @OneToMany(mappedBy = "user")
  private Set<Ticket> tickets = new HashSet<>();

  @OneToMany(mappedBy = "user")
  private Set<Payment> payments = new HashSet<>();

  public User(
      Long id,
      String firstName,
      String lastName,
      String userName,
      String email,
      String password,
      long creditCard,
      int cvcNumber,
      int expiryDate) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.creditCard = creditCard;
    this.cvcNumber = cvcNumber;
    this.expiryDate = expiryDate;
  }

  public User(
      String firstName,
      String lastName,
      String userName,
      String email,
      String password,
      long creditCard,
      int cvcNumber,
      int expiryDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.creditCard = creditCard;
    this.cvcNumber = cvcNumber;
    this.expiryDate = expiryDate;
  }

  public void addTicket(Ticket ticket) {
    ticket.setUser(this);
    tickets.add(ticket);
  }
}
