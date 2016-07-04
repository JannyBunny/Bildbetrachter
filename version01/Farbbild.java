package version01;
import java.awt.*;
import java.awt.image.*;

/**
 * Farbbild ist eine Klasse, die Farbbilder mit einer bequemen
 * Schnittstelle definiert.
 * 
 * @author  Michael Kölling und David J. Barnes
 * @version 1.0
 */
public class Farbbild extends BufferedImage
{
    /**
     * Erzeuge ein Farbbild als Kopie von einem BufferedImage.
     * @param image das zu kopierende BufferedImage.
     */
    public Farbbild(BufferedImage image)
    {
         super(image.getColorModel(), image.copyData(null), 
               image.isAlphaPremultiplied(), null);
    }

    /**
     * Erzeuge ein Farbbild mit der angegebenen Größe mit
     * undefiniertem Inhalt.
     * @param breite die Breite des Bildes.
     * @param hoehe die Hoehe des Bildes.
     */
    public Farbbild(int breite, int hoehe)
    {
        super(breite, hoehe, TYPE_INT_RGB);
    }

    /**
     * Setze den angegebenen Bildpunkt dieses Bildes auf die
     * angegebene Farbe.
     * @param x die x-Koordinate des Bildpunktes.
     * @param y die y-Koordinate des Bildpunktes.
     * @param col die Farbe des Bildpunktes.
     */
    public void setzePunktfarbe(int x, int y, Color col)
    {
        int punktfarbe = col.getRGB();
        setRGB(x, y, punktfarbe);
    }
    
    /**
     * Liefere die Farbe des angegebenen Bildpunktes.
     * @param x die x-Koordinate des Bildpunktes.
     * @param y die y-Koordinate des Bildpunktes.
     * @return die Farbe des Bildpunktes an der angegebenen Position.
     */
    public Color gibPunktfarbe(int x, int y)
    {
        int punktfarbe = getRGB(x, y);
        return new Color(punktfarbe);
    }
    
    /**
     * Abdunkelfiler, dunkelt das vorhandene Bild um eine Stufe ab.
     */
    public void dunkler()
    {
    	int breite = getWidth();
    	int hoehe = getHeight();
    	for(int i = 0; i < breite; i++){
    		for(int j = 0; j < hoehe; j++){
    			Color pixel = gibPunktfarbe(i,j);
    			pixel = pixel.darker();
    			setzePunktfarbe(i, j, pixel);
    		}
    	}
    }
    public void heller()
    {
    	int breite = getWidth();
    	int hoehe = getHeight();
    	for(int i = 0; i < breite; i++){
    		for(int j = 0; j < hoehe; j++){
    			Color pixel = gibPunktfarbe(i,j);
    			pixel = pixel.brighter();
    			setzePunktfarbe(i, j, pixel);
    		}
    	}
    }
    public void alphafilter()
    {
    	int breite = getWidth();
    	int hoehe = getHeight();
    	for(int i = 0; i < breite; i++){
    		for(int j = 0; j < hoehe; j++){
    			
    			Color pixel = gibPunktfarbe(i,j);
    			
    			int rPixel=pixel.getRed();
    			int bPixel=pixel.getBlue();
    			int gPixel=pixel.getGreen();
    			int aPixel=pixel.getAlpha();
    			
    			
    			if(rPixel>=200 && rPixel<=255) {
    				rPixel-=111;
    			}
    			
    			if(gPixel>=100 && gPixel <= 255) {
    				gPixel-=10;
    			}
    			
    			if (bPixel>=50 && bPixel <= 255) {
    				bPixel-=30;
    			}
    			
    			if (aPixel>=100 && aPixel<=255) {
    				aPixel-=10;
    			}
    			
    			Color pxl = new Color(rPixel,gPixel,bPixel,aPixel);
    			if ((i%10==0 && j%10==0)||(i%3==0 && j%3==0)||(i%2==0 && j%2==0) ) {
    				pxl = new Color(0,0,0,255);
    			}
    			
    			setzePunktfarbe(i, j, pxl);
    			
    		}
    	}
    }
    public void rahmen()
    {
    	int breite = getWidth();
    	int hoehe = getHeight();
    	int r=230;
    	int g=230;
    	int b=230;
    	int a=255;
    	int rahmenbreite=10;
    	if (rahmenbreite >= breite || rahmenbreite >= hoehe) {
    		rahmenbreite=rahmenbreite/100;
    	}
    	Color pixel = new Color(r,g,b,a);
    	for(int i = 0; i < breite; i++){
    		for(int j = 0; j < hoehe; j++){
    			if ((i<=rahmenbreite||j<=rahmenbreite)||(i>=breite-rahmenbreite||j>=hoehe-rahmenbreite)) {
    				setzePunktfarbe(i, j, pixel);
    			}
    			
    		}
    	}
    }
}
