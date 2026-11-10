import beans.Consulta;
import beans.ItemConsulta;
import beans.Procedimento;
import dao.ConsultaDAO;
import dao.ItemConsultaDAO;
import dao.ProcedimentoDAO;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aluno
 */
public class TabelaConsultas extends javax.swing.JFrame {

    private final ConsultaDAO dao = new ConsultaDAO();
    private final ItemConsultaDAO itemDAO = new ItemConsultaDAO();
    private final ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
    private final List<Procedimento> procedimentos = new ArrayList<>();

    private static final DateTimeFormatter FORMATO_DATA_HORA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public TabelaConsultas() {
        initComponents();
        setLocationRelativeTo(null);

        tblConsultas.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Paciente", "Médico", "Data/Hora", "Status"}));
        tblConsultas.setDefaultEditor(Object.class, null);
        tblConsultas.getColumnModel().getColumn(0).setMinWidth(0);
        tblConsultas.getColumnModel().getColumn(0).setMaxWidth(0);
        tblConsultas.getColumnModel().getColumn(0).setPreferredWidth(0);

        tblItens.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Procedimento", "Qtd", "Valor unit. (R$)", "Subtotal (R$)"}));
        tblItens.setDefaultEditor(Object.class, null);
        tblItens.getColumnModel().getColumn(0).setMinWidth(0);
        tblItens.getColumnModel().getColumn(0).setMaxWidth(0);
        tblItens.getColumnModel().getColumn(0).setPreferredWidth(0);

        // ao selecionar uma consulta, mostra os itens dela
        tblConsultas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) carregarItens();
        });

        // recarrega a lista quando a janela volta a ficar ativa
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowActivated(java.awt.event.WindowEvent e) {
                carregarTabela();
            }
        });

        txtQuantidade.setText("1");
        carregarProcedimentos();
        carregarTabela();
    }

    // devolve o ID da consulta selecionada, ou 0 se nada estiver selecionado
    private int idSelecionado() {
        int linha = tblConsultas.getSelectedRow();
        if (linha < 0) return 0;
        return Integer.parseInt(tblConsultas.getValueAt(linha, 0).toString());
    }

    // recarrega a lista mantendo a consulta selecionada
    private void carregarTabela() {
        int idAntes = idSelecionado();
        DefaultTableModel modelo = (DefaultTableModel) tblConsultas.getModel();
        modelo.setRowCount(0);
        try {
            for (Consulta c : dao.listar()) {
                modelo.addRow(new Object[]{
                    c.getId(), c.getNomePaciente(), c.getNomeMedico(),
                    c.getDataHora().format(FORMATO_DATA_HORA), c.getStatus()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar consultas: " + e.getMessage());
        }
        if (idAntes != 0) {
            for (int i = 0; i < modelo.getRowCount(); i++) {
                if (Integer.parseInt(modelo.getValueAt(i, 0).toString()) == idAntes) {
                    tblConsultas.setRowSelectionInterval(i, i);
                    break;
                }
            }
        }
    }

    private void carregarProcedimentos() {
        cmbProcedimento.removeAllItems();
        procedimentos.clear();
        try {
            for (Procedimento p : procedimentoDAO.listar()) {
                procedimentos.add(p);
                cmbProcedimento.addItem(p.getNome() + " - R$ " + String.format("%.2f", p.getValor()));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar procedimentos: " + e.getMessage());
        }
    }

    private void carregarItens() {
        DefaultTableModel modelo = (DefaultTableModel) tblItens.getModel();
        modelo.setRowCount(0);
        int idConsulta = idSelecionado();
        if (idConsulta == 0) {
            lblTotal.setText("Total: R$ 0,00");
            return;
        }
        double total = 0;
        try {
            for (ItemConsulta i : itemDAO.listarPorConsulta(idConsulta)) {
                modelo.addRow(new Object[]{
                    i.getId(), i.getNomeProcedimento(), i.getQuantidade(),
                    String.format("%.2f", i.getValorUnitario()),
                    String.format("%.2f", i.getSubtotal())
                });
                total += i.getSubtotal();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar itens: " + e.getMessage());
        }
        lblTotal.setText("Total: R$ " + String.format("%.2f", total)
                + (modelo.getRowCount() == 0 ? "  (consulta sem itens)" : ""));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVoltar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        painel = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItens = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        painel1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblConsultas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cmbProcedimento = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        btnRemoverItem = new javax.swing.JButton();
        btnAdicionarItem = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnVoltar.setText("Voltar");
        btnVoltar.setName("btnVoltar"); // NOI18N
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.setName("btnEditar"); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.setName("btnExcluir"); // NOI18N
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Consultas Agendadas");

        tblItens.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblItens);

        painel.setViewportView(jScrollPane1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Itens da consulta selecionada ");

        tblConsultas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblConsultas);

        painel1.setViewportView(jScrollPane2);

        jLabel2.setText("Procedimento:");

        cmbProcedimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProcedimentoActionPerformed(evt);
            }
        });

        jLabel4.setText("Qtd:");

        btnRemoverItem.setText("Remover item");
        btnRemoverItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverItemActionPerformed(evt);
            }
        });

        btnAdicionarItem.setText("Adicionar Item");
        btnAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarItemActionPerformed(evt);
            }
        });

        lblTotal.setText("Total: R$ 0,00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(226, Short.MAX_VALUE)
                        .addComponent(btnExcluir)
                        .addGap(50, 50, 50)
                        .addComponent(btnEditar)
                        .addGap(43, 43, 43)
                        .addComponent(btnVoltar)
                        .addGap(84, 84, 84)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(cmbProcedimento, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(45, 45, 45)
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(txtQuantidade))
                        .addComponent(painel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdicionarItem)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemoverItem)
                        .addGap(42, 42, 42)
                        .addComponent(lblTotal)
                        .addGap(28, 28, 28)))
                .addGap(44, 44, 44))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(painel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(629, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(27, 27, 27)
                .addComponent(painel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbProcedimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoverItem)
                    .addComponent(btnAdicionarItem)
                    .addComponent(lblTotal))
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(85, Short.MAX_VALUE)
                    .addComponent(painel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(145, 145, 145)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbProcedimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProcedimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProcedimentoActionPerformed

    private void btnRemoverItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverItemActionPerformed
        int linha = tblItens.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um item na tabela de itens para remover.");
            return;
        }
        if (tblItens.getRowCount() == 1) {
            JOptionPane.showMessageDialog(this,
                "A consulta precisa ter ao menos um item.\nAdicione outro antes de remover este.");
            return;
        }
        int idItem = Integer.parseInt(tblItens.getValueAt(linha, 0).toString());
        try {
            itemDAO.excluir(idItem);
            carregarItens();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao remover item: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRemoverItemActionPerformed

    private void btnAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarItemActionPerformed
        int idConsulta = idSelecionado();
        if (idConsulta == 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta na tabela de cima.");
            return;
        }
        int idx = cmbProcedimento.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(this, "Cadastre ao menos um procedimento.");
            return;
        }
        int qtd = 0;
        try {
            qtd = Integer.parseInt(txtQuantidade.getText().trim());
        } catch (NumberFormatException e) {
            // continua com 0 e cai na validação abaixo
        }
        if (qtd <= 0) {
            JOptionPane.showMessageDialog(this, "Informe uma quantidade maior que zero.");
            return;
        }
        Procedimento p = procedimentos.get(idx);
        ItemConsulta item = new ItemConsulta();
        item.setIdConsulta(idConsulta);
        item.setIdProcedimento(p.getId());
        item.setQuantidade(qtd);
        item.setValorUnitario(p.getValor());
        try {
            itemDAO.inserir(item);
            txtQuantidade.setText("1");
            carregarItens();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar item: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAdicionarItemActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int id = idSelecionado();
        if (id == 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta na tabela para editar.");
            return;
        }
        new TelaConsultas(id).setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int id = idSelecionado();
        if (id == 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta na tabela para excluir.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Deseja excluir esta consulta e os itens dela?",
                "Confirmar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
        try {
            dao.excluir(id);
            tblConsultas.clearSelection();
            carregarTabela();
            carregarItens();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TabelaConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TabelaConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TabelaConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TabelaConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TabelaConsultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarItem;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnRemoverItem;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cmbProcedimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JScrollPane painel;
    private javax.swing.JScrollPane painel1;
    private javax.swing.JTable tblConsultas;
    private javax.swing.JTable tblItens;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
