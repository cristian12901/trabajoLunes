import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaApp extends JFrame {
    public JTabbedPane pestañas;
    public JButton btnIngresar, btnPaso2, btnPaso3;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public VistaApp() {
        setTitle("Ejercicio Modificado - Swing");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pestañas = new JTabbedPane();

        // --- PESTAÑA 1: Ingreso ---
        JPanel panel1 = new JPanel();
        btnIngresar = new JButton("Ingresar Estudiante");
        panel1.add(btnIngresar);
        pestañas.addTab("1. Ingreso", panel1);

        // --- PESTAÑA 2: Reporte y Modificación ---
        JPanel panel2 = new JPanel(new BorderLayout());
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Carrera", "Desarrollo", "Definitiva"}, 0);
        tabla = new JTable(modeloTabla);
        panel2.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnPaso2 = new JButton("Paso 2: Filtrar Tecnologías");
        btnPaso3 = new JButton("Paso 3: Incrementar Nota Desarrollo");
        panelBotones.add(btnPaso2);
        panelBotones.add(btnPaso3);
        panel2.add(panelBotones, BorderLayout.SOUTH);

        pestañas.addTab("2. Reporte", panel2);

        add(pestañas);
    }
}
