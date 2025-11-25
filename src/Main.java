import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    final static String DESARROLLADOR = "Enrique Díaz Valenzuela";
    final static String VERSIONPROGRAMA = "0.7";
    final static String FECHAMODIFICACION = "25 / 11 / 2025";

    private static int tamanyoLetra = 14;
    private static Font tipoFuente = new Font("Arial", Font.PLAIN, tamanyoLetra);

    public static void main(String[] args) {
        JFrame vP = new JFrame();//vP = ventanaPrincipal
        vP.setTitle("Bloc de notas");
        vP.setLayout(new BorderLayout());

        vP.setSize(930, 580);
        vP.setResizable(true);
        vP.setLocationRelativeTo(null);
        vP.getContentPane().setBackground(Color.WHITE);

        //componentes
        JMenuBar mB = new JMenuBar();//mB = menuBar
        JPanel pC = new JPanel();//pS = panelCentral
        JPanel pI = new JPanel();//pS = panelInferior

        //mB
            //Componentes pS
            JMenu mAr = new JMenu("Archivo");//mAr = menuArchivo
            JMenu mFo = new JMenu("Formato");//mFo = menuFormato
            JMenu mVe = new JMenu("Ver");//mVe = menuVer
            JMenu mAy = new JMenu("Ayuda");//mAy = menuAyuda

                //Componentes de cada SUB-MENU
                //mAr
                JMenuItem subAbrir = new JMenuItem("Abrir");
                JMenuItem subGuardar = new JMenuItem("Guardar");
                //JMenuItem subGuardarComo = new JMenuItem("Guardar como");
                JMenuItem subSalir = new JMenuItem("Salir");

                mAr.add(subAbrir);
                mAr.add(subGuardar);
                //mAr.add(subGuardarComo);
                mAr.addSeparator();
                mAr.add(subSalir);

                //mFo
                JCheckBoxMenuItem subAjusteLinea = new JCheckBoxMenuItem("Ajuste de línea");
                JMenuItem subColorFondo = new JMenuItem("Color del fondo");
                JMenuItem subFuente = new JMenuItem("Tamaño de la fuente");             //Tamaño
                JMenuItem subTipografia = new JMenuItem("Tipografía");                  //Calibri
                JMenuItem subColorFuente = new JMenuItem("Color de la fuente");         //Color
                JMenuItem subRestablecerConfiguracion = new JMenuItem("Restablecer configuración");

                mFo.add(subAjusteLinea);
                mFo.add(subColorFondo);
                mFo.addSeparator();
                mFo.add(subFuente);
                mFo.add(subTipografia);
                mFo.add(subColorFuente);
                mFo.addSeparator();
                mFo.add(subRestablecerConfiguracion);

                //mVe
                JMenu mZoom = new JMenu("Zoom");
                JCheckBoxMenuItem subBarraEstado = new JCheckBoxMenuItem("Mostrar barra de estado");

                    //sub Zoom
                    JMenuItem subAcercar = new JMenuItem("Acercar");
                    JMenuItem subAlejar = new JMenuItem("Alejar");
                    JMenuItem subRestablecerZoom = new JMenuItem("Restaurar zoom predeterminado");
                    mZoom.add(subAcercar);
                    mZoom.add(subAlejar);
                    mZoom.add(subRestablecerZoom);

                mVe.add(mZoom);
                mVe.add(subBarraEstado);

                //mAy
                JMenuItem subAcercaDe = new JMenuItem("A cerca de Este Programa");
                mAy.add(subAcercaDe);

        mB.add(mAr);
        mB.add(mFo);
        mB.add(mVe);
        mB.add(mAy);

        //pC
        pC.setLayout(new BorderLayout());

            //Componentes pC
            JTextArea tA = new JTextArea();//tA = textArea
                establecerConfiguracionBase(tA);

            JScrollPane sP = new JScrollPane(tA);//sP = scrollPane

            pC.add(sP, BorderLayout.CENTER);

        //pI
        pI.setLayout(new BorderLayout());
        pI.setPreferredSize(new Dimension(0, 20));

            //Barra de información
            JPanel infoBar = new JPanel();
            infoBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 2));
            infoBar.setOpaque(false);

                //Etiquetas de mi barra de información
                JLabel infoZoom = new JLabel(" 100 % ");
                JLabel infoVersion = new JLabel("v " + VERSIONPROGRAMA);

                infoBar.add(infoZoom);
                infoBar.add(infoVersion);

            pI.add(infoBar, BorderLayout.EAST);

        //añadir componentes a la vP
        vP.setJMenuBar(mB);
        vP.add(pC, BorderLayout.CENTER);
        vP.add(pI, BorderLayout.SOUTH);
        vP.setVisible(true);
        vP.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // prevenimos cierre automático. info sacada de internet
        vP.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int opcion = preguntarGuardar(vP);
                if (opcion == 0) {
                    //guardarArchivo();
                    vP.dispose();
                } else if (opcion == 1) {
                    vP.dispose();
                }
                // 2 = Cancelar → no hacemos nada
            }
        });

        //FUNCIONALIDAD

        //ABRIR
        subAbrir.addActionListener(e -> {
            abrirArchivo(tA);
        });

        //GUARDAR
        subGuardar.addActionListener(e -> {
            guardarArchivo(tA);
        });

        //SALIR
        subSalir.addActionListener(e -> {
            int opcion = preguntarGuardar(vP);
            if (opcion == 0) {//GUARDAR
                guardarArchivo(tA);
                vP.dispose();   // Cierra la ventana
            } else if (opcion == 1) {//CERRAR SIN GUARDAR
                vP.dispose();
            }//Si seleccionan el 2, no hago nada (CANCELAR)
        });

        //COLOR DEL FONDO
        subColorFondo.addActionListener(e -> {
            modificarColorFondo(tA);
        });

        //TAMAÑO DE LA FUENTE
        subFuente.addActionListener(e -> {
            modificarTamanyoLetra(tA);
        });

        //TIPOGRAFIA
        subTipografia.addActionListener(e -> {
            modificarFuenteLetra(tA);
        });

        //COLOR DE LA FUENTE
        subColorFuente.addActionListener(e -> {
            modificarColorLetra(tA);
        });

        //RESTABLECER VALORES
        subRestablecerConfiguracion.addActionListener(e -> {
            establecerConfiguracionBase(tA);
        });

        //A CERCA DE
        subAcercaDe.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "Este programa ha sido desarrollado por: " + DESARROLLADOR + "\n" +
                    "Se encuentra en su versión " + VERSIONPROGRAMA + "\n" +
                    "La última modificación se hizo el " + FECHAMODIFICACION,
                    "A cerca de este Bloc de notas",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    /**
     * Permite guardar cambios antes de salir, salir sin guardar o cancelar.
     * @param padre El componente de la UI que lo llama
     * @return Int de la opción seleccionada
     */
    private static int preguntarGuardar(JFrame padre) {
        // Opciones personalizadas (Esto se vio en PanelesOpciones.java)
        Object[] opciones = {"Guardar", "Cerrar sin guardar", "Cancelar"};

        // Devuelve el índice de la opción elegida
        int seleccion = JOptionPane.showOptionDialog(padre, "Hay cambios sin guardar. ¿Qué deseas hacer?", "Bloc de notas", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);

        return seleccion; // 0 = Guardar, 1 = Cerrar sin guardar, 2 = Cancelar
    }

    private static void abrirArchivo(JTextArea tA) {
        JFileChooser exploradorDeCarpetas = new JFileChooser();
        exploradorDeCarpetas.setDialogTitle("Cargar archivo");

        int eleccionUsuario = exploradorDeCarpetas.showOpenDialog(null); //Muestra ventana dl filechooser para cargar archivos

        if (eleccionUsuario == JFileChooser.APPROVE_OPTION) {//Cuando el usuario elige una opcion valida
            File archivoACargar = exploradorDeCarpetas.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(archivoACargar))) {
                String linea;

                tA.setText("");//Si el textArea tenia contenido debe borrarse antes de escribir nada
                while ((linea = br.readLine()) != null) {
                    tA.append(linea + "\n");
                }
                //Al haber hecho un try con recursos, no necesito cerrar el buffered reader, lo hace el por mi
                //incluso si ocurriese una excepción
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void guardarArchivo(JTextArea tA) {
        JFileChooser exploradorDeCarpetas = new JFileChooser(); //clase encontrada investigando por internet
        exploradorDeCarpetas.setDialogTitle("Guardar archivo");

        int eleccionUsuario = exploradorDeCarpetas.showSaveDialog(null);//Muestra la ventana del file chooser

        if (eleccionUsuario == JFileChooser.APPROVE_OPTION) {//Cuando el usuario elige una opcion valida
            File archivoAGuardar = exploradorDeCarpetas.getSelectedFile();
            try (FileWriter fw = new FileWriter(archivoAGuardar)) {
                fw.write(tA.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void modificarColorFondo(Component padre) {
        Color colorFondo = JColorChooser.showDialog(padre, "Fondo", Color.BLACK);
        padre.setBackground(colorFondo);
    }

    private static void modificarColorLetra(Component padre) {
        Color colorFondo = JColorChooser.showDialog(padre, "Fondo", Color.BLACK);
        padre.setForeground(colorFondo);
    }

    private static void modificarTamanyoLetra(Component padre) {
        try {
            tamanyoLetra = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el tamaño de letra que desea (Tamaño actual = "+ tamanyoLetra +"):"));
            padre.setFont(new Font("Arial", Font.PLAIN, tamanyoLetra));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, introduce números enteros.", "Bloc de notas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void modificarFuenteLetra(Component padre) {
        String[] fuentes = { "Consolas", "Arial", "Impact" };//Fuentes que ofrece mi programa

        JComboBox<String> combo = new JComboBox<>(fuentes);
        combo.setSelectedItem(padre.getFont().getFamily());

        JPanel panel = new JPanel();
        panel.add(new JLabel("Fuente:"));
        panel.add(combo);

        int opcion = JOptionPane.showConfirmDialog(padre, panel,"Seleccionar fuente", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {
            String fuenteSeleccionada = combo.getSelectedItem().toString();
            Font nuevaFuente = new Font(fuenteSeleccionada, tipoFuente.getStyle(), tipoFuente.getSize());
            padre.setFont(nuevaFuente);
        }
    }

    private static void establecerConfiguracionBase(Component padre) {
        padre.setFont(new Font("Arial", Font.PLAIN, 14));
        padre.setForeground(Color.BLACK);
        padre.setBackground(Color.WHITE);
    }
}