import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RepelClass implements ActionListener{

	boolean repel;
	
	RepelClass(){
		repel = false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		repel = true;
	}

	public boolean getRepel(){
		return repel;
	}
}
