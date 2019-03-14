package com.example.vuedemo.license;

import com.eltima.components.ui.DatePicker;
import com.secmask.util.tool.LicenseUtil;
import com.secmask.util.tool.PropertiesConfig;
import com.secmask.util.tool.RSAUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class License {
    private JFrame frame;
    private JLabel validDateLB;
    private JLabel label;
    private JLabel label_2;
    private JLabel label_3;
    private JTextArea secretLicenseTA;
    private JTextArea keyTF;
    private JTextField idCodeHexHashStrField;
    private JLabel codeLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    License window = new License();
                    window.frame.setLocationRelativeTo((Component)null);
                    window.frame.setVisible(true);
                } catch (Exception var2) {
                    var2.printStackTrace();
                }

            }
        });
    }

    public License() {
        this.initialize();
    }

    private void initialize() {
        this.frame = new JFrame();
        this.frame.setTitle("许可证生成器");
        this.frame.setBounds(100, 100, 757, 626);
        this.frame.setDefaultCloseOperation(3);
        JPanel panel = new JPanel();
        this.frame.getContentPane().add(panel, "Center");
        panel.setLayout((LayoutManager)null);
        this.codeLabel = new JLabel("识别码：");
        this.codeLabel.setFont(new Font("新宋体", 1, 12));
        this.codeLabel.setBounds(36, 26, 96, 15);
        panel.add(this.codeLabel);
        this.idCodeHexHashStrField = new JTextField();
        this.idCodeHexHashStrField.setBounds(142, 23, 559, 21);
        panel.add(this.idCodeHexHashStrField);
        this.validDateLB = new JLabel("有效期：");
        this.validDateLB.setFont(new Font("宋体", 1, 12));
        this.validDateLB.setBounds(36, 69, 96, 15);
        panel.add(this.validDateLB);
        final DatePicker datepick = getDatePicker(this.validDateLB.getX() + this.validDateLB.getWidth() + 10, this.validDateLB.getY() - 6);
        panel.add(datepick);
        JButton generateLicenseBtn = new JButton("生成许可证");
        generateLicenseBtn.setFont(new Font("新宋体", 1, 12));
        generateLicenseBtn.setBounds(572, 298, 129, 23);
        panel.add(generateLicenseBtn);
        JLabel lblNewLabel_1 = new JLabel("许可证编号【明文】：");
        lblNewLabel_1.setFont(new Font("新宋体", 0, 12));
        lblNewLabel_1.setBounds(36, 338, 129, 15);
        panel.add(lblNewLabel_1);
        final JTextArea licenseTA = new JTextArea();
        licenseTA.setLineWrap(true);
        licenseTA.setFont(new Font("Monospaced", 1, 12));
        licenseTA.setEditable(false);
        licenseTA.setBounds(36, 363, 665, 57);
        panel.add(licenseTA);
        this.label = new JLabel("<html><u>复制编号</u></html>");
        this.label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                License.setClipboardString(licenseTA.getText());
                JOptionPane.showMessageDialog((Component)null, "已复制到系统剪贴板！", "消息", 1);
            }

            public void mouseEntered(MouseEvent e) {
               License.this.label.setCursor(new Cursor(12));
            }
        });
        this.label.setForeground(Color.BLUE);
        this.label.setFont(this.label.getFont().deriveFont(this.label.getFont().getStyle() | 2));
        this.label.setBounds(647, 332, 54, 22);
        panel.add(this.label);
        this.label_2 = new JLabel("许可证编号【密文】：");
        this.label_2.setFont(new Font("新宋体", 0, 12));
        this.label_2.setBounds(36, 437, 129, 15);
        panel.add(this.label_2);
        this.label_3 = new JLabel("<html><u>复制编号</u></html>");
        this.label_3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                com.secmask.license.License.setClipboardString(License.this.secretLicenseTA.getText());
                JOptionPane.showMessageDialog((Component)null, "已复制到系统剪贴板！", "消息", 1);
            }

            public void mouseEntered(MouseEvent e) {
                License.this.label_3.setCursor(new Cursor(12));
            }
        });
        this.label_3.setForeground(Color.BLUE);
        this.label_3.setFont(this.label_3.getFont().deriveFont(this.label_3.getFont().getStyle() | 2));
        this.label_3.setBounds(647, 431, 54, 22);
        panel.add(this.label_3);
        this.secretLicenseTA = new JTextArea();
        this.secretLicenseTA.setLineWrap(true);
        this.secretLicenseTA.setFont(new Font("Monospaced", 1, 12));
        this.secretLicenseTA.setEditable(false);
        this.secretLicenseTA.setBounds(36, 462, 665, 100);
        panel.add(this.secretLicenseTA);
        JLabel label_4 = new JLabel("私钥：");
        label_4.setFont(new Font("宋体", 1, 12));
        label_4.setBounds(36, 113, 96, 15);
        panel.add(label_4);
        this.keyTF = new JTextArea();
        this.keyTF.setLineWrap(true);
        this.keyTF.setFont(new Font("Monospaced", 1, 12));
        this.keyTF.setText(PropertiesConfig.getString("rsa.private_key.default"));
        JScrollPane jScrollPane = new JScrollPane(this.keyTF);
        jScrollPane.setBounds(142, 110, 559, 137);
        jScrollPane.setVerticalScrollBarPolicy(20);
        jScrollPane.setHorizontalScrollBarPolicy(31);
        panel.add(jScrollPane);
        final JLabel lblrsa = new JLabel("<html><u>生成RSA密钥对</u></html>");
        lblrsa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String keyFilePath = RSAUtil.saveKeys(RSAUtil.generateKeys());
                if (StringUtils.isBlank(keyFilePath)) {
                    JOptionPane.showMessageDialog((Component)null, "生成RSA公钥和私钥失败！", "错误", 0);
                } else {
                    JOptionPane.showMessageDialog((Component)null, "成功生成了RSA公钥和私钥，并写入了文件：" + keyFilePath, "消息", 1);
                }

            }

            public void mouseEntered(MouseEvent e) {
                lblrsa.setCursor(new Cursor(12));
            }
        });
        lblrsa.setForeground(Color.BLUE);
        lblrsa.setFont(lblrsa.getFont().deriveFont(lblrsa.getFont().getStyle() | 2));
        lblrsa.setBounds(142, 257, 123, 22);
        panel.add(lblrsa);
        final JLabel licenseMsgLabel = new JLabel("");
        licenseMsgLabel.setForeground(Color.GREEN);
        licenseMsgLabel.setBounds(185, 338, 334, 15);
        panel.add(licenseMsgLabel);
        final JLabel secretLicenseMsgLabel = new JLabel("");
        secretLicenseMsgLabel.setForeground(Color.GREEN);
        secretLicenseMsgLabel.setBounds(185, 437, 334, 15);
        panel.add(secretLicenseMsgLabel);
        generateLicenseBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                licenseMsgLabel.setText("");
                secretLicenseMsgLabel.setText("");
                licenseTA.setText("");
               License.this.secretLicenseTA.setText("");
                String idCodeHexHashStr =License.this.idCodeHexHashStrField.getText();
                if (StringUtils.isBlank(idCodeHexHashStr)) {
                    JOptionPane.showMessageDialog((Component)null, "识别码不能为空！", "警告", 2);
                } else {
                    try {
                        String license = LicenseUtil.generateLicense(idCodeHexHashStr, (Date)datepick.getValue());
                        licenseTA.setText(license);
                        if (StringUtils.isBlank(license)) {
                            licenseMsgLabel.setForeground(Color.GREEN);
                            licenseMsgLabel.setText("生成明文许可证失败！");
                        } else {
                            licenseMsgLabel.setText("成功生成了明文许可证！");
                        }

                        if (StringUtils.isNotBlank(License.this.keyTF.getText())) {
                            String secretLicense = RSAUtil.encrypt(License.this.keyTF.getText(), false, license);
                           License.this.secretLicenseTA.setText(secretLicense);
                            if (StringUtils.isBlank(secretLicense)) {
                                secretLicenseMsgLabel.setText("生成密文许可证失败！");
                            } else {
                                secretLicenseMsgLabel.setText("成功生成了密文许可证！");
                            }
                        }
                    } catch (Exception var5) {
                        var5.printStackTrace();
                        JOptionPane.showMessageDialog((Component)null, "生成许可证编号失败！", "错误", 0);
                    }
                }

            }
        });
    }

    private static DatePicker getDatePicker(int x, int y) {
        String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, 1);
        Date date = calendar.getTime();
        Font font = new Font("Times New Roman", 1, 14);
        Dimension dimension = new Dimension(177, 24);
        int[] hilightDays = new int[]{1, 3, 5, 7};
        int[] disabledDays = new int[]{4, 6, 5, 9};
        DatePicker datepick = new DatePicker(date, DefaultFormat, font, dimension);
        datepick.setLocation(x, y);
        datepick.setHightlightdays(hilightDays, Color.red);
        datepick.setDisableddays(disabledDays);
        datepick.setLocale(Locale.CANADA);
        datepick.setTimePanleVisible(true);
        return datepick;
    }

    public static void setClipboardString(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable trans = new StringSelection(text);
        clipboard.setContents(trans, (ClipboardOwner)null);
    }
}
