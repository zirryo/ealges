package com.server.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

    @Column(name = "street_name_address")
    private String streetNameAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "zip_code")
    private String zipCode;

    @Builder
    public Address(final String streetNameAddress, final String detailAddress, final String zipCode) {
        this.streetNameAddress = streetNameAddress;
        this.detailAddress = detailAddress;
        this.zipCode = zipCode;
    }

    public String getFullAddress() {
        return String.format("%s %s %s", streetNameAddress, detailAddress, zipCode);
    }
}
