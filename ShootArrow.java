import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JPanel;
public class ShootArrow
{
	int power, angle;
	double xVelocity, yVelocity, x, y, velocity;
	int gravity = 10;
	int time = 0;
	public ShootArrow(int power, int angle){
		
		xVelocity = Math.cos(angle);
	
		
		yVelocity = Math.sin(angle) - gravity * time;
		
		
		x = x + xVelocity * time;
		
		
		
		y = y +  yVelocity * time;
	
		
		velocity = Math.sin(angle);

		
	}
	
	public void calculate(){
		
	
		
		
	}
	
}