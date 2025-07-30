package com.alipay.global.api.model.aps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

  private String referenceStoreId;
  private String storeName;
  private String storeMCC;
  private String storeDisplayName;
  private String storeTerminalId;
  private String storeOperatorId;
  private String storePhoneNo;
  private Address storeAddress;
}
