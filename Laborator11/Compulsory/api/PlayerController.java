package com.popastefan.api;

import com.popastefan.model.Player;
import com.popastefan.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public void addPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping(path = "{id}")
    public Player getPlayerById(@PathVariable("id") int id) {
        return playerService.getPlayerById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePlayerById(@PathVariable("id") int id) {
        playerService.deletePlayer(id);
    }

    @PutMapping(path = "{id}")
    public void updatePlayer(@PathVariable("id") int id, @RequestBody Player playerToUpdate) {
        playerService.updatePlayer(id, playerToUpdate);
    }
}
