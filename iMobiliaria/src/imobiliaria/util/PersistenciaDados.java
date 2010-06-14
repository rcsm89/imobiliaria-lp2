package imobiliaria.util;

import imobiliaria.processamento.Sistema;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class PersistenciaDados {
	
	public static void gravar(Object objeto, String arquivo) {
	    	
		try {
		   	FileOutputStream fos = new FileOutputStream(arquivo);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    
		    oos.writeObject(objeto);
		
		    oos.close();
		} catch (Exception e) {
			System.out.println("Erro de Arquivo: " + e.getMessage());
			
		}
	}
	
	public static Object ler(String arquivo) throws Exception {
		
		
		FileInputStream fis;
		
		try {
			
			fis = new FileInputStream(arquivo);
			
		} catch (FileNotFoundException e) {
			
			if (arquivo.equals("DadosDeSistema.dat")) {
				Sistema sis = new Sistema();
				
				gravar(sis, "DadosDeSistema.dat");
			} else if (arquivo.equals("Registros.dat")) {
				
				gravar(0, "Registros.dat");
				
			} else {
				
				throw new FileNotFoundException("Arquivo nao encontrado!");
				
			}
		    
		    fis = new FileInputStream(arquivo);
			
		}
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		
		return ois.readObject();
	}
}