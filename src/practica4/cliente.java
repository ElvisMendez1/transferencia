package practica4;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

class cliente {

    public static void main(String[] args) {
        DataInputStream input;
        BufferedInputStream bis;
        BufferedOutputStream bos;
        int in;
        byte[] byteArray;
        //Fichero a transferir
        //System.out.print("Ingrese la direccion del archivo a descargar:");

        final String filename = JOptionPane.showInputDialog("Ingrese la direccion del archivo a descargar:");
        //final String filename = "//home/genesis/Descargas/archivo.deb";

        try {
            final File localFile = new File(filename);
            Socket client = new Socket("localhost", 5000);
            bis = new BufferedInputStream(new FileInputStream(localFile));
            bos = new BufferedOutputStream(client.getOutputStream());
            //Enviamos el nombre del fichero
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(localFile.getName());
            //Enviamos el fichero
            byteArray = new byte[8192];
            while ((in = bis.read(byteArray)) != -1) {
                bos.write(byteArray, 0, in);
            }

            bis.close();
            bos.close();
                JOptionPane.showMessageDialog(null,"Archivo descargado");

        } catch (Exception e) {
            System.err.println(e);
                JOptionPane.showMessageDialog(null,"No se encontro la ruta o el archivo");
        }
    }
}
