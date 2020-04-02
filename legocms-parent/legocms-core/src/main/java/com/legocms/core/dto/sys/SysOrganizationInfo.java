package com.legocms.core.dto.sys;

import com.legocms.core.dto.Dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysOrganizationInfo extends Dto {

    private static final long serialVersionUID = 8367196170464564704L;

    private String code;
    private String name;
    private boolean state;

}