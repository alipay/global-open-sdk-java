package com.alipay.global.api.model.ams;

import com.alipay.global.api.model.aps.Logo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantRegistrationInfo {
  private String referenceMerchantId;
  private String merchantDisplayName;
  private String merchantMCC;
  private Logo logo;
  private List<WebSite> websites;
  private Address merchantAddress;
  private RegistrationDetail registrationDetail;
}
