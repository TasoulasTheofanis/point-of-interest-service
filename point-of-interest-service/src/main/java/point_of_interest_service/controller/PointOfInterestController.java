package point_of_interest_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import point_of_interest_service.dto.CreatePointRequest;
import point_of_interest_service.dto.NearbyPointRequest;
import point_of_interest_service.dto.PointResponse;
import point_of_interest_service.service.PointOfInterestService;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pois")
@RequiredArgsConstructor
public class PointOfInterestController {

    private final PointOfInterestService service;

    @PostMapping
    public PointResponse createPoint(
            @Valid @RequestBody CreatePointRequest request
    ) {
        return service.createPoint(request);
    }

    @PostMapping("/nearest")
    public PointResponse findNearest(
            @Valid @RequestBody NearbyPointRequest request
    ) {
        return service.findNearestPoint(request);
    }

    @GetMapping("/popular")
    public List<PointResponse> getPopularPoints(
            @RequestParam Long minCounter
    ) {
        return service.getPopularPoints(minCounter);
    }

    @GetMapping
    public List<PointResponse> getAllPoints() {
        return service.getAllPoints();
    }
}