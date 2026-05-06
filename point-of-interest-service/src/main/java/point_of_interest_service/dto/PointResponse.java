package point_of_interest_service.dto;

public record PointResponse(
        Long id,
        String name,
        Double latitude,
        Double longitude,
        Long requestCount
) {
}