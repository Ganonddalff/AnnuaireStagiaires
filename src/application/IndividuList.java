package application;

/*import java.util.Arrays;
import java.util.List;
import java.util.LinkedList; */
import java.util.*;

public class IndividuList extends Individu {

	public List<Stagiaire> IndividuListe() {
		List <Stagiaire> liste = Arrays.asList(lesStagiaires);
		return liste;
	}

	@Override
	public String toString() {
		return  Arrays.toString(lesStagiaires) + Arrays.toString(getLesStagiaires());
	}

	



}


