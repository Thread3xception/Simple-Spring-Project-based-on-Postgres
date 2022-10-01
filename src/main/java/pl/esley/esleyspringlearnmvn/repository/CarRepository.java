package pl.esley.esleyspringlearnmvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.esley.esleyspringlearnmvn.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c")
    List<Car> allCars();

    @Query("SELECT c FROM Car c WHERE c.id = ?1")
    Car getSingleCar(long id);
    @Query("SELECT c FROM Car c WHERE c.brand = ?1")
    List<Car> getAllCarsByBrand(String brand);

    @Query("SELECT c FROM Car c WHERE c.playerNickname = ?1")
    List<Car> getAllCarsByPlayerNickname(String playerNickname);
}
