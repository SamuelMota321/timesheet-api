package com.valeshop.timesheet.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DemandSchema {

    @JsonProperty("title")
    @NotBlank(message = "A demanda deve ter um título")
    protected String title;

    @JsonProperty("gitlink")
    @NotBlank(message = "É obrigatório fornecer um link para o git dessa demanda")
    protected String gitLink;

    @JsonProperty("priority")
    @NotNull(message = "Defina qual a ordem da prioridade da demanda. Ex: 1, 2, 3...")
    protected Integer priority;

    @JsonProperty("status")
    @NotBlank(message = "A demanda deve ser registrada com um status. Ex: Iniciada")
    protected String status;

    @JsonProperty("date")
    @NotNull(message = "Envie junto a data em que a demanda foi pedida")
    protected Date date;

    @JsonProperty("description")
    @NotBlank(message = "Envie junto uma descrição para a demanda")
    protected String description;
}
