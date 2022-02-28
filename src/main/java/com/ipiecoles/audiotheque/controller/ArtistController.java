package com.ipiecoles.audiotheque.controller;

import com.ipiecoles.audiotheque.entity.Artist;
import com.ipiecoles.audiotheque.repository.AlbumRepository;
import com.ipiecoles.audiotheque.repository.ArtistRepository;
import com.ipiecoles.audiotheque.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(value="/#/detail/{id}",
                    produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET)
    public Artist findById(@PathVariable(value = "id") Long id){
        return artistService.findById(id);
    }

    @RequestMapping(value = "/#/count", method = RequestMethod.GET)
    public Long count(){
        return artistService.countAllArtists();
    }

    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, params = "matricule")
    public Artist findByName(@RequestParam("name") String name){
        return artistService.findByname(name);
    }

    @RequestMapping(value = "/#/artists", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Page<Artist> All(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection){
        return artistService.findAllArtists(page, size, sortProperty, String.valueOf(sortDirection));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/#/artists/detail/new")
    public Artist create(@RequestBody Artist artist)  {
        return this.artistService.creerArtiste(artist);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/#/artists/detail/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEmploye(@PathVariable("id") Long id){
        this.artistService.deleteArtist(id);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/#/detail/{id}")
    public Artist update(@PathVariable("id") Long id, @RequestBody Artist artist){
        return this.artistService.updateArtiste(id,artist);
    }
}


