package com.ccr.moo.tool.ui.form.func;

import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.thread.ThreadUtil;
import lombok.Getter;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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
    private JButton     copyCurrentTimestampButton;
    private JTextField  gmtTextField;
    private JButton     copyGeneratedLocalTimeButton;
    private JTextField  timestampTextField;
    private JButton     copyGeneratedTimestampButton;
    private JButton     toLocalTimeButton;
    private JButton     toTimestampButton;
    private JComboBox   comboBox1;
    private JTextField  timeFormatTextField;

    private static TimeConvertForm timeConvertForm;

    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public TimeConvertForm() {
        copyCurrentGmtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThreadUtil.execute(() -> {
                    try {
                        timeConvertForm.getCopyCurrentGmtButton().setEnabled(false);
                        ClipboardUtil.setStr(timeConvertForm.getCurrentGmtLabel().getText());
                        JOptionPane.showMessageDialog(timeConvertPanel, "已复制", "成功", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    } finally {
                        timeConvertForm.getCopyCurrentGmtButton().setEnabled(true);
                    }
                });
            }
        });
        copyCurrentTimestampButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    timeConvertForm.getCopyCurrentTimestampButton().setEnabled(false);
                    ClipboardUtil.setStr(timeConvertForm.getCurrentTimestampLabel().getText());
                    JOptionPane.showMessageDialog(timeConvertForm.getTimeConvertPanel(), "已复制！", "成功", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } finally {
                    timeConvertForm.getCopyCurrentTimestampButton().setEnabled(true);
                }
            }
        });
    }

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

        if ("".equals(timeConvertForm.getGmtTextField().getText())) {
            timeConvertForm.getGmtTextField().setText(DateFormatUtils.format(new Date(), TIME_FORMAT));
        }
        if ("".equals(timeConvertForm.getTimestampTextField().getText())) {
            timeConvertForm.getTimestampTextField().setText(String.valueOf(System.currentTimeMillis()));
        }
    }
}
