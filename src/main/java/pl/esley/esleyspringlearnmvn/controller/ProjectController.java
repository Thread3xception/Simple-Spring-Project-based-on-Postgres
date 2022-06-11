package pl.esley.esleyspringlearnmvn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.esley.esleyspringlearnmvn.model.Car;
import pl.esley.esleyspringlearnmvn.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public String restApi() {
        return projectService.getWelcome();
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return projectService.getListOfCars();
    }
}
