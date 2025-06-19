package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AncillaryData {
    private List<Service> services;
    private String connectedTicketNumber;
}
