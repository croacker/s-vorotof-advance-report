package com.vorotof.advancereport.service.ofd.excerpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {
    @JsonProperty("receipt")
    private OfdCheckExcerpt ofdCheck;
}
