import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
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
                JMenuItem subFuente = new JMenuItem("Fuente...");                   //Tamaño
                JMenuItem subFormatos = new JMenuItem("Formatos");                  //Estilos como negrita
                JMenuItem subTipografia = new JMenuItem("Tipografía");              //Calibri
                JMenuItem subColorFormato = new JMenuItem("Color de la fuente");    //Color

                mFo.add(subAjusteLinea);
                mFo.addSeparator();
                mFo.add(subFuente);
                mFo.addSeparator();
                mFo.add(subTipografia);
                mFo.add(subFormatos);
                mFo.add(subColorFormato);

                //mVe
                JMenu mZoom = new JMenu("Zoom");
                JCheckBoxMenuItem subBarraEstado = new JCheckBoxMenuItem("Mostrar barra de estado");

                    //sub Zoom
                    JMenuItem subAcercar = new JMenuItem("Acercar");
                    JMenuItem subAlejar = new JMenuItem("Alejar");
                    JMenuItem subRestablecer = new JMenuItem("Restaurar zoom predeterminado");
                    mZoom.add(subAcercar);
                    mZoom.add(subAlejar);
                    mZoom.add(subRestablecer);

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
                JLabel infoVersion = new JLabel("v 0.5");

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
        //GUARDAR
        subGuardar.addActionListener(e -> {
            guardarArchivo(tA);
            /*
            JFileChooser selectorCarpetas = new JFileChooser(); //Clase FileChooser, super útil, mejor que hacerlo a mano
            selectorCarpetas.setDialogTitle("Guardar archivo");

            int eleccionUsuario = selectorCarpetas.showSaveDialog(vP); // vP es tu ventana principal

            if (eleccionUsuario == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = selectorCarpetas.getSelectedFile();
                try (java.io.FileWriter fw = new java.io.FileWriter(fileToSave)) {
                    tA.write(fw);
                } catch (java.io.IOException ex) {
                    ex.printStackTrace();
                }
            }*/
        });

        //SALIR
        subSalir.addActionListener(e -> {
            int opcion = preguntarGuardar(vP);
            if (opcion == 0) {//GUARDAR
                guardarArchivo(tA);
                vP.dispose();     // Cierra la ventana
            } else if (opcion == 1) {
                vP.dispose();
            }//Si seleccionan el 2, no hago nada (Cancelar)
        });
    }

    /**
     * Este método me permite guardar cambios antes de salir, salir sin guardar o cancelar.
     * @param padre El componente de la UI que lo llama
     * @return Int de la opción seleccionada
     */
    private static int preguntarGuardar(JFrame padre) {
        // Opciones personalizadas
        // Esto se vio en PanelesOpciones.java
        Object[] opciones = {"Guardar", "Cerrar sin guardar", "Cancelar"};

        // Devuelve el índice de la opción elegida
        int seleccion = JOptionPane.showOptionDialog(padre, "Hay cambios sin guardar. ¿Qué deseas hacer?", "Bloc de notas", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);

        return seleccion; // 0 = Guardar, 1 = Cerrar sin guardar, 2 = Cancelar
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
}