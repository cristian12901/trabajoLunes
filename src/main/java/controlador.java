import javax.swing.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class controlador {

    static ArrayList<Estudiante> lista = new ArrayList<>();
    static VistaApp vista = new VistaApp();

    public static void main(String[] args) {
        vista.setVisible(true);


        vista.btnIngresar.addActionListener(e -> {
            try {
                String cod = JOptionPane.showInputDialog("Código:");
                String nom = JOptionPane.showInputDialog("Nombre:");
                String car = JOptionPane.showInputDialog("Carrera (ej. Tecnologías):");
                double des = Double.parseDouble(JOptionPane.showInputDialog("Nota Desarrollo (0-5):"));
                double def = Double.parseDouble(JOptionPane.showInputDialog("Nota Definitiva (0-5):"));


                lista.add(new Estudiante(cod, nom, car, des, def));
                JOptionPane.showMessageDialog(null, "Estudiante guardado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: Datos inválidos.");
            }
        });


        vista.btnPaso2.addActionListener(e -> {
            try {
                double limite = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nota límite (0.0 a 4.9):"));
                if (limite < 0.0 || limite > 4.9) {
                    JOptionPane.showMessageDialog(null, "La nota debe estar entre 0.0 y 4.9");
                    return;
                }

                listarTecnologiaSuperior(vista.modeloTabla, lista, limite);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }
        });


        vista.btnPaso3.addActionListener(e -> {
            try {
                double cifra = Double.parseDouble(JOptionPane.showInputDialog("Ingrese cifra a incrementar (0.0 a 0.5):"));
                if (cifra < 0.0 || cifra > 0.5) {
                    JOptionPane.showMessageDialog(null, "La cifra debe estar entre 0.0 y 0.5");
                    return;
                }
                
                incrementarNotaDesarrollo(lista, cifra);
                JOptionPane.showMessageDialog(null, "Notas incrementadas (máximo 5.0).");
                
                
                cargarTabla(vista.modeloTabla, lista);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }
        });
    }

    public static void listarTecnologiaSuperior(DefaultTableModel tabla, ArrayList<Estudiante> estudiantes, double limite) {
        tabla.setRowCount(0);
        boolean hayDatos = false;
        
        for (Estudiante est : estudiantes) {

            if (est.carrera.toLowerCase().contains("tecnolog") && est.notaDefinitiva > limite) {
                tabla.addRow(new Object[]{est.codigo, est.nombre, est.carrera, est.notaDesarrollo, est.notaDefinitiva});
                hayDatos = true;
            }
        }
        
        if (!hayDatos) {
            JOptionPane.showMessageDialog(null, "No hay estudiantes de Tecnologías con nota superior a " + limite);
        }
    }

    public static void incrementarNotaDesarrollo(ArrayList<Estudiante> estudiantes, double cifra) {
        for (Estudiante est : estudiantes) {
            double nuevaNota = est.notaDesarrollo + cifra;

            if (nuevaNota > 5.0) {
                est.notaDesarrollo = 5.0;
            } else {
                est.notaDesarrollo = nuevaNota;
            }
        }
    }


    public static void cargarTabla(DefaultTableModel tabla, ArrayList<Estudiante> estudiantes) {
        tabla.setRowCount(0);
        for (Estudiante est : estudiantes) {
            tabla.addRow(new Object[]{est.codigo, est.nombre, est.carrera, est.notaDesarrollo, est.notaDefinitiva});
        }
    }
}
