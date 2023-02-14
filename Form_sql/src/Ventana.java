import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Ventana {
    PreparedStatement ps;
    private JTextField textcell;
    private JTextField apel_cli;
    private JTextField nom_cli;
    private JTextField id_cli;
    private JPanel panel;
    private JButton principal;
    private JTextField correo;

    public Ventana(){

        principal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Connection con;
               try{
                   con = getConection();

                   ps = con.prepareStatement("INSERT INTO CLIENTE(ID_Cli, Nom_Cli, Apel_Cli,Celu_Cli,Correo_Cli) VALUES(?,?,?,?,?)");

                   ps.setString(1,id_cli.getText());
                   ps.setString(2,nom_cli.getText());
                   ps.setString(3,apel_cli.getText());
                   ps.setString(4,textcell.getText());
                   ps.setString(5,correo.getText());

                   System.out.println(ps);
                   int res = ps.executeUpdate();

                   if (res > 0){
                       JOptionPane.showMessageDialog(null,"Persona Guardada");
                   }else {
                       JOptionPane.showMessageDialog(null, "Error al Guardar persona");
                   }

               }catch (HeadlessException | SQLException f){
                   System.err.println(f);
               }

            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static Connection getConection() {
        Connection con = null;
        String base = "DATOS_INF"; //Nombre de la base de datos
        String url = "jdbc:mysql://localhost:/" + base; //Direccion, puerto y nombre de la Base de Datos
        String user = "root"; //Usuario de Acceso a MySQL
        String password = "12345"; //Password del usuario

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }



}
