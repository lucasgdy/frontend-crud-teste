package View;

import Controllers.ConsumidorAPI;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author lucas
 */
public class ViewClient extends javax.swing.JFrame {

    String ip = "localhost";

    public ViewClient() {
        initComponents();
        fillJtable();
        pesquisa1();
    }

    private void limpar() {
        txtName.setText("");
        System.setProperty("idClient", String.valueOf(0));
    }

    private void pesquisa1() {
        DefaultTableModel model = (DefaultTableModel) tableClients.getModel();
        model.setNumRows(0);
        if ("".equals(txtPesquisa.getText().trim())) {
            JSONArray my_obj = null;
            String url = "http://" + ip + ":3030/clients";
            try {
                ConsumidorAPI consumidor = ConsumidorAPI.getInstance();
                my_obj = new JSONArray(consumidor.doGet(url));
            } catch (JSONException jSONException) {
            }

            switch (System.getProperty("code")) {
                case "200":
                    for (int i = 0; i < my_obj.length(); i++) {
                        model.addRow(new Object[]{my_obj.getJSONObject(i).get("id"), my_obj.getJSONObject(i).getString("name")});
                    }
                    System.setProperty("code", String.valueOf(0));
                    break;
                case "400":
                    JOptionPane.showMessageDialog(null, "Erro ao consultar os clientes cadastrados!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                    System.setProperty("code", String.valueOf(0));
                    this.dispose();
                    new ViewHomePage().setVisible(true);
                    break;
                case "404":
                    JOptionPane.showMessageDialog(null, "Nenhum registro foi encontrado.", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                    System.setProperty("code", String.valueOf(0));
                    this.dispose();
                    new ViewHomePage().setVisible(true);
                    break;
                default:
                    break;
            }
        } else {
            String name = txtPesquisa.getText().trim();
            String url = "http://" + ip + ":3030/clients/like";

            String body = "{\"name\": \"" + name + "\"}";
            JSONArray my_obj = null;
            try {
                ConsumidorAPI consumidor = ConsumidorAPI.getInstance();
                my_obj = new JSONArray(consumidor.doPost(url, body));
            } catch (JSONException jSONException) {
            }

            switch (System.getProperty("code")) {
                case "200":
                    for (int i = 0; i < my_obj.length(); i++) {
                        model.addRow(new Object[]{my_obj.getJSONObject(i).get("id"), my_obj.getJSONObject(i).getString("name")});
                    }
                    System.setProperty("code", String.valueOf(0));
                    break;
                case "400":
                    JOptionPane.showMessageDialog(null, "Erro ao consultar os clientes cadastrados!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                    System.setProperty("code", String.valueOf(0));
                    this.dispose();
                    new ViewHomePage().setVisible(true);
                    break;
                case "404":
                    JOptionPane.showMessageDialog(null, "Nenhum registro foi encontrado.", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                    System.setProperty("code", String.valueOf(0));
                    this.dispose();
                    new ViewHomePage().setVisible(true);
                    break;
                default:
                    break;
            }
        }
    }

    private void fillJtable() {
        DefaultTableModel model1 = (DefaultTableModel) tableClients.getModel();
        JTableHeader header = tableClients.getTableHeader();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centralizado = (DefaultTableCellRenderer) header.getDefaultRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tableClients.setAutoResizeMode(AUTO_RESIZE_OFF);
        tableClients.setDefaultRenderer(String.class, centerRenderer);
        tableClients.setRowSorter(new TableRowSorter(model1));
        model1.setNumRows(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClients = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        cbbPesquisarPor = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        pagesFilter = new javax.swing.JPanel();
        jpnPage1 = new javax.swing.JPanel();
        txtPesquisa = new javax.swing.JTextField();
        jpnPage2 = new javax.swing.JPanel();
        txtPesquisa1 = new javax.swing.JTextField();
        jpnPage3 = new javax.swing.JPanel();
        txtPesquisa2 = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CRUD - Clientes");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        tableClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableClients.setRowHeight(25);
        tableClients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableClients.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tableClientsAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tableClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientsMouseClicked(evt);
            }
        });
        tableClients.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableClientsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableClients);
        if (tableClients.getColumnModel().getColumnCount() > 0) {
            tableClients.getColumnModel().getColumn(0).setMinWidth(80);
            tableClients.getColumnModel().getColumn(0).setPreferredWidth(80);
            tableClients.getColumnModel().getColumn(0).setMaxWidth(100);
            tableClients.getColumnModel().getColumn(1).setMinWidth(440);
            tableClients.getColumnModel().getColumn(1).setPreferredWidth(440);
        }

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/001-atualizar.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.setEnabled(false);
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/rejeitado.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/001-carraca.png"))); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/001-boto-voltar.png"))); // NOI18N
        jButton6.setText("Voltar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome:*");

        cbbPesquisarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome" }));
        cbbPesquisarPor.setEnabled(false);
        cbbPesquisarPor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbPesquisarPorItemStateChanged(evt);
            }
        });

        jLabel2.setText("Pesquisar por:*");

        pagesFilter.setLayout(new java.awt.CardLayout());

        jpnPage1.setBackground(new java.awt.Color(204, 204, 204));

        txtPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPesquisa.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jpnPage1Layout = new javax.swing.GroupLayout(jpnPage1);
        jpnPage1.setLayout(jpnPage1Layout);
        jpnPage1Layout.setHorizontalGroup(
            jpnPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnPage1Layout.setVerticalGroup(
            jpnPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pagesFilter.add(jpnPage1, "page1");

        jpnPage2.setBackground(new java.awt.Color(204, 204, 204));

        txtPesquisa1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPesquisa1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesquisa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisa1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesquisa1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jpnPage2Layout = new javax.swing.GroupLayout(jpnPage2);
        jpnPage2.setLayout(jpnPage2Layout);
        jpnPage2Layout.setHorizontalGroup(
            jpnPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPage2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPesquisa1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnPage2Layout.setVerticalGroup(
            jpnPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPage2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPesquisa1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pagesFilter.add(jpnPage2, "page2");

        jpnPage3.setBackground(new java.awt.Color(204, 204, 204));

        txtPesquisa2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPesquisa2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesquisa2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisa2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesquisa2KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jpnPage3Layout = new javax.swing.GroupLayout(jpnPage3);
        jpnPage3.setLayout(jpnPage3Layout);
        jpnPage3Layout.setHorizontalGroup(
            jpnPage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPage3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPesquisa2, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnPage3Layout.setVerticalGroup(
            jpnPage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPage3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPesquisa2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pagesFilter.add(jpnPage3, "page3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(pagesFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbPesquisarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbPesquisarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagesFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.dispose();
        new ViewHomePage().setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        if ("".equals(txtName.getText().trim())) {
            JOptionPane.showMessageDialog(rootPane, "Prencha todos os campos obrigatórios!", "Campo em Branco!", JOptionPane.ERROR_MESSAGE, null);
        } else {
            String name = txtName.getText().trim();
            String url = "http://" + ip + ":3030/clients/create";

            String body = null;
            try {
                body = "{\n"
                        + "    \"name\": \"" + name + "\"\n"
                        + "}";
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Informações inválidas!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
            }

            JSONObject my_obj = null;
            try {
                ConsumidorAPI consumidor = ConsumidorAPI.getInstance();
                my_obj = new JSONObject(consumidor.doPost(url, body).replace("[", "").replace("]", ""));
            } catch (JSONException jSONException) {
            }

            switch (System.getProperty("code")) {
                case "201":
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "CRUD - Informa:", JOptionPane.INFORMATION_MESSAGE);
                    limpar();
                    pesquisa1();
                    System.setProperty("code", String.valueOf(0));
                    break;
                case "400":
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o cliente!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                    limpar();
                    System.setProperty("code", String.valueOf(0));
                    break;
                case "409":
                    JOptionPane.showMessageDialog(null, "Cliente já cadastrado!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                    limpar();
                    System.setProperty("code", String.valueOf(0));
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        switch (JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir esse cliente?")) {
            case 0:
                String url = "http://" + ip + ":3030/clients/" + System.getProperty("idClient") + "/delete";
                try {
                    ConsumidorAPI consumidor = ConsumidorAPI.getInstance();
                    consumidor.doDelete(url);
                } catch (Exception ex) {
                }

                switch (System.getProperty("code")) {
                    case "204":
                        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!", "CRUD - Informa:", JOptionPane.INFORMATION_MESSAGE);
                        limpar();
                        pesquisa1();
                        btnExcluir.setEnabled(false);
                        btnAlterar.setEnabled(false);
                        System.setProperty("code", String.valueOf(0));
                        System.setProperty("idClient", String.valueOf(0));
                        break;
                    case "400":
                        JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                        limpar();
                        pesquisa1();
                        btnExcluir.setEnabled(false);
                        btnAlterar.setEnabled(false);
                        System.setProperty("code", String.valueOf(0));
                        System.setProperty("idClient", String.valueOf(0));
                        break;
                    case "404":
                        JOptionPane.showMessageDialog(null, "O cliente não existe!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                        limpar();
                        pesquisa1();
                        btnExcluir.setEnabled(false);
                        btnAlterar.setEnabled(false);
                        System.setProperty("code", String.valueOf(0));
                        System.setProperty("idClient", String.valueOf(0));
                        break;
                    default:
                        break;
                }
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if ("".equals(txtName.getText().trim()) || System.getProperty("idClient").equals("0") || System.getProperty("idClient") == null) {
            JOptionPane.showMessageDialog(null, "Prencha todos os campos obrigatórios!", "Campo em Branco!", JOptionPane.ERROR_MESSAGE, null);
        } else {
            switch (JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja atualizar esse cliente?")) {
                case 0:
                    String name = txtName.getText().trim();
                    String url = "http://" + ip + ":3030/clients/" + System.getProperty("idClient") + "/update";
                    String body = null;
                    try {
                        body = "{\n"
                                + "    \"name\": \"" + name + "\"\n"
                                + "}";
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Informações inválidas!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        ConsumidorAPI consumidor = ConsumidorAPI.getInstance();
                        consumidor.doPatch(url, body);
                    } catch (Exception ex) {
                    }

                    switch (System.getProperty("code")) {
                        case "200":
                            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!", "CRUD - Informa:", JOptionPane.INFORMATION_MESSAGE);
                            limpar();
                            pesquisa1();
                            btnExcluir.setEnabled(false);
                            btnAlterar.setEnabled(false);
                            System.setProperty("code", String.valueOf(0));
                            System.setProperty("idClient", String.valueOf(0));
                            break;
                        case "400":
                            JOptionPane.showMessageDialog(null, "Erro ao atualizar o cliente!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                            limpar();
                            pesquisa1();
                            btnExcluir.setEnabled(false);
                            btnAlterar.setEnabled(false);
                            System.setProperty("code", String.valueOf(0));
                            System.setProperty("idClient", String.valueOf(0));
                            break;
                        case "404":
                            JOptionPane.showMessageDialog(null, "O cliente não existe!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                            limpar();
                            pesquisa1();
                            btnExcluir.setEnabled(false);
                            btnAlterar.setEnabled(false);
                            System.setProperty("code", String.valueOf(0));
                            System.setProperty("idClient", String.valueOf(0));
                            break;
                        default:
                            break;
                    }
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void tableClientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientsMouseClicked
        int rowSel = tableClients.getSelectedRow();//pega o indice da linha na tabela
        int indexRowModel = tableClients.getRowSorter().convertRowIndexToModel(rowSel);//converte pro indice do model

        String iDProduto = String.valueOf(tableClients.getModel().getValueAt(indexRowModel, 0));
        System.setProperty("idClient", iDProduto);
        String usuGrupo = String.valueOf(tableClients.getModel().getValueAt(indexRowModel, 1));
        txtName.setText(usuGrupo);

        btnExcluir.setEnabled(true);
        btnAlterar.setEnabled(true);
    }//GEN-LAST:event_tableClientsMouseClicked

    private void tableClientsAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tableClientsAncestorAdded

    }//GEN-LAST:event_tableClientsAncestorAdded

    private void txtPesquisaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyTyped
        int limitedType = 100;
        int k = evt.getKeyChar();
        if (txtPesquisa.getText().length() <= limitedType - 1) {
            //deixe passar
        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
        }
    }//GEN-LAST:event_txtPesquisaKeyTyped

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        pesquisa1();
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void cbbPesquisarPorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbPesquisarPorItemStateChanged
        CardLayout cl = (CardLayout) pagesFilter.getLayout();
        switch (cbbPesquisarPor.getSelectedIndex()) {
            case 0:
                cl.show(pagesFilter, "page1");
                break;
            case 1:
                cl.show(pagesFilter, "page2");
                break;
            case 2:
                cl.show(pagesFilter, "page3");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cbbPesquisarPorItemStateChanged

    private void txtPesquisa1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisa1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisa1KeyReleased

    private void txtPesquisa1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisa1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisa1KeyTyped

    private void txtPesquisa2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisa2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisa2KeyReleased

    private void txtPesquisa2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisa2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisa2KeyTyped

    private void tableClientsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableClientsKeyReleased
        int rowSel = tableClients.getSelectedRow();//pega o indice da linha na tabela
        int indexRowModel = tableClients.getRowSorter().convertRowIndexToModel(rowSel);//converte pro indice do model

        String iDProduto = String.valueOf(tableClients.getModel().getValueAt(indexRowModel, 0));
        System.setProperty("idClient", iDProduto);
        String usuGrupo = String.valueOf(tableClients.getModel().getValueAt(indexRowModel, 1));
        txtName.setText(usuGrupo);

        btnExcluir.setEnabled(true);
        btnAlterar.setEnabled(true);
    }//GEN-LAST:event_tableClientsKeyReleased

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JComboBox<String> cbbPesquisarPor;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel jpnPage1;
    private javax.swing.JPanel jpnPage2;
    private javax.swing.JPanel jpnPage3;
    private javax.swing.JPanel pagesFilter;
    private javax.swing.JTable tableClients;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtPesquisa1;
    private javax.swing.JTextField txtPesquisa2;
    // End of variables declaration//GEN-END:variables
}
