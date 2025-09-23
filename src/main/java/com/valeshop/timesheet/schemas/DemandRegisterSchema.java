package com.valeshop.timesheet.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DemandRegisterSchema {
    @JsonProperty("problems")
    protected List<String> problems;
    @JsonProperty("observations")
    protected List<String> observations;
    @JsonProperty("comments")
    protected List<String> comments;

}
