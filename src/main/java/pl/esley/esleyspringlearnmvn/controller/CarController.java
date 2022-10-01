package pl.esley.esleyspringlearnmvn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.esley.esleyspringlearnmvn.model.Car;
import pl.esley.esleyspringlearnmvn.model.dto.CarResponse;
import pl.esley.esleyspringlearnmvn.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    @GetMapping
    public List<CarResponse> getAllCars() {
        return carService.getListOfCars();
    }

    @GetMapping("/id/{carId}")
    public CarResponse getSingleCar(@PathVariable long carId) {
        return carService.getSingleCar(carId);
    }

    @GetMapping("/brand/{brand}")
    public List<CarResponse> getAllCarsByBrand(@PathVariable String brand) {
        return carService.getAllCarsByBrand(brand);
    }

    @GetMapping("/player_info/{playerId}")
    public List<CarResponse> getAllCarsByPlayerId(@PathVariable String playerId) {
        return carService.getAllCarsByPlayerNickname(playerId);
    }


    @PostMapping
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }
}
