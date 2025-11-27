package com.alipay.global.api.model.aps;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletPaymentOptionDetail {

  private List<Wallet> supportWallets;
}
