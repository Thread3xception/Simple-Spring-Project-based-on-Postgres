package pl.esley.esleyspringlearnmvn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.esley.esleyspringlearnmvn.exception.CarNotFoundException;
import pl.esley.esleyspringlearnmvn.model.Car;
import pl.esley.esleyspringlearnmvn.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getListOfCars();
    }

    @GetMapping("/{id}")
    public Car getSingleCar(@PathVariable long id) throws CarNotFoundException {
        return carService.getSingleCar(id);
    }

    @PostMapping()
    public void saveCar() {

    }
}
