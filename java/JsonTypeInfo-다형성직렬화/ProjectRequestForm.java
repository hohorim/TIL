package com.inje.coneportal.service.approve.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequestForm implements RequestForm {

    private String tenantId;
    private String projectId;
    private String nameKr;
    private String nameEn;
    private String projectInfo;
    private String startYmd;
    private String endYmd;
    private String requestComment;
    private String statusCode;
    private String clusterId;
}
