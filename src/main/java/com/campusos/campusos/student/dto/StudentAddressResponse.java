package com.campusos.campusos.student.dto;

import com.campusos.campusos.common.enums.AddressType;
import lombok.Data;

@Data
public class StudentAddressResponse {

    private Long id;

    private AddressType type;

    private String addressLine;

    private String city;

    private String district;

    private String state;

    private String country;

    private String pincode;
}
