package imobiliaria.testes;

import imobiliaria.auxiliar.TipoArea;
import imobiliaria.entidades.Area;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class AreaTest {

    private Area area1;
    private Area area2;
    private Area area3;

    @Before
    public void criaAreas() throws Exception {
	area1 = new Area(2, 4); // Pequeno
	area2 = new Area(8, 7); // Medio
	area3 = new Area(25, 30); // Grande
    }

    @Test
    public void testaExcecoes() {
	try {
	    area1 = new Area(0, 5);
	    Assert.fail("Deveria ter lancado excessao aqui");
	} catch (Exception e) {
	    Assert.assertEquals("Comprimento invalido\n", e.getMessage());
	}
	try {
	    area1 = new Area(10, -1);
	    Assert.fail("Deveria ter lancado excessao aqui");
	} catch (Exception e) {
	    Assert.assertEquals("Largura invalida\n", e.getMessage());
	}
	try {
	    area1 = new Area(-10, 0);
	    Assert.fail("Deveria ter lancado excessao aqui");
	} catch (Exception e) {
	    Assert.assertEquals(
		    "Comprimento invalido\n" + "Largura invalida\n", e
			    .getMessage());
	}
    }

    @Test
    public void testaModificadores() throws Exception {

	// Modificadores

	try {
	    area1.setComprimento(25.0);
	} catch (Exception erro) {
	    Assert.fail("Nao Deveria ter lancado excessao aqui");
	}
	/*
	 * MUDOU DE PEQUENO PARA MEDIO
	 */
	Assert.assertEquals(25.0, area1.getComprimento());
	Assert.assertEquals(TipoArea.MEDIA, area1.getClassificacao());

	try {
	    area2.setLargura(50.0);
	} catch (Exception erro) {
	    Assert.fail("Nao Deveria ter lancado excessao aqui");
	}
	/*
	 * MUDOU DE MEDIO PARA GRANDE
	 */
	Assert.assertEquals(50.0, area2.getLargura());
	Assert.assertEquals(TipoArea.GRANDE, area2.getClassificacao());

	try {
	    area3.setComprimento(1.0);
	    area3.setLargura(3.0);
	} catch (Exception erro) {
	    Assert.fail("Nao Deveria ter lancado excessao aqui");
	}
	/*
	 * MUDOU DE GRANDE PARA PEQUENDO
	 */
	Assert.assertEquals(1.0, area3.getComprimento());
	Assert.assertEquals(3.0, area3.getLargura());
	Assert.assertEquals(TipoArea.PEQUENA, area3.getClassificacao());

	// Excecoes nos Modificadores
	try {
	    area1.setComprimento(-5);
	    Assert.fail("Deveria ter lancado excessao aqui");
	} catch (Exception e) {
	    Assert.assertEquals("Comprimento invalido", e.getMessage());
	}

	try {
	    area2.setLargura(0);
	    Assert.fail("Deveria ter lancado excessao aqui");
	} catch (Exception e) {
	    Assert.assertEquals("Largura invalida", e.getMessage());
	}

    }

    @Test
    public void testaToString() {

	Assert.assertEquals("2.0x4.0 (PEQUENA)", area1.toString());
	Assert.assertEquals("8.0x7.0 (MEDIA)", area2.toString());
	Assert.assertEquals("25.0x30.0 (GRANDE)", area3.toString());
    }

    @Test
    public void testaAcessadores() {

	Assert.assertEquals(2.0, area1.getComprimento());
	Assert.assertEquals(4.0, area1.getLargura());
	Assert.assertEquals(TipoArea.PEQUENA, area1.getClassificacao());

	Assert.assertEquals(8.0, area2.getComprimento());
	Assert.assertEquals(7.0, area2.getLargura());
	Assert.assertEquals(TipoArea.MEDIA, area2.getClassificacao());

	Assert.assertEquals(25.0, area3.getComprimento());
	Assert.assertEquals(30.0, area3.getLargura());
	Assert.assertEquals(TipoArea.GRANDE, area3.getClassificacao());
    }
}
