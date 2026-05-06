package point_of_interest_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
    @Operation(summary = "Create a new point of interest")
    public PointResponse createPoint(
            @Valid @RequestBody CreatePointRequest request
    ) {
        return service.createPoint(request);
    }

    @PostMapping("/nearest")
    @Operation(summary = "Find nearest point of interest")
    public PointResponse findNearest(
            @Valid @RequestBody NearbyPointRequest request
    ) {
        return service.findNearestPoint(request);
    }

    @GetMapping("/popular")
    @Operation(summary = "Get popular points of interest")
    public List<PointResponse> getPopularPoints(
            @RequestParam Long minCounter
    ) {
        return service.getPopularPoints(minCounter);
    }

    @GetMapping
    @Operation(summary = "Get all points of interest")
    public List<PointResponse> getAllPoints() {
        return service.getAllPoints();
    }
}