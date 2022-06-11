package pl.esley.esleyspringlearnmvn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.esley.esleyspringlearnmvn.model.Player;
import pl.esley.esleyspringlearnmvn.repository.PlayerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAllPlayers();
    }
}
