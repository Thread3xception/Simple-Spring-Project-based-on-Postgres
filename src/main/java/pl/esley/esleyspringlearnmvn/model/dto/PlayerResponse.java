package pl.esley.esleyspringlearnmvn.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import pl.esley.esleyspringlearnmvn.model.Car;
import pl.esley.esleyspringlearnmvn.model.Player;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@Schema(description = "PlayerResponse")
public class PlayerResponse {

    @Schema(description = "Player identity", example = "1")
    private long id;

    @Schema(description = "Player nickname", example = "login123")
    private String nickname;

    @Schema(description = "Player years old", example = "18")
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
                .carPlates(player.getCars().stream()
                        .map(Car::getPlate)
                        .collect(Collectors.toList()))
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
}
