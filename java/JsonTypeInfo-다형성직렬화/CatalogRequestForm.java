package com.inje.coneportal.service.approve.form;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogRequestForm implements RequestForm {

    private String clusterId;
    private String projectUuid;
    private String instanceName;
    private String imageId;
    private String flavorId;
    private List<String> networks;
    private String description;
    private String keyName;
    private List<String> securityGroups;
    private String availabilityZone;

    private boolean passwordAuthEnabled;
    private boolean accountCreated;
    private String userName;
    private String password;
    private String adminPassword;
    
    private NetworkRequestForm networkInfo;

    private String catalogId;
    private long categoryIdx;
    private long billingPolicyIdx;

}
