package imobiliaria.processamento;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class PersistenciaDados {
	
	public static void gravar(Sistema sis) {
	    	
		try {
		   	FileOutputStream fos = new FileOutputStream("DadosDoSistema.dat");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    
		    oos.writeObject(sis);
		
		    oos.close();
		} catch (Exception e) {
			System.out.println("Erro de Arquivo: " + e.getMessage());
			
		}
	}
	
	public static Sistema ler() throws Exception {
		
		
		FileInputStream fis;
		
		try {
			
			fis = new FileInputStream("DadosDoSistema.dat");
			
		} catch (FileNotFoundException e) {
			
			Sistema sis = new Sistema();
			
			gravar(sis);
		    
		    fis = new FileInputStream("DadosDoSistema.dat");
			
		}
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		
		return (Sistema) ois.readObject();
	}
}