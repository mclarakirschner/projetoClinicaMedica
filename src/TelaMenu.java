
/**
 *
 * @author Maria Clara
 */
public class TelaMenu extends javax.swing.JFrame {

    public TelaMenu() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        btnPacientes = new javax.swing.JButton();
        btnConsultas = new javax.swing.JButton();
        btnMedicos = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnListaConsulta = new javax.swing.JButton();
        btnProcedimentos = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusTraversalPolicyProvider(true);
        setFocusable(false);
        setFocusableWindowState(false);

        btnPacientes.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnPacientes.setText("Cadastro de Pacientes");
        btnPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacientesActionPerformed(evt);
            }
        });

        btnConsultas.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnConsultas.setText("Cadastro de Consultas");
        btnConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultasActionPerformed(evt);
            }
        });

        btnMedicos.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnMedicos.setText("Cadastro de Médicos");
        btnMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicosActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        lblTitulo.setText("Clinica Médica");

        btnListaConsulta.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnListaConsulta.setText("Lista de Consultas");
        btnListaConsulta.setName("btnListaConsultas"); // NOI18N
        btnListaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaConsultaActionPerformed(evt);
            }
        });

        btnProcedimentos.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnProcedimentos.setText("Cadastro de Procedimentos");
        btnProcedimentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcedimentosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(383, 383, 383)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPacientes)
                                .addGap(87, 87, 87)
                                .addComponent(btnConsultas)
                                .addGap(87, 87, 87)
                                .addComponent(btnMedicos))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnProcedimentos)
                                .addGap(100, 100, 100)
                                .addComponent(btnListaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)))))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPacientes)
                            .addComponent(btnConsultas)
                            .addComponent(btnMedicos))
                        .addGap(159, 159, 159))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProcedimentos)
                            .addComponent(btnListaConsulta))
                        .addGap(60, 60, 60)))
                .addComponent(btnSair)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacientesActionPerformed
        new TelaPacientes().setVisible(true);
    }//GEN-LAST:event_btnPacientesActionPerformed

    private void btnConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultasActionPerformed
        new TelaConsultas().setVisible(true);
    }//GEN-LAST:event_btnConsultasActionPerformed

    private void btnMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicosActionPerformed
        new TelaMedicos().setVisible(true);
    }//GEN-LAST:event_btnMedicosActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnListaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaConsultaActionPerformed
        new TabelaConsultas().setVisible(true);
    }//GEN-LAST:event_btnListaConsultaActionPerformed

    private void btnProcedimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcedimentosActionPerformed
        new TelaProcedimentos().setVisible(true);
    }//GEN-LAST:event_btnProcedimentosActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultas;
    private javax.swing.JButton btnListaConsulta;
    private javax.swing.JButton btnMedicos;
    private javax.swing.JButton btnPacientes;
    private javax.swing.JButton btnProcedimentos;
    private javax.swing.JButton btnSair;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
