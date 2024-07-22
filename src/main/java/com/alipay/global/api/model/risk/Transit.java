package com.alipay.global.api.model.risk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transit {
    private String transitType;
    private List<Leg> legs;
    private List<Passenger> passengers;
}
