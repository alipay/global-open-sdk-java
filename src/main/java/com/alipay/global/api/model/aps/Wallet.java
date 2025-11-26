package com.alipay.global.api.model.aps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

  private String walletName;
  private String walletBrandName;
  private Logo walletLogo;
  private String walletRegion;
  private WalletFeature walletFeature;
}
