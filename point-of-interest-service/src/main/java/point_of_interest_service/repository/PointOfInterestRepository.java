package point_of_interest_service.repository;

import point_of_interest_service.entity.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {

    List<PointOfInterest> findByRequestCountGreaterThan(Long requestCount);
}