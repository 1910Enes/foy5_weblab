package com.enes.webproje5.serviceImpl;

import com.enes.webproje5.dao.PlayerRepository;
import com.enes.webproje5.model.Player;
import com.enes.webproje5.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository PlayerRepository;

    public PlayerServiceImpl(PlayerRepository PlayerRepository) {
        this.PlayerRepository = PlayerRepository;
    }

    @Override
    public List<Player> getAllPlayers() {
        return PlayerRepository.findAll();
    }

    @Override
    public Player getPlayerById(Long id) {
        return PlayerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with ID: " + id));
    }


    @Override
    public Player savePlayer(Player player) {
        return PlayerRepository.save(player);
    }

    @Override
    public void deletePlayer(Long id) {
        PlayerRepository.deleteById(id);
    }

    @Override
    public List<Player> searchPlayers(String keyword) {
        return PlayerRepository.findByNameContainingIgnoreCase(keyword);
    }
}