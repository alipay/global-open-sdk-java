package com.alipay.global.api.model.ams;


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
    private TransitType transitType;
    private String agentCode;
    private String agentName;
    private String ticketNumber;
    private String  ticketIssuerCode;
    private String restrictedTicketIndicator;
    private List<Leg> legs;
    private List<Passenger> passengers;
    private AncillaryData ancillaryData;
}
