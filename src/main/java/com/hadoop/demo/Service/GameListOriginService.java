package com.hadoop.demo.Service;

import com.hadoop.demo.Model.GameList;
import com.hadoop.demo.Model.GameListOrigin;
import com.hadoop.demo.Repository.GameListOriginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameListOriginService {

    @Autowired
    private GameListOriginRepository gameListOriginRepository;

    public GameListOrigin save(GameListOrigin gameList) {
        return gameListOriginRepository.save(gameList);
    }

    public List<GameListOrigin> findAll() { return gameListOriginRepository.findAll();}

}