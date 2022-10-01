package pl.esley.esleyspringlearnmvn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.esley.esleyspringlearnmvn.exception.EntitiesNotFoundException;
import pl.esley.esleyspringlearnmvn.exception.EntityNotFoundException;
import pl.esley.esleyspringlearnmvn.model.Car;
import pl.esley.esleyspringlearnmvn.model.dto.CarResponse;
import pl.esley.esleyspringlearnmvn.repository.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<CarResponse> getListOfCars() {
        return carRepository.allCars()
                .stream()
                .map(CarResponse::from)
                .collect(Collectors.toList());
    }

    public List<CarResponse> getAllCarsByBrand(String brand) {
        return Optional.ofNullable(carRepository.getAllCarsByBrand(brand))
                .orElseThrow(() -> new EntityNotFoundException(Car.class, brand))
                .stream()
                .map(CarResponse::from)
                .collect(Collectors.toList());
    }

    public List<CarResponse> getAllCarsByPlayerNickname(String playerNickname) {
        return Optional.ofNullable(carRepository.getAllCarsByPlayerNickname(playerNickname))
                .orElseThrow(() -> new EntitiesNotFoundException(playerNickname))
                .stream()
                .map(CarResponse::from)
                .collect(Collectors.toList());
    }

    public CarResponse getSingleCar(long identity) {
        return carRepository.findById(identity)
                .stream()
                .findFirst()
                .map(CarResponse::from)
                .orElseThrow(() -> new EntityNotFoundException(Car.class, identity));
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }
}
