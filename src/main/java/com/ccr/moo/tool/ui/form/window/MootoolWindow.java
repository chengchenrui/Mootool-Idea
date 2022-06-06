package com.ccr.moo.tool.ui.form.window;

import com.intellij.openapi.wm.ToolWindow;
import lombok.Getter;

import javax.swing.*;

/**
 * @Title: MootoolWindow
 * @Author: chengchenrui
 * @Date: created 2022/5/20 10:39
 */
@Getter
public class MootoolWindow {

    private JPanel      mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel      timeConvertPanel;

    public MootoolWindow(ToolWindow toolWindow) {

    }


    public JPanel getContent() {
        return mainPanel;
    }
}
