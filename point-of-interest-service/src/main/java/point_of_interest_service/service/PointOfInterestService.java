package point_of_interest_service.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import point_of_interest_service.dto.CreatePointRequest;
import point_of_interest_service.dto.NearbyPointRequest;
import point_of_interest_service.dto.PointResponse;
import point_of_interest_service.entity.PointOfInterest;
import point_of_interest_service.repository.PointOfInterestRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointOfInterestService {

    private final PointOfInterestRepository repository;

    private final GeometryFactory geometryFactory = new GeometryFactory();

    private List<PointOfInterest> cachedPoints = new ArrayList<>();

    @PostConstruct
    public void loadCache() {
        refreshCache();
    }

    private void refreshCache() {
    cachedPoints = repository.findAll();
}

    public PointResponse createPoint(CreatePointRequest request) {

        Point point = geometryFactory.createPoint(
                new Coordinate(
                        request.longitude(),
                        request.latitude()
                )
        );

        point.setSRID(4326);

        PointOfInterest poi = new PointOfInterest();

        poi.setName(request.name());
        poi.setLocation(point);

        PointOfInterest savedPoi = repository.save(poi);

        refreshCache();

        return mapToResponse(savedPoi);
    }

    public PointResponse findNearestPoint(NearbyPointRequest request) {

        Point targetPoint = geometryFactory.createPoint(
                new Coordinate(
                        request.longitude(),
                        request.latitude()
                )
        );

        PointOfInterest nearest = cachedPoints.stream()
                .min(Comparator.comparingDouble(
                        poi -> poi.getLocation().distance(targetPoint)
                ))
                .orElseThrow(() -> new RuntimeException("No POIs found"));

        nearest.setRequestCount(nearest.getRequestCount() + 1);

        repository.save(nearest);

        refreshCache();

        return mapToResponse(nearest);
    }

    public List<PointResponse> getPopularPoints(Long minCounter) {

        return repository.findByRequestCountGreaterThan(minCounter)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<PointResponse> getAllPoints() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private PointResponse mapToResponse(PointOfInterest poi) {

        return new PointResponse(
                poi.getId(),
                poi.getName(),
                poi.getLocation().getY(),
                poi.getLocation().getX(),
                poi.getRequestCount()
        );
    }
}