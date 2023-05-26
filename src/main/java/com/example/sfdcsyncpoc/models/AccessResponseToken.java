package com.example.sfdcsyncpoc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccessResponseToken(String access_token) {
}
