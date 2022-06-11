package pl.esley.esleyspringlearnmvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.esley.esleyspringlearnmvn.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query("SELECT c FROM Car c")
    List<Car> allCars();
}
