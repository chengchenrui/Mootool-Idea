package com.ccr.moo.tool.mootoolplugin.form.func;

import cn.hutool.core.thread.ThreadUtil;
import lombok.Getter;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.swing.*;
import java.util.Date;

/**
 * @Title: TimeConvertForm
 * @Author: chengchenrui
 * @Date: created 2022/5/20 13:55
 */
@Getter
public class TimeConvertForm {
    private JPanel      timeConvertPanel;
    private JPanel      leftPanel;
    private JSplitPane  splitPane;
    private JScrollPane leftScrollPane;
    private JTextArea   timeHisTextArea;
    private JLabel      currentGmtLabel;
    private JButton     copyCurrentGmtButton;
    private JLabel      currentTimestampLabel;
    private JButton     currentTimestampButton;
    private JTextField  timestampTextField;
    private JButton     copyGeneratedTimestampButton;
    private JTextField  gmtTextField;
    private JButton     copyGeneratedLocalTimeButton;
    private JButton     toLocalTimeButton;
    private JButton     toTimestampButton;
    private JComboBox   comboBox1;
    private JTextField  timeFormatTextField;

    private static TimeConvertForm timeConvertForm;

    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static TimeConvertForm getInstance() {
        if (timeConvertForm == null) {
            timeConvertForm = new TimeConvertForm();
        }
        return timeConvertForm;
    }

    public static void init() {
        timeConvertForm = getInstance();

        ThreadUtil.execute(() -> {
            while (true) {
                timeConvertForm.getCurrentTimestampLabel().setText(String.valueOf(System.currentTimeMillis() / 1000));
                timeConvertForm.getCurrentGmtLabel().setText(DateFormatUtils.format(new Date(), TIME_FORMAT));
                ThreadUtil.safeSleep(1000);
            }
        });

        if ("".equals(timeConvertForm.getTimestampTextField().getText())) {
            timeConvertForm.getTimestampTextField().setText(String.valueOf(System.currentTimeMillis()));
        }
        if ("".equals(timeConvertForm.getGmtTextField().getText())) {
            timeConvertForm.getGmtTextField().setText(DateFormatUtils.format(new Date(), TIME_FORMAT));
        }
    }
}
