package com.alipay.global.api.model.ams;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AncillaryData {
  private List<Service> services;
  private String connectedTicketNumber;
}
