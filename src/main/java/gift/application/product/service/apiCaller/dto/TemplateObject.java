package gift.application.product.service.apiCaller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TemplateObject(
    @JsonProperty("object_type")
    String objectType,
    String text,
    Link link
) {

}
