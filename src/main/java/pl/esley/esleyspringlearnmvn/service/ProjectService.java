package pl.esley.esleyspringlearnmvn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.esley.esleyspringlearnmvn.model.Car;
import pl.esley.esleyspringlearnmvn.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final CarRepository carRepository;

    public String getWelcome() {
        return "Witaj w aplikacji!";
    }

    public List<Car> getListOfCars() {
        return carRepository.allCars();
    }
}
