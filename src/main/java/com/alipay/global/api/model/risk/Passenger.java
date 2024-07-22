package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.UserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    private UserName passengerName;
    private String passengerEmail;
    private String passengerPhoneNo;
}
