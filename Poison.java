package snake;
import java.awt.Rectangle;
import java.util.Random;

public class Poison extends Rectangle{
	
	static Random random = new Random();
	
	public Poison(int w, int h) {
		super((random.nextInt(20) + 1)*20, (random.nextInt(20) + 1) * 20, w , h);
	}
}
