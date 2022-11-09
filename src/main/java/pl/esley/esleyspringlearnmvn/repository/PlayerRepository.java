package pl.esley.esleyspringlearnmvn.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.esley.esleyspringlearnmvn.model.Player;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p") // N + 1 ... konwertowanie Encji na DTO (DTO)
    List<Player> findAllPlayers(Pageable page);

    @Query("SELECT p FROM Player p WHERE p.id = ?1")
    Optional<Player> findSinglePlayerById(Long id);
}
