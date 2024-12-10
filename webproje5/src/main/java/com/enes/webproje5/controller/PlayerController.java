package com.enes.webproje5.controller;

import com.enes.webproje5.model.Player;
import com.enes.webproje5.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public String listPlayers(@RequestParam(value = "search", required = false) String search, Model model) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("players", playerService.searchPlayers(search));
        } else {
            model.addAttribute("players", playerService.getAllPlayers());
        }
        return "player/list";
    }

    @GetMapping("/create")
    public String createPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        return "player/create";
    }

    @PostMapping
    public String savePlayer(@ModelAttribute("player") Player player) {
        playerService.savePlayer(player);
        return "redirect:/players";
    }

    @GetMapping("/edit/{id}")
    public String editPlayerForm(@PathVariable Long id, Model model) {
        Player player = playerService.getPlayerById(id);
        if (player == null) {
            throw new RuntimeException("player not found with ID: " + id);
        }
        model.addAttribute("player", player);
        return "player/edit";
    }

    @PostMapping("/{id}")
    public String updatePlayer(@PathVariable Long id, @ModelAttribute("player") Player player) {
        player.setId(id);
        playerService.savePlayer(player);
        return "redirect:/players";
    }

    @GetMapping("/delete/{id}")
    public String deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return "redirect:/players";
    }
}