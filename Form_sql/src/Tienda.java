import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

import java.sql.*;
public class Tienda {
    private JPanel panel;
    private JTextField provee_pro;
    private JTextField precio_pro;
    private JTextField fecha_pro;
    private JTextField cod_pro;
    private JTextField nom_pro;
    private JTextField unida_pro;
    private JButton actualizar;
    private JButton borrar;
    private JButton crear;
    private JButton buscar;
public Tienda() {
    crear.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {



        }
    });



    buscar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Connection con;
            try{
            con = getConection();
             String query = "SELECT * FROM  PRODUCTOS WHERE Codigo_Pro = "+cod_pro.getText();;
            Statement dialogo  = con.createStatement();
            ResultSet resultado = dialogo.executeQuery(query);
            ResultSetMetaData datos = resultado.getMetaData();

            int columnCount = datos.getColumnCount();
            JTable table = new JTable();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            if (columnCount > 0){
                JOptionPane.showMessageDialog(null,"Si se encuentra en la Base");
            }else {
                JOptionPane.showMessageDialog(null, "No se encuentra en la Base");
            }
        }catch (HeadlessException | SQLException f){
            System.err.println(f);
        }


        }
    });



    actualizar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Connection con2;
            PreparedStatement ps;

            try {
                con2 = getConection();
                ps = con2.prepareStatement("UPDATE PRODUCTO SET Codigo_Pro=?, Nombre_Pro=?,Univendida_Pro=?,FechaCaduce_Pro=?,Precio_Pro=?,NombreProcedor_Pro=? WHERE Codigo_Pro=?"+cod_pro.getText());
                ps.setString(1,cod_pro.getText());
                ps.setString(2,nom_pro.getText());
                ps.setString(3,unida_pro.getText());
                ps.setString(4,fecha_pro.getText());
                ps.setString(5,precio_pro.getText());
                ps.setString(6,provee_pro.getText());

                int res = ps.executeUpdate();
                if (res > 0){
                    JOptionPane.showMessageDialog(null,"Actualizacion Completa");

                }else {
                    JOptionPane.showMessageDialog(null,"Error al Actualizar");
                }

                con2.close();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }}
    });



    borrar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });

}
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Tienda().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static Connection getConection() {
        Connection con = null;
        String base = "TIENDA";
        String url = "jdbc:mysql://localhost:/" + base;
        String user = "root";
        String password = "12345";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }
}

