package com.inje.coneportal.service.approve.form;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NetworkRequestForm implements RequestForm{
    private String networkName;
    private String description;
    private boolean routerExternal;
    private boolean sharedEnabled;
    private boolean portSecurity;
    private int mtuByte;

    private SubnetRequestForm subnet;

    public boolean isValid() {
        boolean rs = StringUtils.isBlank(networkName) || (subnet!=null && (StringUtils.isBlank(subnet.getCidr()) || (subnet.getIpVersion()!=4 && subnet.getIpVersion()!=6)) );
        return !rs;
    }
}
