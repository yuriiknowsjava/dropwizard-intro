package edu.yuriikoval1997.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Saying {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String content;
}
