package pl.esley.esleyspringlearnmvn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.esley.esleyspringlearnmvn.model.Player;
import pl.esley.esleyspringlearnmvn.model.dto.PlayerResponse;
import pl.esley.esleyspringlearnmvn.service.PlayerApiService;

import java.util.List;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerApiService playerApiService;

    @GetMapping
    public List<PlayerResponse> getAllPlayers(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0; // zabezpieczenie przed uzyciem minusowych stron w paginacji (swagger)
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return playerApiService.getAllPlayers(pageNumber, sortDirection);
    }

    @GetMapping("/cars")
    public List<PlayerResponse> getAllPlayersWithCars(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0; // zabezpieczenie przed uzyciem minusowych stron w paginacji (swagger)
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return playerApiService.getAllPlayersWithCars(pageNumber, sortDirection);
    }

    @GetMapping("/cars/{playerId}")
    public PlayerResponse getSinglePlayerByIdWithCars(@PathVariable("playerId") Long id) {
        return playerApiService.getSinglePlayerByIdWithCars(id);
    }

    @PatchMapping("{playerId}/car/{carId}")
    public String assignCar(@PathVariable("playerId") Long playerId, @PathVariable("carId") Long carId) {
        return playerApiService.assignCar(playerId, carId);
    }

    @PostMapping
    public PlayerResponse addPlayer(@RequestBody Player player) {
        return playerApiService.addPlayer(player);
    }
    @PutMapping
    public Player editPlayer(@RequestBody Player player) {
        return playerApiService.editPlayer(player);
    }

    @DeleteMapping("/{playerId}")
    public String deletePlayerById(@PathVariable("playerId") Long id) {
        return playerApiService.deletePlayer(id);
    }
}
