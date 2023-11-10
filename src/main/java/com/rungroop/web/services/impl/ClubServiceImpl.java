package com.rungroop.web.services.impl;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.repository.IClubRepository;
import com.rungroop.web.services.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements IClubService {
    private final IClubRepository _clubRepository;
    private Club club;

    @Autowired
    public ClubServiceImpl(IClubRepository clubRepository) {
        this._clubRepository = clubRepository;
    }
    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = _clubRepository.findAll();
        return clubs.stream().map((club)-> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        Club clubToSave = mapToClub(clubDto);
        return _clubRepository.save(clubToSave);
    }

    @Override
    public ClubDto findClubById(long clubId) {
        Club club = _clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        _clubRepository.save(club);
    }

    @Override
    public void delete(long cludId) {
        _clubRepository.deleteById(cludId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = _clubRepository.searchClubs(query);
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

    public Club mapToClub(ClubDto clubDto) {
        return Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
    }
    private ClubDto mapToClubDto(Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }
}
