import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    final static String DESARROLLADOR = "Enrique Díaz Valenzuela";
    final static String VERSIONPROGRAMA = "1.2";
    final static String FECHAMODIFICACION = "25 / 11 / 2025";

    private static int tamanyoLetra = 14;
    private static Font tipoFuente = new Font("Arial", Font.PLAIN, tamanyoLetra);

    //variable aux para comprobar si hay cambios
    private static String contenidoOriginal = "";


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
                JMenuItem subSalir = new JMenuItem("Salir");

                mAr.add(subAbrir);
                mAr.add(subGuardar);
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
                JCheckBoxMenuItem subBarraEstado = new JCheckBoxMenuItem("Mostrar barra de estado");
                subBarraEstado.setSelected(true);
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
                JLabel infoVersion = new JLabel("v " + VERSIONPROGRAMA);
                infoBar.add(infoVersion);

            pI.add(infoBar, BorderLayout.EAST);

        // TOOLBAR
        JToolBar barraHerramientas = new JToolBar();

        JButton bCopiar = new JButton("Copiar");
        barraHerramientas.add(bCopiar);

        JButton bCopiarTodo = new JButton("Copiar todo");
        barraHerramientas.add(bCopiarTodo);

        JButton bPegar = new JButton("Pegar");
        barraHerramientas.add(bPegar);

        pI.add(barraHerramientas, BorderLayout.WEST);


        // MENU CONTEXTUAL
        JPopupMenu menuContextual = new JPopupMenu();

        JMenuItem mContextCopiar = new JMenuItem("Copiar");
        menuContextual.add(mContextCopiar);

        JMenuItem mContextPegar = new JMenuItem("Pegar");
        menuContextual.add(mContextPegar);

        JMenuItem mContextCortar = new JMenuItem("Cortar");
        menuContextual.add(mContextCortar);

        tA.setComponentPopupMenu(menuContextual);

        //COPIAR
        bCopiar.addActionListener(e -> tA.copy());

        //COPIAR TODITO
        bCopiarTodo.addActionListener(e -> {
            tA.selectAll();
            tA.copy();
            tA.setCaretPosition(0); // opcional: deselecciona todito después de copiar
        });

        //PEGAR
        bPegar.addActionListener(e -> tA.paste());

        mContextCopiar.addActionListener(e -> tA.copy());
        mContextPegar.addActionListener(e -> tA.paste());
        mContextCortar.addActionListener(e -> tA.cut());

        //añadir componentes a la vP
        vP.setJMenuBar(mB);
        vP.add(pC, BorderLayout.CENTER);
        vP.add(pI, BorderLayout.SOUTH);
        vP.setVisible(true);
        vP.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // prevenimos cierre automático. info sacada de internet
        vP.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                guardarYSalir(tA, vP);
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
            guardarYSalir(tA, vP);
        });

        //AJUSTE DE LINEA
        subAjusteLinea.addActionListener(e -> {
            boolean activado = subAjusteLinea.isSelected();
            tA.setLineWrap(activado);
            tA.setWrapStyleWord(activado);
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

        //BARRA DE ESTADO
        subBarraEstado.addActionListener(e -> {
            pI.setVisible(subBarraEstado.isSelected());
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

    private static boolean hayCambiosSinGuardar(JTextArea tA) {
        if (tA.getText().equals(contenidoOriginal)) {
            return false; //No hay cambios
        } else {
            return true;  //Si hay cambios
        }
    }

    private static void guardarYSalir(JTextArea tA, JFrame ventana) {
        if (hayCambiosSinGuardar(tA)) {//si hay cambios por guardar
            int opcion = preguntarGuardar(ventana);
            if (opcion == 0) {//GUARDAR
                guardarArchivo(tA);
                ventana.dispose();   // Cierra la ventana
            } else if (opcion == 1) {//CERRAR SIN GUARDAR
                ventana.dispose();
            }//Si seleccionan el 2, no hago nada (CANCELAR)
        } else {//si no se han echo cambios permito cerrar el programa del tirón
            ventana.dispose();
        }
    }

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
                contenidoOriginal = tA.getText();
                //Al haber hecho un try con recursos, no necesito cerrar el buffered reader, lo hace el por mi
                //incluso si ocurriese una excepción
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Bloc de notas", JOptionPane.ERROR_MESSAGE);
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
                contenidoOriginal = tA.getText();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Bloc de notas", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void modificarColorFondo(Component padre) {
        Color colorFondo = JColorChooser.showDialog(padre, "Fondo", padre.getBackground());
        padre.setBackground(colorFondo);
    }

    private static void modificarColorLetra(Component padre) {
        Color colorFondo = JColorChooser.showDialog(padre, "Fondo", padre.getForeground());
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