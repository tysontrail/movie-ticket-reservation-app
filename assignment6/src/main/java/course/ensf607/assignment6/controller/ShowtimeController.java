package course.ensf607.assignment6.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import course.ensf607.assignment6.entity.Showtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin({"*"})
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    public ShowtimeController(ShowtimeService showtimeService){
        this.showtimeService = showtimeService;
    }

    @PostMapping({"/api/v1/addshowtime"})
    public void addShowtimeToTheatre(@RequestParam
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                         LocalDateTime showtime, @RequestParam String theatreName,
                                            @RequestParam String movieName){
        Optional<Showtime> newShowtime = this.showtimeService.addShowtimeToTheatre(showtime, theatreName);
        this.showtimeService.addShowtimeToMovie(newShowtime.get(), movieName);
    }

    @GetMapping({"/api/v1/viewshowtimes"})
    public Iterable<Showtime> getAllMovieShowtimes(String movieName){
        return this.showtimeService.getAllMovieShowtimes(movieName);
    }

}
