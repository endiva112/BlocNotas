import javax.swing.*;
import java.awt.*;

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
                JMenuItem subGuardarComo = new JMenuItem("Guardar como");
                JMenuItem subSalir = new JMenuItem("Salir");

                mAr.add(subAbrir);
                mAr.add(subGuardar);
                mAr.add(subGuardarComo);
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
        vP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}