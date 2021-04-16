package app.REST;

import model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.repos.ArtistRepo;

import java.util.List;

@RestController
@RequestMapping("/app/artisti")
public class ArtistController {
    @Autowired
    private ArtistRepo ar = new ArtistRepo();

    @RequestMapping(method = RequestMethod.GET)
    public List<Artist> getAll() {
        return ar.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> cauta(@PathVariable  String id) {
        Artist a= ar.cauta(id);
        if(a==null)
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Artist>(a,HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Artist adauga(@RequestBody Artist artist)
    {
        ar.adauga(artist);
        return artist;
    }




}
