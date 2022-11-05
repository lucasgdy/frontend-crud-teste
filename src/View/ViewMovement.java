package View;

import Controllers.ConsumidorAPI;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ViewMovement extends javax.swing.JFrame {

    String ip = "localhost";

    public ViewMovement() {
        initComponents();
        fillJtable();
        pesquisa1();
        popularCbb();
    }

    private void limpar() {
        cbbContainer.getModel().setSelectedItem("Selecione");
        cbbType.getModel().setSelectedItem("Selecione");
    }

    private void popularCbb() {
        cbbContainer.removeAll();
        JSONArray my_obj = null;
        String url = "http://" + ip + ":3030/containers";

        try {
            ConsumidorAPI consumidor = ConsumidorAPI.getInstance();
            my_obj = new JSONArray(consumidor.doGet(url));
        } catch (JSONException jSONException) {
        }

        for (int i = 0; i < my_obj.length(); i++) {
            cbbContainer.addItem(String.valueOf(my_obj.getJSONObject(i).getString("name")));
        }
    }

    private void pesquisa1() {
        DefaultTableModel model = (DefaultTableModel) tableMovements.getModel();
        model.setNumRows(0);
        if ("".equals(txtPesquisa.getText().trim())) {
            JSONArray my_obj = null;
            String url = "http://" + ip + ":3030/movements";
            try {
                ConsumidorAPI consumidor = ConsumidorAPI.getInstance();
                my_obj = new JSONArray(consumidor.doGet(url));
            } catch (JSONException jSONException) {
            }

            switch (System.getProperty("code")) {
                case "200":
                    SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yy HH:mm"); // download db
                    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // upload db
                    for (int i = 0; i < my_obj.length(); i++) {
                        try {

                            String startDate = formatter2.format(formatter1.parse(my_obj.getJSONObject(i).getString("startDate").replace("T", " ").replace(".000Z", ":00")));
                            String endDate = formatter2.format(formatter1.parse(my_obj.getJSONObject(i).getString("endDate").replace("T", " ").replace(".000Z", ":00")));
                            model.addRow(new Object[]{my_obj.getJSONObject(i).get("id"), my_obj.getJSONObject(i).getJSONObject("Container").getString("name"), my_obj.getJSONObject(i).getString("type"), startDate, endDate});
                        } catch (ParseException | JSONException e) {
                            System.out.println(e);
                        }
                    }

                    System.setProperty("code", String.valueOf(0));
                    break;
                case "400":
                    JOptionPane.showMessageDialog(null, "Erro ao consultar as movimentações cadastradas!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
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
        DefaultTableModel model1 = (DefaultTableModel) tableMovements.getModel();
        JTableHeader header = tableMovements.getTableHeader();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centralizado = (DefaultTableCellRenderer) header.getDefaultRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tableMovements.setAutoResizeMode(AUTO_RESIZE_OFF);
        tableMovements.setDefaultRenderer(String.class, centerRenderer);
        tableMovements.setRowSorter(new TableRowSorter(model1));
        model1.setNumRows(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMovements = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbbContainer = new javax.swing.JComboBox<>();
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
        jLabel6 = new javax.swing.JLabel();
        cbbType = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        spnInicio = new javax.swing.JSpinner();
        spnFim = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CRUD - Movimentações");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        tableMovements.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Container", "Tipo", "Início", "Fim"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMovements.setRowHeight(25);
        tableMovements.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableMovements.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tableMovementsAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tableMovements.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMovementsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMovements);
        if (tableMovements.getColumnModel().getColumnCount() > 0) {
            tableMovements.getColumnModel().getColumn(0).setMinWidth(80);
            tableMovements.getColumnModel().getColumn(0).setPreferredWidth(80);
            tableMovements.getColumnModel().getColumn(0).setMaxWidth(100);
            tableMovements.getColumnModel().getColumn(1).setMinWidth(150);
            tableMovements.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableMovements.getColumnModel().getColumn(2).setMinWidth(150);
            tableMovements.getColumnModel().getColumn(2).setPreferredWidth(150);
            tableMovements.getColumnModel().getColumn(3).setMinWidth(150);
            tableMovements.getColumnModel().getColumn(3).setPreferredWidth(150);
            tableMovements.getColumnModel().getColumn(4).setMinWidth(150);
            tableMovements.getColumnModel().getColumn(4).setPreferredWidth(150);
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

        jLabel1.setText("Container:*");

        cbbContainer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        cbbContainer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbContainerItemStateChanged(evt);
            }
        });

        cbbPesquisarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Container" }));
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

        jLabel6.setText("Tipo:*");

        cbbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Embarque", "Descarga", "Gate In", "Gate Out", "Reposicionamento", "Pesagem", "Scanner" }));
        cbbType.setMaximumSize(new java.awt.Dimension(181, 35));
        cbbType.setMinimumSize(new java.awt.Dimension(181, 35));
        cbbType.setPreferredSize(new java.awt.Dimension(181, 35));

        jLabel7.setText("Data e Hora de Início:*");

        spnInicio.setModel(new javax.swing.SpinnerDateModel());
        spnInicio.setMaximumSize(new java.awt.Dimension(165, 35));
        spnInicio.setMinimumSize(new java.awt.Dimension(165, 35));
        spnInicio.setPreferredSize(new java.awt.Dimension(165, 35));

        spnFim.setModel(new javax.swing.SpinnerDateModel());
        spnFim.setMaximumSize(new java.awt.Dimension(165, 35));
        spnFim.setMinimumSize(new java.awt.Dimension(165, 35));
        spnFim.setPreferredSize(new java.awt.Dimension(165, 35));

        jLabel8.setText("Data e Hora de Fim:*");

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
                            .addComponent(cbbContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbPesquisarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spnFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(105, 105, 105)
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
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
        if (cbbType.getModel().getSelectedItem().equals("Selecione") || cbbContainer.getModel().getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(rootPane, "Prencha todos os campos obrigatórios!", "Campo em Branco!", JOptionPane.ERROR_MESSAGE, null);
        } else {
            try {
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // upload db
                Date date1 = (Date) spnInicio.getModel().getValue();
                Date date2 = (Date) spnFim.getModel().getValue();

                if (date2.compareTo(date1) < 0) {
                    JOptionPane.showMessageDialog(rootPane, "A data de início é maior que a do fim!", "Campo em Branco!", JOptionPane.ERROR_MESSAGE, null);
                } else {
                    String idContainer = System.getProperty("idContainer");
                    String type = cbbType.getModel().getSelectedItem().toString();
                    String url = "http://" + ip + ":3030/movements/create";
                    String body = null;
                    try {
                        body = "{\n"
                                + "    \"idContainer\": " + idContainer + ",\n"
                                + "    \"type\": \"" + type + "\",\n"
                                + "    \"startDate\": \"" + formatter1.format(date1) + "\",\n"
                                + "    \"endDate\": \"" + formatter1.format(date2) + "\"\n"
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
                            JOptionPane.showMessageDialog(null, "Movimentação cadastrada com sucesso!", "CRUD - Informa:", JOptionPane.INFORMATION_MESSAGE);
                            limpar();
                            pesquisa1();
                            System.setProperty("code", String.valueOf(0));
                            System.setProperty("idMovement", String.valueOf(0));
                            System.setProperty("idContainer", String.valueOf(0));
                            break;
                        case "400":
                            JOptionPane.showMessageDialog(null, "Erro ao cadastrar a movimentação!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                            limpar();
                            System.setProperty("code", String.valueOf(0));
                            System.setProperty("idMovement", String.valueOf(0));
                            System.setProperty("idContainer", String.valueOf(0));
                            break;
                        default:
                            break;
                    }
                }
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Informações inválidas!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        switch (JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir essa movimentação?")) {
            case 0:

                String url = "http://" + ip + ":3030/movements/" + System.getProperty("idMovement") + "/delete";
                try {
                    ConsumidorAPI consumidor = ConsumidorAPI.getInstance();
                    consumidor.doDelete(url);
                } catch (Exception ex) {
                }

                switch (System.getProperty("code")) {
                    case "204":
                        JOptionPane.showMessageDialog(null, "Movimentação excluída com sucesso!", "CRUD - Informa:", JOptionPane.INFORMATION_MESSAGE);
                        limpar();
                        pesquisa1();
                        btnExcluir.setEnabled(false);
                        btnAlterar.setEnabled(false);
                        System.setProperty("code", String.valueOf(0));
                        System.setProperty("idMovement", String.valueOf(0));
                        System.setProperty("idContainer", String.valueOf(0));
                        break;
                    case "400":
                        JOptionPane.showMessageDialog(null, "Erro ao excluir a movimentação!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                        limpar();
                        pesquisa1();
                        btnExcluir.setEnabled(false);
                        btnAlterar.setEnabled(false);
                        System.setProperty("code", String.valueOf(0));
                        System.setProperty("idMovement", String.valueOf(0));
                        System.setProperty("idContainer", String.valueOf(0));
                        break;
                    case "404":
                        JOptionPane.showMessageDialog(null, "O modelo não existe!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                        limpar();
                        pesquisa1();
                        btnExcluir.setEnabled(false);
                        btnAlterar.setEnabled(false);
                        System.setProperty("code", String.valueOf(0));
                        System.setProperty("idMovement", String.valueOf(0));
                        System.setProperty("idContainer", String.valueOf(0));
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
        if (cbbType.getModel().getSelectedItem().equals("Selecione") || cbbContainer.getModel().getSelectedItem().equals("Selecione")) {
            JOptionPane.showMessageDialog(rootPane, "Prencha todos os campos obrigatórios!", "Campo em Branco!", JOptionPane.ERROR_MESSAGE, null);
        } else {
            switch (JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja atualizar essa movimentação?")) {
                case 0:
                    try {
                        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // upload db
                        Date date1 = (Date) spnInicio.getModel().getValue();
                        Date date2 = (Date) spnFim.getModel().getValue();

                        if (date2.compareTo(date1) < 0) {
                            JOptionPane.showMessageDialog(rootPane, "A data de início é maior que a do fim!", "Campo em Branco!", JOptionPane.ERROR_MESSAGE, null);
                        } else {
                            String idContainer = System.getProperty("idContainer");
                            String type = cbbType.getModel().getSelectedItem().toString();
                            String url = "http://" + ip + ":3030/movements/" + System.getProperty("idMovement") + "/update";
                            String body = null;
                            try {

                                body = "{\n"
                                        + "    \"idContainer\": " + idContainer + ",\n"
                                        + "    \"type\": \"" + type + "\",\n"
                                        + "    \"startDate\": \"" + formatter1.format(date1) + "\",\n"
                                        + "    \"endDate\": \"" + formatter1.format(date2) + "\"\n"
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
                                    JOptionPane.showMessageDialog(null, "Movimentação atualizada com sucesso!", "CRUD - Informa:", JOptionPane.INFORMATION_MESSAGE);
                                    limpar();
                                    pesquisa1();
                                    btnExcluir.setEnabled(false);
                                    btnAlterar.setEnabled(false);
                                    System.setProperty("code", String.valueOf(0));
                                    System.setProperty("idMovement", String.valueOf(0));
                                    System.setProperty("idContainer", String.valueOf(0));
                                    break;
                                case "400":
                                    JOptionPane.showMessageDialog(null, "Erro ao atualizar a movimentação!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                                    limpar();
                                    pesquisa1();
                                    btnExcluir.setEnabled(false);
                                    btnAlterar.setEnabled(false);
                                    System.setProperty("code", String.valueOf(0));
                                    System.setProperty("idMovement", String.valueOf(0));
                                    System.setProperty("idContainer", String.valueOf(0));
                                    break;
                                case "404":
                                    JOptionPane.showMessageDialog(null, "A movimentação não existe!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                                    limpar();
                                    pesquisa1();
                                    btnExcluir.setEnabled(false);
                                    btnAlterar.setEnabled(false);
                                    System.setProperty("code", String.valueOf(0));
                                    System.setProperty("idMovement", String.valueOf(0));
                                    System.setProperty("idContainer", String.valueOf(0));
                                    break;
                                default:
                                    break;
                            }
                        }
                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Informações inválidas!", "CRUD - Informa:", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void tableMovementsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMovementsMouseClicked
        try {
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yy HH:mm"); // download db
            int rowSel = tableMovements.getSelectedRow();//pega o indice da linha na tabela
            int indexRowModel = tableMovements.getRowSorter().convertRowIndexToModel(rowSel);//converte pro indice do model

            String iDProduto = String.valueOf(tableMovements.getModel().getValueAt(indexRowModel, 0));
            System.setProperty("idMovement", iDProduto);
            String usuGrupo = String.valueOf(tableMovements.getModel().getValueAt(indexRowModel, 1));
            cbbContainer.getModel().setSelectedItem(usuGrupo);
            String usuGrupo1 = String.valueOf(tableMovements.getModel().getValueAt(indexRowModel, 2));
            cbbType.getModel().setSelectedItem(usuGrupo1);
            String usuGrupo2 = String.valueOf(tableMovements.getModel().getValueAt(indexRowModel, 3));
            spnInicio.getModel().setValue(formatter2.parse(usuGrupo2));
            String usuGrupo3 = String.valueOf(tableMovements.getModel().getValueAt(indexRowModel, 4));
            spnFim.getModel().setValue(formatter2.parse(usuGrupo3));

            btnExcluir.setEnabled(true);
            btnAlterar.setEnabled(true);
        } catch (ParseException e) {
        }
    }//GEN-LAST:event_tableMovementsMouseClicked

    private void tableMovementsAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tableMovementsAncestorAdded

    }//GEN-LAST:event_tableMovementsAncestorAdded

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

    private void cbbContainerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbContainerItemStateChanged
        if (cbbContainer.getModel().getSelectedItem().equals("Selecione")) {
        } else {
            String name = cbbContainer.getModel().getSelectedItem().toString();
            String url = "http://" + ip + ":3030/containers/pk";
            String body = "{\"name\": \"" + name + "\"}";
            JSONObject my_obj = null;
            try {
                ConsumidorAPI consumidor = ConsumidorAPI.getInstance();
                my_obj = new JSONObject(consumidor.doPost(url, body));
                System.setProperty("idContainer", String.valueOf(my_obj.get("id")));
            } catch (JSONException jSONException) {
            }
        }
    }//GEN-LAST:event_cbbContainerItemStateChanged

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
            java.util.logging.Logger.getLogger(ViewMovement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewMovement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewMovement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewMovement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ViewMovement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JComboBox<String> cbbContainer;
    private javax.swing.JComboBox<String> cbbPesquisarPor;
    private javax.swing.JComboBox<String> cbbType;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel jpnPage1;
    private javax.swing.JPanel jpnPage2;
    private javax.swing.JPanel jpnPage3;
    private javax.swing.JPanel pagesFilter;
    private javax.swing.JSpinner spnFim;
    private javax.swing.JSpinner spnInicio;
    private javax.swing.JTable tableMovements;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtPesquisa1;
    private javax.swing.JTextField txtPesquisa2;
    // End of variables declaration//GEN-END:variables
}
