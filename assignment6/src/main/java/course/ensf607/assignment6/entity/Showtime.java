package course.ensf607.assignment6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**Showtime class that models a showtime with its seats and all its movies..
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "showtime")
public class Showtime implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  private LocalDateTime startTime;

  @ManyToOne
  @JoinColumn(name = "movie_id", nullable = true)
  private Movie movie;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "theatre_id", nullable = false)
  private Theatre theatre;

  @JsonIgnore
  @OneToMany(mappedBy = "showtime")
  private Set<Seat> seats;

  public Showtime(Long id, LocalDateTime startTime, Theatre theatre) {
    this.id = id;
    this.startTime = startTime;
    //        this.movie = movie;
    this.theatre = theatre;
    this.seats = new HashSet<Seat>();
    //    for (int i = 1; i <= theatre.getSeatRows(); i++) {
    //      for (int j = 1; j <= theatre.getSeatCols(); j++) {
    //        seats.add(new Seat(this, i, j));
    //      }
    //    }
  }

  public Showtime(LocalDateTime startTime, Theatre theatre) {
    this.id = id;
    this.startTime = startTime;
    this.seats = new HashSet<>();
    //        this.movie = movie;
    this.theatre = theatre;
    this.seats = new HashSet<>();
    //    for (int i = 1; i <= theatre.getSeatRows(); i++) {
    //      for (int j = 1; j <= theatre.getSeatCols(); j++) {
    //        seats.add(new Seat(this, i, j));
    //      }
    //    }
  }

  public void addMovieToShowtime(Optional<Movie> movie) {
    if (movie.isPresent()) {
      this.setMovie(movie.get());
    }
  }

  public void addSeatToShowtime(Seat seat) {
    seats.add(seat);
  }
}
