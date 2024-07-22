package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.Address;
import com.alipay.global.api.model.ams.UserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lodging {
    private String hotelName;
    private Address hotelAddress;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer numberOfNights;
    private Integer numberOfRooms;
    private List<UserName> guestNames;
}
