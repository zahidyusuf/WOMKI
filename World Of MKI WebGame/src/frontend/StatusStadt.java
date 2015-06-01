package frontend;

import interfaces.iBackend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import daten.d;
import daten.dSpieler;
import daten.dStadt;

public class StatusStadt extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private FrontendBean fr;
	private dStadt[] dSt;
	private dSpieler dSp;
	private JButton abbrechen=new JButton("Abbrechen");
	public JButton[] buttons=new JButton[10];
	private JPanel jp=new JPanel();
	
	public StatusStadt(FrontendBean fr,dStadt[] dSt,int[] pos){
		super();
		this.fr=fr;
		this.dSt=dSt;
		this.dSp=fr.getBackend().getSpielerdaten(d.toInt(dSt[0].get("idSpieler")));
		
		JPanel jpButtons=new JPanel();
		setLayout(new BorderLayout());
		jp.setLayout(new GridBagLayout());
		jpButtons.setLayout(new GridLayout(buttons.length,1));
		
		for (int i=0;i<buttons.length;i++){
			buttons[i]=new JButton("");
			buttons[i].addActionListener(this);
			jpButtons.add(buttons[i]);
		}
		
		buttons[0].setText("Produktion");
		
		

		buttons[9].setText("Aufloesen");
		
		setTitle("Status der Stadt");
		abbrechen.addActionListener(this);

		setDaten();
		JLabel bild=new JLabel();
		bild.setIcon(new ImageIcon(fr.getBackend().getFeldBild(pos)));

		add(bild,BorderLayout.WEST);
		
		add(setLabelHeader("Stadt "+dSt[0].get("name")+" von "+dSp.get("name")
				+" aus "+dSp.get("nation")),BorderLayout.NORTH);
		add(jp,BorderLayout.CENTER);
		add(jpButtons,BorderLayout.EAST);			
		add(abbrechen,BorderLayout.SOUTH);
		setSize(new Dimension(500,300));
//		setLocationRelativeTo(fr);
		setModal(true);
		setVisible(true);
	
	}
	
	private void setDaten(){
		removeEintraege();
		addEintrag(1,jp,"Gruendung in Runde: ",""+dSt[0].get("rundeGruendung"));
		addEintrag(2,jp,"Groesse: ",""+dSt[0].get("groesse"));
		// TODO Angabe zur Produktion der Stadt
		addEintrag(3,jp,"In Produktion: ",""+dSt[0].get("inProduktion"));//Martin
		addEintrag(4,jp,"Siedler Anteil: ",""+dSt[0].get("siedlerAnteil") + "/15");
		addEintrag(5,jp,"Krieger Anteil: ",""+dSt[0].get("kriegerAnteil") + "/5");
		addEintrag(6,jp,"Produzierte Siedler: ",""+dSt[0].get("siedler"));
		addEintrag(7,jp,"Produzierte Krieger: ",""+dSt[0].get("krieger"));	
	}

	private void addEintrag(int zeile,JPanel jp,String kopf,String daten){
		GridBagConstraints gbc;
		gbc=new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=zeile;
		gbc.gridwidth=20;
		gbc.gridheight=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		jp.add(setLabel(kopf),gbc);
		gbc=new GridBagConstraints();
		gbc.gridx=21;
		gbc.gridy=zeile;
		gbc.gridwidth=20;
		gbc.gridheight=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		jp.add(setLabel(daten),gbc);
	}
	
	private void removeEintraege(){
		jp.removeAll();
		jp.revalidate();
	}

	private JLabel setLabel(String text){
		JLabel jl=new JLabel();
		jl.setFont(new Font("Arial",Font.BOLD,12));
		jl.setText(text);
		return jl;
	}

	private JLabel setLabelHeader(String text){
		JLabel jl=new JLabel();
		jl.setFont(new Font("Arial",Font.BOLD,16));
		jl.setText(text);
		return jl;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		String[] einheiten = {"Siedler", "Krieger"};
		JComboBox wahlEinheitProduktion = new JComboBox(einheiten);
		iBackend ba = fr.getBackend();
		Object o=ev.getSource();
		if (o==buttons[0]){
			
			if(!(dSt[0].get("inProduktion").equals("nichts"))){
				JOptionPane.showMessageDialog(null, "Eine Einheit ist in Produktion!", "Nicht möglich!", JOptionPane.WARNING_MESSAGE);
				
				
			}
			else{
		
			JOptionPane value = new JOptionPane(wahlEinheitProduktion, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
			value.createDialog(null, "Wähle eine Einheit").setVisible(true);
			
			if((value.getValue() != null) && (value.getValue().equals(JOptionPane.OK_OPTION))){

				String s = wahlEinheitProduktion.getSelectedItem().toString();
				
				if(s.equals("Siedler")){
//					fr.log("Ein Siedler wird in 15 Runden Produziert!");
					ba.produktion(dSt, s);
				}
				if(s.equals("Krieger")){
//					fr.log("Ein Krieger wird in 5 Runden Produziert!");
					ba.produktion(dSt, s);
				}
				this.setVisible(false);
				this.dispose();
			}
			if((value.getValue() != null) && (value.getValue().equals(JOptionPane.CANCEL_OPTION))){
				this.setVisible(false);
				this.dispose();
			}
		}
			}
		if (o==abbrechen){
			this.setVisible(false);
			this.dispose();
		}
	}
}
