/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myavltreegui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.io.File;
/**
 *
 * @author LIVERPOOL I5
 */
public class MyAVLtreeGUI extends JFrame implements ActionListener {

    int FRAME_WIDTH    = 400;

    /**
     * Default frame height
     */
    int FRAME_HEIGHT   = 400;

    /**
     * X coordinate of the frame default origin point
     */
     int FRAME_X_ORIGIN = 150;

    /**
     * Y coordinate of the frame default origin point
     */
     int FRAME_Y_ORIGIN = 250;
     
     JMenu options;
     
     JTextArea textArea;
     
     AVLtree avl;
     Node nodeobj;
     String opcionmenu;
     
     int dato;
     
     //int i;
    public static void main(String[] args) {
          MyAVLtreeGUI frame = new MyAVLtreeGUI();
        frame.setVisible(true);
    }
        public MyAVLtreeGUI (){
        Container contentPane; 
        
        setSize      (FRAME_WIDTH, FRAME_HEIGHT);
        setResizable (false);
        setTitle     ("Mi Arreglo");
        setLocation  (FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
        
       avl = new AVLtree(); //Se crea el objeto de la clase MiArreglo
       nodeobj = new Node();
        JMenuItem item; 
           
        options = new JMenu("Actions"); 
        
        item = new JMenuItem("ADD");
        item.addActionListener(this);
        options.add(item);
        
        options.addSeparator();
        
        item = new JMenuItem("Delete");
        item.addActionListener(this);
        options.add(item);
        
        options.addSeparator();
        
        item = new JMenuItem("Insert File");
        item.addActionListener(this);
        options.add(item);
        
        options.addSeparator();
        
        item = new JMenuItem ("Show Tree");
        item.addActionListener(this);
        options.add(item);
        
         options.addSeparator();
        
        item = new JMenuItem ("Search");
        item.addActionListener(this);
        options.add(item);
        
        options.addSeparator();
        
        item = new JMenuItem ("Exit");
        item.addActionListener(this);
        options.add(item);
        
        JMenuBar menuBar = new JMenuBar();//Se crea el area la bara para el menu
        setJMenuBar(menuBar);
        menuBar.add(options);
      

        contentPane = getContentPane( );
        contentPane.setLayout( null );
        contentPane.setBackground( new Color(22, 26, 35));
        
         textArea = new JTextArea(); //Se crea el area para escribir
        JScrollPane scrollText= new JScrollPane(textArea);
        scrollText.setBounds(1, 1, 392, 340);

        textArea.setEditable(false);
        scrollText.setBorder(BorderFactory.createLineBorder(Color.blue));
        contentPane.add(scrollText);
        textArea.setFont(new Font("Serif", Font.PLAIN, 30));
        
        setDefaultCloseOperation( EXIT_ON_CLOSE ); //Se cierra el programa
        
    }
    
    public void actionPerformed (ActionEvent e){
        String clickedbutton = e.getActionCommand();
        opcionmenu = clickedbutton;
        metodos();
    }
     public void metodos(){ //Switch case para cada caso de uso, para ser mostrado en el menu y el usuario pueda interactuar con estas opciones.
             switch(opcionmenu){
            case ("ADD"): 
               String input =  JOptionPane.showInputDialog("Escribe dato que se quiere añadir:");
               avl.data = Integer.parseInt(input);
               avl.node = avl.root;
                avl.add(avl.data,avl.node,avl.root);
                //System.out.println(avl.cont);
            break;
            case ("Delete"): 
                String input3 =  JOptionPane.showInputDialog("Escribe el dato que quieres eliminar ");
                 int numero = Integer.parseInt(input3);
                avl.delete(numero);
            break;
            case ("Insert File"): 
                avl.loadFile();
    
            break;
             case ("Search"): 
                 String input2 =  JOptionPane.showInputDialog("Escribe el dato que quieres saber si se encuentra en el arbol: ");
                 int valor = Integer.parseInt(input2);
                 Node hola = avl.search(valor);
                 if(hola == null){
                     JOptionPane.showMessageDialog(null,"No se encontro valor");
                 }
                 else{
                      JOptionPane.showMessageDialog(null,"Si se encontro valor");
                 }
    
            break;
            case ("Show Tree"):
               // String z = avl.print_tree(avl.root,avl.cont);
                textArea.setText(avl.print_tree(avl.root,avl.cont));
            break;
            case ("Exit"): System.exit(0);
            break;
            
        }
    }
}
