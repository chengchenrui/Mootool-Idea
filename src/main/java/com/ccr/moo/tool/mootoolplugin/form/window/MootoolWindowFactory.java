package com.ccr.moo.tool.mootoolplugin.form.window;

import cn.hutool.core.thread.ThreadUtil;
import com.ccr.moo.tool.mootoolplugin.form.func.TimeConvertForm;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.uiDesigner.core.GridConstraints;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @Title: MootoolWindowFactory
 * @Author: chengchenrui
 * @Date: created 2022/5/20 10:20
 */
public class MootoolWindowFactory implements ToolWindowFactory {
    private static GridConstraints gridConstraints = new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false);

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        MootoolWindow mootoolWindow = new MootoolWindow(toolWindow);
        mootoolWindow.getTabbedPane().setIconAt(0, new FlatSVGIcon("icon/time.svg"));
        mootoolWindow.getTimeConvertPanel().add(TimeConvertForm.getInstance().getTimeConvertPanel(), gridConstraints);
        ThreadUtil.execute(TimeConvertForm::init);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(mootoolWindow.getContent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
