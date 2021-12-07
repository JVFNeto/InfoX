/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    //aqui eu vou usar a variavel conexao 
    Connection conexao = null;
//duas variaveis especiais de apoio a conexao ao BD
//PreparedStatement é outro framework, pst é o nome
    PreparedStatement pst = null; //está variavel prepara a consulta ao BD
    ResultSet rs = null; //ela vai executar a variavel de cima, e exibir os resultado da conexão

    public TelaUsuario() { //Construtor
        initComponents();
        conexao = ModuloConexao.conector(); // chamando o metodo conector
    }

    //metodo do read (CRUD)
    private void consultar() {
        String sql = "select * from tbusuarios where iduser=?";

        try {
            pst = conexao.prepareStatement(sql);//faz a "tradução" sql
            pst.setString(1, txtUId.getText());//Substitui o ? po oq foi escrito no txtUId
            rs = pst.executeQuery();//executa a Query (linhas acima)

            if (rs.next()) {
                txtUUsuario.setText(rs.getString(2));//setando nos campos, oq
                txtUFone.setText(rs.getString(3));//ele trouxe do Banco de dados
                txtULogin.setText(rs.getString(4));//campo.setando o texto
                txtUsenha.setText(rs.getString(5));//(query. campo do BD);
                cbxUPerfil.setSelectedItem(rs.getString(6));
                //as linhas acima, substituem os campos pelos dados do BD.

            } else {
                JOptionPane.showMessageDialog(null, "Este usuário não foi cadastrado ainda");

                //as linhas abaixo "limpam" os campos 
                txtUUsuario.setText(null);
                txtUFone.setText(null);
                txtULogin.setText(null);
                txtUsenha.setText(null);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    //metodo do create (CRUD)
    private void adicionar() {

        String sql = "insert into tbusuarios (iduser, usuario, fone, login, senha , perfil) values (?, ?, ?, ?, ?, ?)";

        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUId.getText());
            pst.setString(2, txtUUsuario.getText()); //setando o tipo String
            pst.setString(3, txtUFone.getText());//(campo do BD, Var do app.
            pst.setString(4, txtULogin.getText());// get texto);
            pst.setString(5, txtUsenha.getText());
            pst.setString(6, cbxUPerfil.getSelectedItem().toString());
            //precisa-se converter pra string se não o java reclama 

            //validação dos campos obrigatórios 
            if ((txtUId.getText().isEmpty()) || (txtUUsuario.getText().isEmpty()) || (txtULogin.getText().isEmpty()) || (txtUsenha.getText().isEmpty())) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                //a estrutra abaixo é usada para cofirmar a inserção dos dados na tabela
                //a linha abaixo atualiza a tbusuarios com os dados do formulario
                int adicionado = pst.executeUpdate();
                //apoio ao codigo 
                //System.out.println(adicionado); 

                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                    //as linhas abaixo "limpam" os campos 
                    txtUId.setText(null);
                    txtUUsuario.setText(null);
                    txtUFone.setText(null);
                    txtULogin.setText(null);
                    txtUsenha.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //metodo alterar
    private void alterar() {

        String sql = "update tbusuarios set usuario=?, fone=?, login=?, senha=?, perfil=? where iduser =?";
        //2, 3, 4, 5, 6, 1

        try {
            
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, txtUUsuario.getText());
            pst.setString(2, txtUFone.getText());
            pst.setString(3, txtULogin.getText());
            pst.setString(4, txtUsenha.getText());
            pst.setString(5, cbxUPerfil.getSelectedItem().toString());
            pst.setString(6, txtUId.getText());
            
            
            //cntrl c + cntrl v
             if ((txtUId.getText().isEmpty()) || (txtUUsuario.getText().isEmpty()) || (txtULogin.getText().isEmpty()) || (txtUsenha.getText().isEmpty())) {

                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

                //a estrutra abaixo é usada para cofirmar a alterçaõ dos dados na tabela
                //a linha abaixo atualiza a tbusuarios com os dados do formulario
                int adicionado = pst.executeUpdate();
                //apoio ao codigo 
                //System.out.println(adicionado); 

                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                    //as linhas abaixo "limpam" os campos 
                    txtUId.setText(null);
                    txtUUsuario.setText(null);
                    txtUFone.setText(null);
                    txtULogin.setText(null);
                    txtUsenha.setText(null);

                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    private void remover(){
        
         int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar este usuário?","Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION){
         
        
        String sql = "delete from tbusuarios where iduser=?";
        
        try {
            pst=conexao.prepareStatement(sql);
            
            pst.setString(1, txtUId.getText());
            int removido = pst.executeUpdate();
            
            if (removido > 0) {

                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                    //as linhas abaixo "limpam" os campos 
                    txtUId.setText(null);
                    txtUUsuario.setText(null);
                    txtUFone.setText(null);
                    txtULogin.setText(null);
                    txtUsenha.setText(null);

                }
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
        }
        
    
    
    }}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        TelaUid = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUId = new javax.swing.JTextField();
        txtUUsuario = new javax.swing.JTextField();
        txtUFone = new javax.swing.JTextField();
        txtULogin = new javax.swing.JTextField();
        txtUsenha = new javax.swing.JTextField();
        cbxUPerfil = new javax.swing.JComboBox<>();
        BCreate = new javax.swing.JButton();
        BRead = new javax.swing.JButton();
        BUpdate = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        CObrigatorios = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(640, 480));

        TelaUid.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        TelaUid.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("*Usuário:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Fone:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("*Login:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("*Senha:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("*Perfil:");

        txtUId.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtUUsuario.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtUFone.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtULogin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtUsenha.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbxUPerfil.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbxUPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        BCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        BCreate.setToolTipText(" Adicionar");
        BCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        BCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCreateActionPerformed(evt);
            }
        });

        BRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/search.png"))); // NOI18N
        BRead.setToolTipText("Consultar");
        BRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BRead.setPreferredSize(new java.awt.Dimension(80, 80));
        BRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BReadActionPerformed(evt);
            }
        });

        BUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/upda.png"))); // NOI18N
        BUpdate.setToolTipText("Alterar");
        BUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        BUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUpdateActionPerformed(evt);
            }
        });

        BDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        BDelete.setToolTipText("Apagar");
        BDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        CObrigatorios.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        CObrigatorios.setText("*Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TelaUid)
                        .addGap(43, 43, 43)
                        .addComponent(txtUId)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtUFone, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtUUsuario))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxUPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsenha))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(BRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                                .addComponent(BUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)
                                .addComponent(BDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtULogin)
                                .addGap(5, 5, 5)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CObrigatorios)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TelaUid)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtUFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtULogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(cbxUPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtUsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(CObrigatorios)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BRead, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(48, 48, 48))
        );

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void BReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BReadActionPerformed
        // chamando o metodo consultar

        consultar();
    }//GEN-LAST:event_BReadActionPerformed

    private void BCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCreateActionPerformed

        adicionar();
    }//GEN-LAST:event_BCreateActionPerformed

    private void BUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUpdateActionPerformed
        alterar();
    }//GEN-LAST:event_BUpdateActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
       remover();
    }//GEN-LAST:event_BDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCreate;
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BRead;
    private javax.swing.JButton BUpdate;
    private javax.swing.JLabel CObrigatorios;
    private javax.swing.JLabel TelaUid;
    private javax.swing.JComboBox<String> cbxUPerfil;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtUFone;
    private javax.swing.JTextField txtUId;
    private javax.swing.JTextField txtULogin;
    private javax.swing.JTextField txtUUsuario;
    private javax.swing.JTextField txtUsenha;
    // End of variables declaration//GEN-END:variables
}
