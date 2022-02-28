package com.ipiecoles.audiotheque.controller;

import com.ipiecoles.audiotheque.entity.Album;
import com.ipiecoles.audiotheque.entity.Artist;
import com.ipiecoles.audiotheque.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/#/artists/detail/newalbum")
    public Album create(@RequestBody Album album)  {
        return this.albumService.creerAlbum(album);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/#/artists/detail/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        this.albumService.deleteAlbum(id);
    }
}
