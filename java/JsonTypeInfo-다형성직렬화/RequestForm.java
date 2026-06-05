package com.inje.coneportal.service.approve.form;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.NoClass;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    property = "formType", include = JsonTypeInfo.As.PROPERTY, defaultImpl = NoClass.class, visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(name = "project", value = ProjectRequestForm.class),
    @JsonSubTypes.Type(name = "catalog", value = CatalogRequestForm.class)})
abstract public class RequestForm {
    String formType;
}
