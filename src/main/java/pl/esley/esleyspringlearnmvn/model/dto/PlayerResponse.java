package pl.esley.esleyspringlearnmvn.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import pl.esley.esleyspringlearnmvn.model.Car;
import pl.esley.esleyspringlearnmvn.model.Player;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Builder
@Schema(description = "PlayerResponse")
public class PlayerResponse {

    @Schema(description = "Player identity")
    private Long id;

    @Schema(description = "Player nickname")
    private String nickname;

    @Schema(description = "Player years old")
    private int yearsOld;

    @Schema(description = "Player car plates")
    private List<String> carPlates;
    public static List<PlayerResponse> listFrom(List<Player> players) {
        return players.stream()
                .map(player -> from(player))
                .collect(Collectors.toList());
    }

    public static PlayerResponse from(Player player) {
        return PlayerResponse.builder()
                .id(player.getId())
                .nickname(player.getNickname())
                .yearsOld(player.getYearsOld())
                .carPlates(getCarPlatesForPlayer(player))
                .build();
    }

    public static PlayerResponse fromWithoutCars(Player player) {
        return PlayerResponse.builder()
                .id(player.getId())
                .nickname(player.getNickname())
                .yearsOld(player.getYearsOld())
                .carPlates(null)
                .build();
    }

    public static List<String> getCarPlatesForPlayer(Player player) {
        return Optional.ofNullable(player.getCars())
                .orElse(Collections.emptyList())
                .stream()
                .map(Car::getPlate)
                .collect(Collectors.toList());
    }
}
