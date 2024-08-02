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
public class Individual {

    private UserName          name;
    private UserName          englishName;
    private String            dateOfBirth;
    private Address           placeOfBirth;
    private List<Certificate> certificates;
    private String            nationality;
    private List<Contact>     contacts;

}
