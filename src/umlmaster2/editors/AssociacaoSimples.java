package editor.editors;

import org.eclipse.swt.graphics.GC;

public class AssociacaoSimples implements Style{
  
	@Override
	public void addfeature(GC gc, Ponto fim , Ponto inicio) {
		gc.drawLine(fim.x-7, fim.y-5,fim.x, fim.y);
		gc.drawLine(fim.x-7, fim.y+5,fim.x, fim.y);
	}

}
