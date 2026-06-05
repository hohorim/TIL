package com.inje.coneportal.service.approve.form;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubnetRequestForm extends RequestForm{
    private int ipVersion;
    private String subnetName;
    private String cidr;
    private String gatewayIp;
    private String description;
    private boolean dhcpEnabled;
    private List<String> dnsNameservers;
    private List<String> allocationPools;

    public boolean isValid() {
        boolean rs = StringUtils.isBlank(cidr) || (ipVersion!=4 && ipVersion!=6);
        return !rs;
    }
    
}
