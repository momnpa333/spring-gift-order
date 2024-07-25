package gift.application.product.service.apiCaller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Link(
    @JsonProperty("object_type")
    String webUrl,
    @JsonProperty("mobile_web_url")
    String mobileWebUrl
) {

}
