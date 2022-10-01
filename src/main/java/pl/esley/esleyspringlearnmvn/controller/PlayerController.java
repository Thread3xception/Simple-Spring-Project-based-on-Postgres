package pl.esley.esleyspringlearnmvn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.esley.esleyspringlearnmvn.model.Player;
import pl.esley.esleyspringlearnmvn.model.dto.PlayerResponse;
import pl.esley.esleyspringlearnmvn.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public List<PlayerResponse> getAllPlayers(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0; // zabezpieczenie przed uzyciem minusowych stron w paginacji (swagger)
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return playerService.getAllPlayers(pageNumber, sortDirection);
    }

    @GetMapping("/cars")
    public List<PlayerResponse> getAllPlayersWithCars(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0; // zabezpieczenie przed uzyciem minusowych stron w paginacji (swagger)
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return playerService.getAllPlayersWithCars(pageNumber, sortDirection);
    }

    @GetMapping("/cars/{playerId}")
    public PlayerResponse getSinglePlayerByIdWithCars(@PathVariable("playerId") Long id) {
        return playerService.getSinglePlayerByIdWithCars(id);
    }

    @PatchMapping("{playerId}/car/{carId}")
    public String assignCar(@PathVariable("playerId") Long playerId, @PathVariable("carId") Long carId) {
        return playerService.assignCar(playerId, carId);
    }

    @PostMapping
    public PlayerResponse addPlayer(@RequestBody Player player) {
        return playerService.addPlayer(player);
    }
    @PutMapping
    public Player editPlayer(@RequestBody Player player) {
        return playerService.editPlayer(player);
    }

    @DeleteMapping("/{playerId}")
    public String deletePlayerById(@PathVariable("playerId") Long id) {
        return playerService.deletePlayer(id);
    }
}
