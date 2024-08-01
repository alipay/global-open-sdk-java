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
public class PaymentOptionDetail {

    private List<SupportCardBrand> supportCardBrands;

    private List<String>           funding;

    private List<SupportBank>      supportBanks;

}
