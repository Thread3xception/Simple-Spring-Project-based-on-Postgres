package pl.esley.esleyspringlearnmvn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.esley.esleyspringlearnmvn.model.Car;
import pl.esley.esleyspringlearnmvn.model.dto.CarResponse;
import pl.esley.esleyspringlearnmvn.service.CarApiService;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarApiService carApiService;
    @GetMapping
    public List<CarResponse> getAllCars() {
        return carApiService.getListOfCars();
    }

    @GetMapping("/id/{carId}")
    public CarResponse getSingleCar(@PathVariable long carId) {
        return carApiService.getSingleCar(carId);
    }

    @GetMapping("/brand/{brand}")
    public List<CarResponse> getAllCarsByBrand(@PathVariable String brand) {
        return carApiService.getAllCarsByBrand(brand);
    }

    @GetMapping("/player_info/{playerId}")
    public List<CarResponse> getAllCarsByPlayerId(@PathVariable String playerId) {
        return carApiService.getAllCarsByPlayerNickname(playerId);
    }

    @PostMapping
    public void addCar(@RequestBody Car car) {
        carApiService.addCar(car);
    }
}
