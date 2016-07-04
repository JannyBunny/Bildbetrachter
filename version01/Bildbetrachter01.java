package version01;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Bildbetrachter01 implements ActionListener {

	private Bildflaeche bildFlaeche;
	private JFrame fenster;
	private Farbbild bild;
	private JLabel status;
	
	public Bildbetrachter01() {
		fensterErzeugen();
	}

	private void fensterErzeugen()
	{
		// Top-Level Container erstellen
		fenster = new JFrame("Titel");
		
		// Zugang zur Content-Pane schaffen
		Container contentPane = fenster.getContentPane();
		
		//menü erzeugen
		menueErzeugen(fenster);
		
		//Komponente fuer Bildflaeche erzeugen, Bildflaeche
		bildFlaeche = new Bildflaeche();
		
		// der Content-Pane hinzufügen
		contentPane.add(bildFlaeche);
		
		//Label für Statusinfo erzeugen 
		status = new JLabel("");
		
		// und am unteren Bildrand hinzufuegen
		contentPane.add(status, BorderLayout.SOUTH);

		// Fenster auf definierte Groesse setzen
		fenster.setSize(600, 400);
		
		//alternativ Fenster auf optimale Größe setzen
		//fenster.pack();
		
		//Fenster sichtbar machen
		fenster.setVisible(true);
		
		//Anwendung schließen wenn Fenter geschlossen wird
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void menueErzeugen(JFrame fenster)
	{
		// Menüzeile erzeugen
		JMenuBar menuezeile = new JMenuBar();
		// dem Fenster hinzufügen
		fenster.setJMenuBar(menuezeile);
		
		
		dateimenueErzeugen(menuezeile);
		filtermenueErzeugen(menuezeile);
		
	}
	
	private void dateimenueErzeugen(JMenuBar menuezeile)
	{
		//Menü erzeugen und der Menüzeile hinzufügen
		JMenu dateimenue = new JMenu("Datei");
		menuezeile.add(dateimenue);
		
		//Menüeintrag Öffnen erzeugen
		JMenuItem oeffnen = new JMenuItem("Öffnen");
		dateimenue.add(oeffnen);
		
		// Listener mit Objekt der inneren Klasse
//		OeffnenActionListener oal = new OeffnenActionListener();
//		oeffnen.addActionListener(oal);
		
		// Listener mit anonymem Objekt der inneren Klasse
		oeffnen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				dateiOeffnen();
			}
		});

		//Menüeintrag Beenden erzeugen
		JMenuItem beenden = new JMenuItem("Beenden");
		dateimenue.add(beenden);
		
		// Direkt implementiertes Interface verwenden
		beenden.addActionListener(this);
		
	}
	
  	private void filtermenueErzeugen(JMenuBar menuezeile)
  	{
        // Filter-Menue
        JMenu filterMenue = new JMenu("Filter");
        menuezeile.add(filterMenue);
        
        // Abdunkel-Filter
        JMenuItem dunklerEintrag = new JMenuItem("Abdunkeln...");
		filterMenue.add(dunklerEintrag);
		dunklerEintrag.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				abdunkeln();
			}
		});
		
		// Aufhell-Filter
        JMenuItem hellerEintrag = new JMenuItem("Aufhellen...");
		filterMenue.add(hellerEintrag);
		hellerEintrag.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				aufhellen();
			}
		});
		// Alpha-Filter
        JMenuItem alphaEintrag = new JMenuItem("Alpha...");
		filterMenue.add(alphaEintrag);
		alphaEintrag.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				alphafilter();
			}
		});
		// Rahmen-Filter
        JMenuItem rahmenEintrag = new JMenuItem("Rahmen...");
		filterMenue.add(rahmenEintrag);
		rahmenEintrag.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				zieheRahmen();
			}
		});
	}
	
  	// Innere Klasse fuer Oeffnen-Action-Listener
	private class OeffnenActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			dateiOeffnen();
		}
	}

	public void actionPerformed(ActionEvent e) {
//		String quelle = e.getActionCommand();
//		System.out.println(quelle +" ist bisher noch nicht implementiert!");
		beenden();
	}
	
	private void abdunkeln() {
		if(bild != null) {
			status.setText("");
			bild.dunkler();
			fenster.repaint();
		}
		else {
			status.setText("kein Bild!");
		}		
	}
	private void aufhellen() {
		if(bild != null) {
			status.setText("");
			bild.heller();
			fenster.repaint();
		}
		else {
			status.setText("kein Bild!");
		}		
	}
	private void alphafilter() {
		if(bild != null) {
			status.setText("");
			bild.alphafilter();
			fenster.repaint();
		}
		else {
			status.setText("kein Bild!");
		}		
	}
	private void zieheRahmen() {
		if(bild != null) {
			status.setText("");
			bild.rahmen();
			fenster.repaint();
		}
		else {
			status.setText("kein Bild!");
		}		
	}
	
	/*
	 * Datei Öffnen
	 */
	private void dateiOeffnen()
	{
		bild = BilddateiManager.gibBild();
		bildFlaeche.setzeBild(bild);
		fenster.pack();
		status.setText("Bild geladen");
	}
	
	/*
	 * Anwendung beenden
	 */
	private void beenden()
	{
		System.exit(0);
	}	
	
	public static void main(String[] args) {
		new Bildbetrachter01();
	}
}
