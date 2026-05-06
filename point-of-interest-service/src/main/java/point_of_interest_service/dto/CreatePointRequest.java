package point_of_interest_service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePointRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Latitude is required")
        @Min(value = -90)
        @Max(value = 90)
        Double latitude,

        @NotNull(message = "Longitude is required")
        @Min(value = -180)
        @Max(value = 180)
        Double longitude
) {
}