package pl.esley.esleyspringlearnmvn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.esley.esleyspringlearnmvn.exception.CarNotFoundException;
import pl.esley.esleyspringlearnmvn.model.Car;
import pl.esley.esleyspringlearnmvn.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public String getWelcome() {
        return "Witaj w aplikacji!";
    }

    public List<Car> getListOfCars() {
        return carRepository.allCars();
    }

    public Car getSingleCar(long id) throws CarNotFoundException {
        Car toReturn = carRepository.getSingleCar(id);
        if(toReturn == null) {
            throw new CarNotFoundException(id);
        }
        return toReturn;
    }
}
