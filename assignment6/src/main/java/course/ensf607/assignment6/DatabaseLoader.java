package course.ensf607.assignment6;

import course.ensf607.assignment6.entity.*;
import course.ensf607.assignment6.repository.*;
import course.ensf607.assignment6.service.FinancialInstService;
import course.ensf607.assignment6.service.ShowtimeService;
import course.ensf607.assignment6.service.TheatreService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
  private final UserRepository userRepository;
  private final TheatreRepository theatreRepository;
  private final MovieRepository movieRepository;
  private final ShowtimeRepository showtimeRepository;
  private final ShowtimeService showtimeService;
  private final TheatreService theatreService;
  private final FinancialInstRepository financialRepository;

  public DatabaseLoader(
      UserRepository userRepository,
      TheatreRepository theatreRepository,
      MovieRepository movieRepository,
      ShowtimeRepository showtimeRepository,
      ShowtimeService showtimeService,
      TheatreService theatreService,
      FinancialInstRepository financialRepository) {
    this.userRepository = userRepository;
    this.movieRepository = movieRepository;
    this.showtimeRepository = showtimeRepository;
    this.theatreRepository = theatreRepository;
    this.showtimeService = showtimeService;
    this.theatreService = theatreService;
    this.financialRepository = financialRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    // Create some users.
    this.userRepository.save(
        new User(
            "Aaron", "Manuel", "amanuel1", "email@email.com", "password", 12345678, 202, 12232029));
    this.userRepository.save(
        new User(
            "Sue", "Martin", "smartin", "email2@email.com", "password", 87654321, 453, 11052024));
    // Create some movies.
    Movie movie1 =
        new Movie(
            "The Cake Knight Rises", "A cake knight will rise.", LocalDate.of(2022, 11, 20), LocalDate.of(2022, 12, 01));
    Movie movie2 =
        new Movie(
            "Love, Probably", "A film about love, probably", LocalDate.of(2022, 12, 02), LocalDate.of(2022, 12,8));
    this.movieRepository.save(movie1);
    this.movieRepository.save(movie2);

    // Create a theatre.
    Theatre theatre1 = new Theatre("Canyon Meadows Theatre", "13226 Macleod Trail", 10, 10);

    // Add movie to theatre.
    this.theatreService.addMovieToTheatre(theatre1, "The Cake Knight Rises");
    this.theatreService.addMovieToTheatre(theatre1, "Love, Probably");
    //    this.theatreRepository.save(theatre1);

    // Setup the showtimes, then create showtimes with the theatre.
    LocalDateTime dateTime1 = LocalDateTime.of(2022, 12, 8, 14, 30);
    LocalDateTime dateTime2 = LocalDateTime.of(2022, 12, 8, 16, 30);
    Showtime showtime1 = new Showtime(dateTime1, theatre1);
    Showtime showtime2 = new Showtime(dateTime2, theatre1);
    this.showtimeRepository.save(showtime1);
    this.showtimeRepository.save(showtime2);

    // Tie the showtimes to a movie.
    this.showtimeService.addShowtimeToMovie(showtime1, movie1.getName());
    this.showtimeService.addShowtimeToMovie(showtime2, movie2.getName());
    this.showtimeService.addSeatsEmptyTicketsToShowtime(showtime1, theatre1);
    this.showtimeService.addSeatsEmptyTicketsToShowtime(showtime2, theatre1);

    // Establish financial institution database.
    this.financialRepository.save(
            new FinancialInst(
                    "Aaron", "Manuel",  12345678, 202, 12232029));
    this.financialRepository.save(
            new FinancialInst(
                    "Sue", "Martin", 87654321, 453, 11052024));

    // Save everything.
    this.movieRepository.save(movie1);
    this.movieRepository.save(movie2);
    //    this.theatreRepository.save(theatre1);
    //    this.showtimeRepository.save(showtime1);
    //    this.showtimeRepository.save(showtime2);
  }
}
