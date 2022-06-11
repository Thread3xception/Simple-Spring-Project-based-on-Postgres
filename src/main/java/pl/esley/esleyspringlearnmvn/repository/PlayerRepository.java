package pl.esley.esleyspringlearnmvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.esley.esleyspringlearnmvn.model.Player;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    @Query("SELECT p FROM Player p")
    public List<Player> findAllPlayers();
}
