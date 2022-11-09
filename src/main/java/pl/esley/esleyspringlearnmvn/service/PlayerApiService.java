package pl.esley.esleyspringlearnmvn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.esley.esleyspringlearnmvn.exception.EntityNotFoundException;
import pl.esley.esleyspringlearnmvn.model.Car;
import pl.esley.esleyspringlearnmvn.model.Player;
import pl.esley.esleyspringlearnmvn.model.dto.PlayerResponse;
import pl.esley.esleyspringlearnmvn.repository.CarRepository;
import pl.esley.esleyspringlearnmvn.repository.PlayerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerApiService {

    private static final int Page_SIZE = 10;
    private final PlayerRepository playerRepository;
    private final CarRepository carRepository;

    public List<PlayerResponse> getAllPlayers(Integer page, Sort.Direction sortDirection) {
        return playerRepository.findAllPlayers(PageRequest.of(page,Page_SIZE, Sort.by(sortDirection, "id")))
                .stream()
                .map(PlayerResponse::fromWithoutCars)
                .collect(Collectors.toList());
    }

    public List<PlayerResponse> getAllPlayersWithCars(Integer page, Sort.Direction sortDirection) {
        // Pobieranie wszystkich graczy
        List<Player> allPlayers = playerRepository.findAllPlayers(PageRequest.of(page,Page_SIZE, Sort.by(sortDirection, "id")));

        // Dla playera szukamy aut
        allPlayers.forEach(player -> player.setCars(extractCars(player.getNickname())));

        // Zamieniamy wszystko na response
        List<PlayerResponse> allResponses = allPlayers.stream()
                                                    .map(PlayerResponse::from)
                                                    .collect(Collectors.toList());

        return allResponses;
    }

    private List<Car> extractCars(String nickname) {
        return carRepository.getAllCarsByPlayerNickname(nickname);
    }

    public PlayerResponse getSinglePlayerByIdWithCars(long id) {
        Player player = Optional.ofNullable(playerRepository.findSinglePlayerById(id)
                .orElseThrow(() -> new EntityNotFoundException(Player.class, id)))
                .get();

        player.setCars(extractCars(player.getNickname()));

        return Optional.of(player).map(PlayerResponse::from).get();
    }

    public PlayerResponse addPlayer(Player player) {
        PlayerResponse response = PlayerResponse.from(player);

        playerRepository.save(player);
        return response;
    }

    @Transactional
    public Player editPlayer(Player player) {
        Player playerEdit = playerRepository.findById(player.getId()).orElseThrow(() -> new EntityNotFoundException(Player.class, player.getId()));
        playerEdit.setNickname(player.getNickname());
        playerEdit.setYearsOld(player.getYearsOld());
        // w tym miejscu moglbym nie uzywac save poniewaz hibernate sprawdza za kazdym razem czy w encji nie zmienily sie pola
        // jesli cokolwiek sie zmieni to hibernate automatycznie updatuje nasze pola w bazie danych.
        return playerEdit;
    }

    public String deletePlayer(long id) {
        Player playerToRemove = playerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Player.class, id));

        playerToRemove.getCars().stream()
                .forEach(e -> e.setPlayerNickname(null));

        playerRepository.deleteById(id);

        return "Pomyślnie usunięto użytkownika o nazwie: " + playerToRemove.getNickname();
    }

    public String assignCar(Long playerId, Long carId) {
        final Player player = playerRepository.findById(playerId).orElseThrow(() -> new EntityNotFoundException(Player.class, playerId));
        final Car car = carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException(Car.class, carId));

        car.setPlayerNickname(player.getNickname());
        carRepository.save(car);

        player.getCars().add(car);
        System.out.println("[DBG] Player: " + player.getCars());
        playerRepository.save(player);

        return "Pomyślnie przypisano auto o numerze rejestracyjnym: " + car.getPlate() + " do użytkownika o id: " + player.getNickname();

    }
}
