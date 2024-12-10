package com.enes.webproje5.service;

import com.enes.webproje5.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    Player getPlayerById(Long id);
    Player savePlayer(Player player);
    void deletePlayer(Long id);
    List<Player> searchPlayers(String keyword);
}