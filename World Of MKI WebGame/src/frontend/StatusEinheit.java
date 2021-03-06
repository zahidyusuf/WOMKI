package frontend;

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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import daten.d;
import daten.dEinheit;
import daten.dSpieler;


public class StatusEinheit extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private FrontendBean fr;
	private dEinheit[] dE;
	private dSpieler dSp;
	private JButton abbrechen=new JButton("Abbrechen");
	private JButton[] buttons=new JButton[10];
	
	
	public StatusEinheit(FrontendBean fr,dEinheit[] dE,int[] pos){
		super();
		this.fr=fr;
		this.dE=dE;
		this.dSp=fr.getBackend().getSpielerdaten(d.toInt(dE[0].get("idSpieler")));
		JPanel jp=new JPanel();
		JPanel jpButtons=new JPanel();
		setLayout(new BorderLayout());
		jp.setLayout(new GridBagLayout());
		jpButtons.setLayout(new GridLayout(buttons.length,1));
		
		for (int i=0;i<buttons.length;i++){
			buttons[i]=new JButton("");
			buttons[i].addActionListener(this);
			jpButtons.add(buttons[i]);
		}
		if (dE[0].get("art").equals("Siedler")){
			buttons[0].setText("Stadt gruenden");			
		}
		buttons[9].setText("Aufloesen");
		
		setTitle("Status der Einheit");
		abbrechen.addActionListener(this);

		addEintrag(1,jp,"Leben: ",dE[0].get("leben")+" von "+dE[1].get("leben"));
		addEintrag(2,jp,"Angriff: ",dE[0].get("angriff")+" von "+dE[1].get("angriff"));
		addEintrag(3,jp,"Verteidigung: ",dE[0].get("verteidigung")+" von "+dE[1].get("verteidigung"));
		addEintrag(4,jp,"Bewegung: ",dE[0].get("bewegung")+" von "+dE[1].get("bewegung"));
		
		
		
		JLabel bild=new JLabel();
		bild.setIcon(new ImageIcon(fr.getBackend().getFeldBild(pos)));

		add(bild,BorderLayout.WEST);
		add(setLabelHeader(dE[0].get("art")+" von "+dSp.get("name")+" aus "+dSp.get("nation")),BorderLayout.NORTH);
		add(jp,BorderLayout.CENTER);
		add(jpButtons,BorderLayout.EAST);
		add(abbrechen,BorderLayout.SOUTH);
		
		setSize(new Dimension(500,300));
//    setLocationRelativeTo(fr);
    setModal(true);
		setVisible(true);
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
		Object o=ev.getSource();
		if (o==buttons[0]){
			if (dE[0].get("art").equals("Siedler")){
				// Stadt gruenden
				JTextField name=new JTextField(20);
		  	Object[] eingaben={"Name der Stadt",name};
		  	JOptionPane eingabe=new JOptionPane(eingaben,JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION){
					private static final long serialVersionUID = 1L;
					 @Override
		        public void selectInitialValue() {
		          super.selectInitialValue();
		          Object[] felder=(Object[]) this.getMessage();
		          ((JTextField)felder[1]).requestFocusInWindow();
		        }
		  	};
		  	eingabe.createDialog(null, "Neue Stadt gruenden...").setVisible(true);
			  	if ((eingabe.getValue()!=null)&&(eingabe.getValue().equals(JOptionPane.OK_OPTION))){
			  		fr.getBackend().neueStadt(dE,name.getText());
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
