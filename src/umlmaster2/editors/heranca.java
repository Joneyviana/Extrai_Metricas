package editor.editors;

import org.eclipse.swt.graphics.GC;

public class heranca  implements Style{

	@Override
	public void addfeature(GC gc, Ponto fim , Ponto inicio) {
		float hipotenusa =    (float) Math.sqrt(Math.pow(Math.abs(fim.x - inicio.x),2)+ Math.pow(Math.abs(fim.y - inicio.y),2));
		gc.drawLine(fim.x-8, fim.y,fim.x, fim.y +10);
	
		gc.drawLine(fim.x+8, fim.y,fim.x, fim.y+10);
		gc.drawLine(fim.x-8, fim.y,fim.x+10, fim.y);
		
	   
	}

}
