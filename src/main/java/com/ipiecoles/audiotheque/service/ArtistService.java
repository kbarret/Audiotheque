package com.ipiecoles.audiotheque.service;

import com.ipiecoles.audiotheque.entity.Artist;
import com.ipiecoles.audiotheque.repository.AlbumRepository;
import com.ipiecoles.audiotheque.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public Page<Artist> findAllArtists(Integer page, Integer size, String sortProperty, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortProperty);
        Pageable pageable = PageRequest.of(page,size,sort);
        return artistRepository.findAll(pageable);
    }

    public Artist findById(Long id) {
        Optional<Artist> artist = this.artistRepository.findById(id);
        return artist.get();
    }

    public Long countAllArtists() {
        return artistRepository.count();
    }

    public Artist creerArtiste(Artist artist) {
        return artistRepository.save(artist);
    }

    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }

    public Artist updateArtiste(Long id, Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist findByname(
            String name
    ) {
        Artist artist = this.artistRepository.findByName(artist);
        return artist;
    }
}
