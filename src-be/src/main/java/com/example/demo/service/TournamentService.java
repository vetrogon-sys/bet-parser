package com.example.demo.service;

import com.example.demo.entity.Tournament;

public interface TournamentService {

    Tournament saveIfNotExist(Tournament tournament);

}
