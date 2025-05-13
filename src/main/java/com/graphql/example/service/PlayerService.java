package com.graphql.example.service;

import com.graphql.example.model.Player;
import com.graphql.example.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(7);

    public List<Player> findAll() {
        return players;
    }

    public Optional<Player> findOne(Integer id) {
        return players.stream()
                .filter(player -> player.id().equals(id))
                .findFirst();
    }

    public Player create(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player delete(Integer id) {
        Optional<Player> player = findOne(id);
        if (player.isPresent()) {
            players.remove(player.get());
            return player.get();
        }
        return null;
    }

    public Player update(Integer pid, String name, Team team) {
        Optional<Player> player = findOne(pid);
        if (player.isPresent()) {
            Player updatedPlayer = new Player(id.incrementAndGet(), name, team);
            players.remove(player.get());
            players.add(updatedPlayer);
            return updatedPlayer;
        } else {
            throw new IllegalArgumentException("Player not found");
        }
    }

    @PostConstruct
    private void init() {
        players.add(new Player(1, "Lionel Messi", Team.BARCELONA));
        players.add(new Player(2, "Cristiano Ronaldo", Team.REAL_MADRID));
        players.add(new Player(3, "Neymar Jr", Team.PSG));
        players.add(new Player(4, "Kylian Mbappe", Team.PSG));
        players.add(new Player(5, "Kevin De Bruyne", Team.MANCHESTER_CITY));
        players.add(new Player(6, "Robert Lewandowski", Team.BARCELONA));
        players.add(new Player(7, "Mohamed Salah", Team.LIVERPOOL));
    }
}
