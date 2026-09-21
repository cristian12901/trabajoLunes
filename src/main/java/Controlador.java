import javax.swing.*;
import java.util.ArrayList;

public class Controlador {
    static ArrayList<Estudiante> lista = new ArrayList<>();
    static VistaApp vista = new VistaApp();

    public static void main(String[] args) {
        vista.setVisible(true);

        // --- ACCIÓN PESTAÑA 1: Ingresar datos ---
        vista.btnIngresar.addActionListener(e -> {
            try {
                // Cuadros flotantes para pedir datos
                String cod = JOptionPane.showInputDialog("Código:");
                String nom = JOptionPane.showInputDialog("Nombre:");
                String car = JOptionPane.showInputDialog("Carrera (ej. Tecnologías):");
                double des = Double.parseDouble(JOptionPane.showInputDialog("Nota Desarrollo (0-5):"));
                double def = Double.parseDouble(JOptionPane.showInputDialog("Nota Definitiva (0-5):"));

                // Guardar en el arreglo
                lista.add(new Estudiante(cod, nom, car, des, def));
                JOptionPane.showMessageDialog(null, "¡Estudiante guardado!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: Dato inválido.");
            }
        });

        // --- ACCIÓN PESTAÑA 2 (Punto 2): Filtrar ---
        vista.btnPaso2.addActionListener(e -> {
            double limite = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nota límite (0.0 a 4.9):"));
            vista.modeloTabla.setRowCount(0); // Limpiar tabla

            for (Estudiante est : lista) {
                // Si es de tecnologías y su nota es mayor al límite
                if (est.carrera.toLowerCase().contains("tecnolog") && est.notaDefinitiva > limite) {
                    vista.modeloTabla.addRow(new Object[]{est.codigo, est.nombre, est.carrera, est.notaDesarrollo, est.notaDefinitiva});
                }
            }
        });

        // --- ACCIÓN PESTAÑA 2 (Punto 3): Incrementar ---
        vista.btnPaso3.addActionListener(e -> {
            double cifra = Double.parseDouble(JOptionPane.showInputDialog("Ingrese cifra a sumar (0.0 a 0.5):"));
            
            // Lógica del método void
            for (Estudiante est : lista) {
                double nuevaNota = est.notaDesarrollo + cifra;
                if (nuevaNota > 5.0) {
                    est.notaDesarrollo = 5.0; // Tope máximo
                } else {
                    est.notaDesarrollo = nuevaNota;
                }
            }
            JOptionPane.showMessageDialog(null, "Notas incrementadas. (Máximo 5.0)");
            
            // Refrescar tabla para ver cambios
            vista.modeloTabla.setRowCount(0);
            for (Estudiante est : lista) {
                vista.modeloTabla.addRow(new Object[]{est.codigo, est.nombre, est.carrera, est.notaDesarrollo, est.notaDefinitiva});
            }
        });
    }
}
