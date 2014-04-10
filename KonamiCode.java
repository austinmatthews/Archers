import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KonamiCode implements KeyListener{
	
	//constansts
	public static final int UP_KEY = 38;
	public static final int DOWN_KEY = 40;
	public static final int LEFT_KEY = 37;
	public static final int RIGHT_KEY = 39;
	public static final int B_KEY = 66;
	public static final int A_KEY = 65;

	//class variables
	public static int leftKeyPress;
	public static int rightKeyPress;
	public static int upKeyPress;
	public static int downKeyPress;
	public static int aKeyPress;
	public static int bKeyPress;

	//constructor
	public KonamiCode(){

		//initialize the counters
		resetCode();
	}
	
	//resets the counters to 0
	public void resetCode(){

		//if it is not a correct key, reset the counters
		rightKeyPress = 0;
		leftKeyPress = 0;
		downKeyPress = 0;
		rightKeyPress = 0;
		aKeyPress = 0;
		bKeyPress = 0;
		upKeyPress = 0;
	}
		
	
	//check the upkey
	public void checkUpKey(KeyEvent e){

		//if up is pressed, add 1 to the upkeypress counter
		if(UP_KEY==e.getKeyCode())
		{
			upKeyPress = upKeyPress+1;

			//if another key is pressed, reset the code counter
		}else{

			//reset the counters
			resetCode();
		}
	}

	//check the down key
	public void checkDownKey(KeyEvent e){

		//if down is pressed, add 1 to the downkeypress counter
		if(DOWN_KEY==e.getKeyCode())
		{
			downKeyPress = downKeyPress+1;

			//if another key is pressed, reset the code counter
		}else{

			//reset the counters
			resetCode();
		}
	}

	//check the left key
	public void checkLeftKey(KeyEvent e){
		//if left is pressed, add 1 to the leftkeypress counter
		if(LEFT_KEY==e.getKeyCode())
		{
			leftKeyPress = leftKeyPress+1;

			//if another key is pressed, reset the code counter
		}else{

			//reset the counters
			resetCode();
		}
	}

	//check the right key
	public void checkRightKey(KeyEvent e){
		
		//if right is pressed, add 1 to the rightkey counter
		if(RIGHT_KEY==e.getKeyCode())
		{
			rightKeyPress = rightKeyPress+1;

			//if another key is pressed, reset the code counter
		}else{

			//reset the counters
			resetCode();
		}
	}

	//check the B key
	public void checkBKey(KeyEvent e){
		
		//if B is pressed, add one to b counter
		if(B_KEY==e.getKeyCode())
		{
			bKeyPress = bKeyPress+1;

			//if another key is pressed, reset the code counter
		}else{

			//reset the counters
			resetCode();
		}
	}

	//check the A Key
	
	public void checkAKey(KeyEvent e){
		//if A is pressed, add one to b counter
		if(A_KEY==e.getKeyCode())
		{
			aKeyPress = aKeyPress+1;

			//if another key is pressed, reset the code counter
		}else{

			//reset the counters
			resetCode();
		}
	}
	//**********************************************
	//following From KeyListener

	@Override
	public void keyTyped(KeyEvent e) {
		
		//print the key for testing
		//System.out.println(e.getKeyChar());
		
		//if the keycounters are 0
		if(	rightKeyPress == 0 &&
				leftKeyPress == 0 &&
				downKeyPress == 0 &&
				rightKeyPress == 0 &&
				aKeyPress == 0 &&
				bKeyPress == 0 &&
				upKeyPress == 0){	
			//check the first key
			this.checkUpKey(e);

			//if upkey is one and rest are o
		}else if(rightKeyPress == 0 &&
				leftKeyPress == 0 &&
				downKeyPress == 0 &&
				rightKeyPress == 0 &&
				aKeyPress == 0 &&
				bKeyPress == 0 &&
				upKeyPress == 1){

			//check the second key
			checkUpKey(e);

		}else if(rightKeyPress == 0 &&
				leftKeyPress == 0 &&
				downKeyPress == 0 &&
				rightKeyPress == 0 &&
				aKeyPress == 0 &&
				bKeyPress == 0 &&
				upKeyPress == 2){

			//check the third key
			checkDownKey(e);

		} else if(rightKeyPress == 0 &&
				leftKeyPress == 0 &&
				downKeyPress == 1 &&
				rightKeyPress == 0 &&
				aKeyPress == 0 &&
				bKeyPress == 0 &&
				upKeyPress == 2){


			//check the fourthkey
			checkDownKey(e);

		} else if(rightKeyPress == 0 &&
				leftKeyPress == 0 &&
				downKeyPress == 2 &&
				rightKeyPress == 0 &&
				aKeyPress == 0 &&
				bKeyPress == 0 &&
				upKeyPress == 2){

			//check fifth key
			checkLeftKey(e);

		}else if(rightKeyPress == 0 &&
				leftKeyPress == 1 &&
				downKeyPress == 2 &&
				rightKeyPress == 0 &&
				aKeyPress == 0 &&
				bKeyPress == 0 &&
				upKeyPress == 2){

			//check the sixth key
			checkRightKey(e);

		}else if (rightKeyPress == 1 &&
				leftKeyPress == 1 &&
				downKeyPress == 2 &&
				rightKeyPress == 0 &&
				aKeyPress == 0 &&
				bKeyPress == 0 &&
				upKeyPress == 2){

			//check the seventh key
			checkLeftKey(e);

		}else if(rightKeyPress == 1 &&
				leftKeyPress == 2 &&
				downKeyPress == 2 &&
				rightKeyPress == 0 &&
				aKeyPress == 0 &&
				bKeyPress == 0 &&
				upKeyPress == 2){

			//check the eighth key
			checkRightKey(e);
		} else if (rightKeyPress == 2 &&
				leftKeyPress == 2 &&
				downKeyPress == 2 &&
				rightKeyPress == 0 &&
				aKeyPress == 0 &&
				bKeyPress == 0 &&
				upKeyPress == 2){

			//check the ninth key
			checkBKey(e);
		}else if(rightKeyPress == 2 &&
				leftKeyPress == 2 &&
				downKeyPress == 2 &&
				rightKeyPress == 0 &&
				aKeyPress == 0 &&
				bKeyPress == 1 &&
				upKeyPress == 2){

			//check the ninth key
			checkAKey(e);

			//if it does not match the previous conditions,
			//reset the code

		}else{
			resetCode();
		}


	

	}


	@Override
	public void keyPressed(KeyEvent e) {
		// unused

	}


	@Override
	public void keyReleased(KeyEvent e) {
		// unused

	}
	
	}
	