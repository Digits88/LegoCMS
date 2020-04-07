package com.legocms.web.directive.cms;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.legocms.core.annotation.RequiresPermissions;
import com.legocms.core.dto.cms.CmsModelInfo;
import com.legocms.core.vo.sys.SysPermissionCode;
import com.legocms.data.handler.RenderHandler;
import com.legocms.service.cms.ICmsModelService;
import com.legocms.web.directive.ControllerTemplateDirective;

@Component
@RequiresPermissions(SysPermissionCode.MODEL)
public class CmsModelListDirective extends ControllerTemplateDirective {

    @Autowired
    private ICmsModelService modelService;

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        List<CmsModelInfo> models = modelService.findByParent(handler.getString("parentCode"));
        handler.put("models", models).render();
    }

}
