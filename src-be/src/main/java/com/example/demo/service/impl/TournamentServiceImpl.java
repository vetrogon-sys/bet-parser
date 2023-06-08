package com.example.demo.service.impl;

import com.example.demo.entity.Tournament;
import com.example.demo.repository.TournamentRepository;
import com.example.demo.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;

    @Override
    public Tournament saveIfNotExist(Tournament tournament) {
        return tournamentRepository.findById(tournament.getName())
              .orElse(tournamentRepository.save(tournament));
    }
}
