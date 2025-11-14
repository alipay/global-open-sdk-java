package com.alipay.global.api.model.ams;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentBank {
  private Logo logo;
  private String bankName;
  private String bankShortName;
  private String bankTerms;
  private String bankPromoUrl;
  private List<String> bins;
  private List<Plan> plans;
}
