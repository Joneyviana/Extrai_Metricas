package editor.editors;

public class Ponto {

public int x ;
public int y;

public boolean equals(Object obj){
	 System.out.print("capiroto"+((Ponto)obj).y);
	if ((this.x == ((Ponto)obj).x)&&(this.y ==((Ponto)obj).y)){
	    System.out.print("merda");
		return true;
	}
		else
		return false;
}
}
