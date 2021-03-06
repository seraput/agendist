/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.mReportPenjualan;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Dell
 */
public class ReportPenjualan extends javax.swing.JPanel {

    mReportPenjualan lpm = new mReportPenjualan();
    public DefaultTableModel dtm;

    /**
     * Creates new form ReportPenjualan
     */
    public ReportPenjualan() {
        initComponents();
        lpm.fun_ObjectTableDefault(this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_report = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txt_label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        t_netto = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        empat = new javax.swing.JLabel();
        enam = new javax.swing.JLabel();
        tiga = new javax.swing.JLabel();
        tujuh = new javax.swing.JLabel();
        dua = new javax.swing.JLabel();
        lapan = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cb_parameter = new javax.swing.JComboBox<>();
        cb_sales = new javax.swing.JComboBox<>();
        submit = new javax.swing.JButton();
        start_date = new com.toedter.calendar.JDateChooser();
        end_date = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(102, 204, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Report - Penjualan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tb_report.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tb_report);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Data Penjualan");

        txt_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_label.setText("jLabel7");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        t_netto.setText("0");

        jLabel7.setText("80% =");

        jLabel8.setText("20% = ");

        jLabel9.setText("70% =");

        jLabel10.setText("30% =");

        jLabel11.setText("60% =");

        jLabel12.setText("40% =");

        empat.setText("0");

        enam.setText("0");

        tiga.setText("0");

        tujuh.setText("0");

        dua.setText("0");

        lapan.setText("0");

        jLabel13.setText("Total =");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(3, 3, 3)
                        .addComponent(txt_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(t_netto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lapan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dua, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tujuh, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tiga, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enam, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(empat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_label)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_netto)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(empat)
                    .addComponent(enam)
                    .addComponent(tiga)
                    .addComponent(tujuh)
                    .addComponent(dua)
                    .addComponent(lapan)
                    .addComponent(jLabel13))
                .addGap(29, 29, 29))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pilihan Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel2.setText("Parameter Report");

        cb_parameter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Penjualan PerSales" }));
        cb_parameter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_parameterItemStateChanged(evt);
            }
        });

        cb_sales.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cb_sales.setEnabled(false);

        submit.setText("Proses");
        submit.setEnabled(false);
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        start_date.setDateFormatString("dd-MM-yyyy");
        start_date.setEnabled(false);

        end_date.setDateFormatString("dd-MM-yyyy");
        end_date.setEnabled(false);

        jLabel3.setText("Salesman");

        jLabel4.setText("Start Date");

        jLabel5.setText("End Date");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_parameter, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_sales, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(start_date, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(end_date, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(submit))
                    .addComponent(jLabel5))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(end_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_sales)
                    .addComponent(cb_parameter)
                    .addComponent(submit, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(start_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_parameterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_parameterItemStateChanged

        if (cb_parameter.getSelectedItem().equals("Penjualan PerSales")) {
            txt_label.setText("Per-Sales");
            end_date.setCalendar(null);
            start_date.setCalendar(null);
            try {
                lpm.fun_GetSales(this);
            } catch (SQLException ex) {
                Logger.getLogger(ReportPenjualan.class.getName()).log(Level.SEVERE, null, ex);
            }
            cb_sales.setEnabled(true);
            end_date.setEnabled(true);
            start_date.setEnabled(true);
            submit.setEnabled(true);

            ((DefaultTableModel) tb_report.getModel()).setNumRows(0);
        } else if (cb_parameter.getSelectedItem().equals("Penjualan Detail PerSales")) {
            txt_label.setText("Detail Per-Sales");
            end_date.setCalendar(null);
            start_date.setCalendar(null);
            try {
                lpm.fun_GetSales(this);
            } catch (SQLException ex) {
                Logger.getLogger(ReportPenjualan.class.getName()).log(Level.SEVERE, null, ex);
            }
            cb_sales.setEnabled(true);
            end_date.setEnabled(true);
            start_date.setEnabled(true);
            submit.setEnabled(true);
            ((DefaultTableModel) tb_report.getModel()).setNumRows(0);
        } else {
            txt_label.setText("Default");
            lpm.fun_ObjectTableDefault(this);
            cb_sales.setEnabled(false);
            end_date.setCalendar(null);
            start_date.setCalendar(null);
            end_date.setEnabled(false);
            start_date.setEnabled(false);
            submit.setEnabled(false);
            ((DefaultTableModel) tb_report.getModel()).setNumRows(0);
        }
    }//GEN-LAST:event_cb_parameterItemStateChanged

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        if (cb_parameter.getSelectedItem().equals("Penjualan Detail")) {
            String startDate = ((JTextField) start_date.getDateEditor().getUiComponent()).getText();
            String endDate = ((JTextField) end_date.getDateEditor().getUiComponent()).getText();
            if (cb_sales.getSelectedItem().equals("Pilih") || startDate.equals("") || endDate.equals("")) {
                JOptionPane.showMessageDialog(null, "Periksa Parameter!", "Gagal", HEIGHT, lpm.invalid);
            } else {
                if (cb_sales.getSelectedItem().equals("Semua")) {
                    try {
                        lpm.fun_TarikDetailSemua(this);
                    } catch (SQLException ex) {
                        Logger.getLogger(ReportPenjualan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        lpm.fun_TarikDetail(this);
                    } catch (SQLException ex) {
                        Logger.getLogger(ReportPenjualan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if (cb_parameter.getSelectedItem().equals("Penjualan Detail PerSales")) {
            String startDate = ((JTextField) start_date.getDateEditor().getUiComponent()).getText();
            String endDate = ((JTextField) end_date.getDateEditor().getUiComponent()).getText();
            if (!startDate.equals("") && !endDate.equals("") && !cb_sales.getSelectedItem().equals("Pilih")) {
                try {
                    lpm.fun_TarikDetail(this);
                } catch (SQLException ex) {
                    Logger.getLogger(ReportPenjualan.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Parameter Start, End Date atau Sales Kosong!", "Gagal", HEIGHT, lpm.invalid);
            }
        } else if (cb_parameter.getSelectedItem().equals("Penjualan PerSales")) {
            String startDate = ((JTextField) start_date.getDateEditor().getUiComponent()).getText();
            String endDate = ((JTextField) end_date.getDateEditor().getUiComponent()).getText();
            if (!startDate.equals("") && !endDate.equals("") && !cb_sales.getSelectedItem().equals("Pilih")) {
                try {
                    lpm.fun_TarikBySales(this);
                } catch (SQLException ex) {
                    Logger.getLogger(ReportPenjualan.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Parameter Start, End Date atau Sales Kosong!", "Gagal", HEIGHT, lpm.invalid);
            }
        }
    }//GEN-LAST:event_submitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String startDate = ((JTextField) start_date.getDateEditor().getUiComponent()).getText();
        String endDate = ((JTextField) end_date.getDateEditor().getUiComponent()).getText();
        if (!txt_label.getText().equals("Pilih") && !cb_sales.getSelectedItem().equals("Pilih") && !startDate.isEmpty() && !endDate.isEmpty()) {
            if (txt_label.getText().equals("Per-Sales")) {
                try {
                    lpm.fun_cetakSales(this);
                } catch (SQLException ex) {
                    Logger.getLogger(ReportPenjualan.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (txt_label.getText().equals("Detail Per-Sales")) {
                try {
                    lpm.fun_cetakSales(this);
                } catch (SQLException ex) {
                    Logger.getLogger(ReportPenjualan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Parameter Kosong!", "Gagal", HEIGHT, lpm.invalid);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void paginationOnPageChange(com.stripbandunk.jwidget.event.PaginationEvent evt) {//GEN-FIRST:event_paginationOnPageChange

    }//GEN-LAST:event_paginationOnPageChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cb_parameter;
    public javax.swing.JComboBox<String> cb_sales;
    public javax.swing.JLabel dua;
    public javax.swing.JLabel empat;
    public javax.swing.JLabel enam;
    public com.toedter.calendar.JDateChooser end_date;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lapan;
    public com.toedter.calendar.JDateChooser start_date;
    public javax.swing.JButton submit;
    public javax.swing.JLabel t_netto;
    public javax.swing.JTable tb_report;
    public javax.swing.JLabel tiga;
    public javax.swing.JLabel tujuh;
    private javax.swing.JLabel txt_label;
    // End of variables declaration//GEN-END:variables

}
