package imobiliaria.testes;

import imobiliaria.processamento.Area;
import imobiliaria.processamento.TipoArea;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class AreaTest {
	
	private Area area1;
	private Area area2;
	private Area area3;
	
	@Before
	public void criaAreas() throws Exception {
		area1 = new Area(2, 4);  // Pequeno
		area2 = new Area(8, 7);  // Medio
		area3 = new Area(25,30); // Grande
	}
	
	@Test
	public void testaExcecoes() {
		try {
			area1 = new Area(0, 5);
		} catch (Exception e) {
			Assert.assertEquals("Comprimento invalido\n", e.getMessage());
		}
		try {
			area1 = new Area(10, -1);
		} catch (Exception e) {
			Assert.assertEquals("Largura invalida\n", e.getMessage());
		}
		
		try {
			area1 = new Area(-10, 0);
		} catch (Exception e) {
			Assert.assertEquals("Comprimento invalido\n" +
					"Largura invalida\n", e.getMessage());
		}
	}
	
	@Test
	public void testaAreas() throws Exception {
		
		// toStrings
		
		Assert.assertEquals("2.0x4.0 (PEQUENA)", area1.toString());
		Assert.assertEquals("8.0x7.0 (MEDIA)", area2.toString());
		Assert.assertEquals("25.0x30.0 (GRANDE)", area3.toString());
		
		
		// Acessadores
		
		Assert.assertEquals(2.0, area1.getComprimento());
		Assert.assertEquals(4.0, area1.getLargura());
		Assert.assertEquals(TipoArea.PEQUENA, area1.getClassificacao());
		
		Assert.assertEquals(8.0, area2.getComprimento());
		Assert.assertEquals(7.0, area2.getLargura());
		Assert.assertEquals(TipoArea.MEDIA, area2.getClassificacao());
		
		Assert.assertEquals(25.0, area3.getComprimento());
		Assert.assertEquals(30.0, area3.getLargura());
		Assert.assertEquals(TipoArea.GRANDE, area3.getClassificacao());
		
		// Modificadores
		
		
		area1.setComprimento(25.0);
		
		Assert.assertEquals(25.0, area1.getComprimento()); 	// Mudou de Pequeno para
		Assert.assertEquals(TipoArea.MEDIA, area1.getClassificacao()); // Medio
		
		area2.setLargura(50.0);
		
		Assert.assertEquals(50.0, area2.getLargura()); 		// Mudou de Medio para
		Assert.assertEquals(TipoArea.GRANDE, area2.getClassificacao()); // Grande
		
		area3.setComprimento(1.0);
		area3.setLargura(3.0);
		
		Assert.assertEquals(1.0, area3.getComprimento()); // Mudou de Grande para
		Assert.assertEquals(3.0, area3.getLargura()); 		// Pequeno
		Assert.assertEquals(TipoArea.PEQUENA, area3.getClassificacao());
		
		
		// Excecoes nos Modificadores
		
		try {
			area1.setComprimento(-5);
		} catch (Exception e) {
			Assert.assertEquals("Comprimento invalido", e.getMessage());
		}
		
		try {
			area2.setLargura(0);
		} catch (Exception e) {
			Assert.assertEquals("Largura invalida", e.getMessage());
		}
		
	}
}
