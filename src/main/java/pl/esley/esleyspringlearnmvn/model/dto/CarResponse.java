package pl.esley.esleyspringlearnmvn.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import pl.esley.esleyspringlearnmvn.model.Car;


@Getter
@Builder
@Schema(description = "CarResponse")
public class CarResponse {

    @Schema(description = "Car identity", example = "1")
    private Long id;

    @Schema(description = "Car plate", example = "W0 XXXXX")
    private String plate;

    @Schema(description = "Car brand", example = "brand")
    private String brand;

    @Schema(description = "Car power", example = "100")
    private double power;

    @Schema(description = "Owner nickname", example = "login123")
    private String playerNickname;

    public static CarResponse from(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .plate(car.getPlate())
                .brand(car.getBrand())
                .power(car.getPower())
                .playerNickname(car.getPlayerNickname())
                .build();
    }
}
