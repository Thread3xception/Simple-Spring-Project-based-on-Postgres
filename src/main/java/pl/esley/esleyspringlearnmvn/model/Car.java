package pl.esley.esleyspringlearnmvn.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String plate;

    @NotNull
    private String brand;

    @PositiveOrZero
    @Column(nullable = false)
    private double power;

    private String playerNickname;
}
