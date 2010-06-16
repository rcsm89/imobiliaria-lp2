package imobiliaria.util;

import imobiliaria.processamento.Sistema;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


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
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Object> ler(String arquivo) throws IOException, ClassNotFoundException {
		
		
		FileInputStream fis;
		
		try {
			
			fis = new FileInputStream(arquivo);
			
		} catch (FileNotFoundException e) {
			
			if (arquivo == "DadosDeSistema.dat") {
				(new Sistema()).salvarDados();
			} else {
			
				throw new FileNotFoundException("Arquivo nao encontrado!");
				
			}
		}
		    
		fis = new FileInputStream(arquivo);
			
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		ArrayList<Object> objetos = (ArrayList<Object>) ois.readObject();
		
		return objetos;
	}
}