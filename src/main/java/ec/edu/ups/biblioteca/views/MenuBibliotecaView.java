/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.ups.biblioteca.views;

import ec.edu.ups.biblioteca.controllers.AutorController;
import ec.edu.ups.biblioteca.controllers.DevolucionController;
import ec.edu.ups.biblioteca.controllers.EjemplarLibroController;
import ec.edu.ups.biblioteca.controllers.LibroController;
import ec.edu.ups.biblioteca.controllers.PrestamoController;
import ec.edu.ups.biblioteca.controllers.UsuarioController;
import ec.edu.ups.biblioteca.dao.AutorDAO;
import ec.edu.ups.biblioteca.dao.EjemplarLibroDAO;
import ec.edu.ups.biblioteca.dao.LibroDAO;
import ec.edu.ups.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.biblioteca.utils.Idioma;                         
import ec.edu.ups.biblioteca.utils.Idiomatizable;
        

/**
 *
 * @author jimen
 */
public class MenuBibliotecaView extends javax.swing.JFrame {
    //verdadero:
    /////
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuBibliotecaView.class.getName());
    private AutorController autorController;
    private LibroController libroController;
    private UsuarioController usuarioController;
    private EjemplarLibroController ejemplarLibroController;
    private PrestamoController prestamoController;
    private DevolucionController devolucionController;

    // Guardamos las vistas ya conectadas a sus Controllers en main().
    // Antes el menú creaba vistas NUEVAS (sin Controller) en cada click,
    // por eso los botones no hacían nada al abrir desde el menú.
    private AutorView autorView;
    private LibroView libroView;
    private UsuarioView usuarioView;
    private PrestamoView prestamoView;
    private DevolucionView devolucionView;
    
    /**
     * Creates new form MenuBibliotecarioView
     */
    public MenuBibliotecaView() {
        initComponents();
        
        AutorDAO autorDAO = AutorDAO.getAutorDAO();
        UsuarioDAO usuarioDAO = UsuarioDAO.getUsuarioDAO();
        LibroDAO libroDAO = LibroDAO.getLibroDAO();
        EjemplarLibroDAO ejemplarLibroDAO = EjemplarLibroDAO.getEjemplarLibroDAO();
        PrestamoDAO prestamoDAO = PrestamoDAO.getPrestamoDAO(libroDAO, usuarioDAO);
        
        autorView = new AutorView();
        usuarioView = new UsuarioView();
        libroView = new LibroView();
        prestamoView = new PrestamoView();
        devolucionView = new DevolucionView();
        
        autorController = new AutorController(autorDAO, autorView);
        usuarioController = new UsuarioController(usuarioDAO, usuarioView);
        ejemplarLibroController = new EjemplarLibroController(ejemplarLibroDAO);
        libroController = new LibroController(libroDAO, libroView, autorController, ejemplarLibroController);
        prestamoController = new PrestamoController(prestamoDAO, usuarioController, libroController, ejemplarLibroController, prestamoView);
        devolucionController = new DevolucionController(prestamoDAO, devolucionView, ejemplarLibroController);
        
        this.autorController = autorController;
        this.libroController = libroController;
        this.usuarioController = usuarioController;
        this.ejemplarLibroController = ejemplarLibroController;
        this.prestamoController = prestamoController;
        this.autorView = autorView;
        this.libroView = libroView;
        this.usuarioView = usuarioView;
        this.prestamoView = prestamoView;
        this.devolucionView = devolucionView;
        aplicarIdioma();
        
    }
   
    private void aplicarIdioma() {
    java.util.ResourceBundle bundle = Idioma.getBundle();
    
    jLabel3.setText(bundle.getString("menu.bienvenida"));
    jLabel2.setText(bundle.getString("menu.pregunta"));

    btnLibro.setText(bundle.getString("menu.libros"));
    gestionarLibrosMenuItem.setText(bundle.getString("menu.gestionarLibros"));

    btnAutores.setText(bundle.getString("menu.autores"));
    gestionarAutoresMenuItem.setText(bundle.getString("menu.gestionarAutores"));

    btnPrestamos.setText(bundle.getString("menu.prestamos"));
    gestionarPrestamosMenuItem.setText(bundle.getString("menu.gestionarPrestamos"));

    btnUsuario.setText(bundle.getString("menu.usuarios"));
    gestionarUsuariosMenuItem.setText(bundle.getString("menu.gestionarUsuarios"));

    btnDevoluciones.setText(bundle.getString("menu.devoluciones"));
    gestionarDevolucionesMenuItem.setText(bundle.getString("menu.gestionarDevoluciones"));

    btnIdioma.setText(bundle.getString("menu.idioma"));
    }
    
    private void actualizarIdiomaVentanasAbiertas() {
        for (javax.swing.JInternalFrame frame : jDesktopPane1.getAllFrames()) {
            if (frame instanceof Idiomatizable) {
                ((Idiomatizable) frame).aplicarIdioma();
            }
        }
    }
    
   
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        btnLibro = new javax.swing.JMenu();
        gestionarLibrosMenuItem = new javax.swing.JMenuItem();
        btnAutores = new javax.swing.JMenu();
        gestionarAutoresMenuItem = new javax.swing.JMenuItem();
        btnPrestamos = new javax.swing.JMenu();
        gestionarPrestamosMenuItem = new javax.swing.JMenuItem();
        btnUsuario = new javax.swing.JMenu();
        gestionarUsuariosMenuItem = new javax.swing.JMenuItem();
        btnDevoluciones = new javax.swing.JMenu();
        gestionarDevolucionesMenuItem = new javax.swing.JMenuItem();
        btnIdioma = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú de la Biblioteca");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel2.setBackground(new java.awt.Color(51, 51, 0));

        jDesktopPane1.setBackground(new java.awt.Color(96, 96, 67));

        jLabel3.setText("===== BIENVENIDO A LA BIBLIOTECA =====");

        jLabel2.setText("¿Qué quieres realizar hoy?");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/biblio.png"))); // NOI18N

        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(336, 336, 336)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addGap(45, 45, 45))
                            .addComponent(jLabel3))))
                .addContainerGap(539, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(77, 77, 77)
                .addComponent(jLabel4)
                .addContainerGap(311, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnLibro.setText("Libros");

        gestionarLibrosMenuItem.setText("GestionarLibros");
        gestionarLibrosMenuItem.addActionListener(this::gestionarLibrosMenuItemActionPerformed);
        btnLibro.add(gestionarLibrosMenuItem);

        jMenuBar1.add(btnLibro);

        btnAutores.setText("Autores");

        gestionarAutoresMenuItem.setText("Gestionar Autores");
        gestionarAutoresMenuItem.addActionListener(this::gestionarAutoresMenuItemActionPerformed);
        btnAutores.add(gestionarAutoresMenuItem);

        jMenuBar1.add(btnAutores);

        btnPrestamos.setText("Prestamos");

        gestionarPrestamosMenuItem.setText("Gestionar Prestamos");
        gestionarPrestamosMenuItem.addActionListener(this::gestionarPrestamosMenuItemActionPerformed);
        btnPrestamos.add(gestionarPrestamosMenuItem);

        jMenuBar1.add(btnPrestamos);

        btnUsuario.setText("Usuarios");

        gestionarUsuariosMenuItem.setText("Gestionar Usuarios");
        gestionarUsuariosMenuItem.addActionListener(this::gestionarUsuariosMenuItemActionPerformed);
        btnUsuario.add(gestionarUsuariosMenuItem);

        jMenuBar1.add(btnUsuario);

        btnDevoluciones.setText("Devoluciones");

        gestionarDevolucionesMenuItem.setText("Gestionar Devoluciones");
        gestionarDevolucionesMenuItem.addActionListener(this::gestionarDevolucionesMenuItemActionPerformed);
        btnDevoluciones.add(gestionarDevolucionesMenuItem);

        jMenuBar1.add(btnDevoluciones);

        btnIdioma.setText("Idioma");

        jMenuItem16.setText("Ingles");
        jMenuItem16.addActionListener(this::jMenuItem16ActionPerformed);
        btnIdioma.add(jMenuItem16);

        jMenuItem17.setText("Español");
        jMenuItem17.addActionListener(this::jMenuItem17ActionPerformed);
        btnIdioma.add(jMenuItem17);

        jMenuItem18.setText("Portugues");
        jMenuItem18.setToolTipText("");
        jMenuItem18.addActionListener(this::jMenuItem18ActionPerformed);
        btnIdioma.add(jMenuItem18);

        jMenuBar1.add(btnIdioma);

        jMenu1.setText("jMenu1");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        Idioma.setIdioma("en");
        aplicarIdioma();
        actualizarIdiomaVentanasAbiertas();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void gestionarAutoresMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionarAutoresMenuItemActionPerformed
        if (autorView.getParent() == null) {
            jDesktopPane1.add(autorView);
            autorView.setLocation(20, 20);
        }
        autorView.aplicarIdioma();
        autorView.setVisible(true);
        try { autorView.setSelected(true); } catch (java.beans.PropertyVetoException ex) { }
    }//GEN-LAST:event_gestionarAutoresMenuItemActionPerformed

    private void gestionarLibrosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionarLibrosMenuItemActionPerformed
        if (libroView.getParent() == null) {
            jDesktopPane1.add(libroView);
            libroView.setLocation(20, 20);
        }
        libroView.aplicarIdioma();
        libroView.setVisible(true);
        
        try { libroView.setSelected(true); 
        } 
        catch (java.beans.PropertyVetoException ex) { }
    }//GEN-LAST:event_gestionarLibrosMenuItemActionPerformed

    private void gestionarUsuariosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionarUsuariosMenuItemActionPerformed
        if (usuarioView.getParent() == null) {
            jDesktopPane1.add(usuarioView);
            usuarioView.setLocation(20, 20);
        }
        usuarioView.aplicarIdioma();
        usuarioView.setVisible(true);
        try { usuarioView.setSelected(true); } catch (java.beans.PropertyVetoException ex) { }
    }//GEN-LAST:event_gestionarUsuariosMenuItemActionPerformed

    private void gestionarDevolucionesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionarDevolucionesMenuItemActionPerformed
        if (devolucionView.getParent() == null) {
            jDesktopPane1.add(devolucionView);
            devolucionView.setLocation(20, 20);
        }
        devolucionView.aplicarIdioma();
        devolucionView.setVisible(true);
        try { devolucionView.setSelected(true); } catch (java.beans.PropertyVetoException ex) { }
    }//GEN-LAST:event_gestionarDevolucionesMenuItemActionPerformed

    private void gestionarPrestamosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionarPrestamosMenuItemActionPerformed
        if (prestamoView.getParent() == null) {
            jDesktopPane1.add(prestamoView);
            prestamoView.setLocation(20, 20);
        }
        prestamoView.aplicarIdioma();
        prestamoView.setVisible(true);
        try { prestamoView.setSelected(true); } catch (java.beans.PropertyVetoException ex) { }
    }//GEN-LAST:event_gestionarPrestamosMenuItemActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        Idioma.setIdioma("es");
        aplicarIdioma();
        actualizarIdiomaVentanasAbiertas();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
        Idioma.setIdioma("pt");
        aplicarIdioma();
        actualizarIdiomaVentanasAbiertas();
    }//GEN-LAST:event_jMenuItem18ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
         
        
        java.awt.EventQueue.invokeLater(() -> {
            MenuBibliotecaView menu = new MenuBibliotecaView(
            );
            menu.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            menu.setVisible(true);
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu btnAutores;
    private javax.swing.JMenu btnDevoluciones;
    private javax.swing.JMenu btnIdioma;
    private javax.swing.JMenu btnLibro;
    private javax.swing.JMenu btnPrestamos;
    private javax.swing.JMenu btnUsuario;
    private javax.swing.JMenuItem gestionarAutoresMenuItem;
    private javax.swing.JMenuItem gestionarDevolucionesMenuItem;
    private javax.swing.JMenuItem gestionarLibrosMenuItem;
    private javax.swing.JMenuItem gestionarPrestamosMenuItem;
    private javax.swing.JMenuItem gestionarUsuariosMenuItem;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
