import java.util.ArrayList;
import java.util.Scanner;

public class AP1_2021_1_Q1 {
	
	static boolean posRepetida (int[][] pos, int l, int c) {
		//verifica se a posicao da matriz ja foi usada.
		for (int[] e: pos) {
			if (e[0]-l == 0 && e[1]-c == 0) {
				return false;
			}
		}
		return true;
	}
	
	static boolean verFrase(char[][] matrizGerada, String frase) {
		
		int[][] pos = new int[frase.toCharArray().length][2];
                int cont=0;
		
		int s, //para string
			l, //para linha
			c, //para coluna
			lTemp=0, //para linha temporaria
			cTemp=0, //para coluna temporaria
			max = matrizGerada.length;//maximo de quadrantes 

		//loop para verificacao das letras contidas na string
		for(s=0;s<frase.toCharArray().length;s++) {
			loop:
			for(l=lTemp;l<max;l++) {
				for(c=cTemp;c<max;c++) {
					//Verifica a posicao, string e matriz 
					if(Character.toString(frase.charAt(s)).equals(Character.toString(matrizGerada[l][c])) &&
							posRepetida(pos, l, c)) {
						pos[s][0]=l;
						pos[s][1]=c;
						lTemp = l>0 ? l-1 : l;
						cTemp = c>0 ? c-1 : c;
						max = max<matrizGerada.length ? max+1 : max;
                                                cont++;
						break loop;
					}
				}
			}
		}
		
		if (cont == frase.toCharArray().length) {
			return true;
		}
		return false;
	}

	static char[][] geraMatriz(ArrayList<Character> matriz) {

		int i, k, tamanho = (int)Math.sqrt(matriz.size()), cont=0;

		char[][] matrizGerada = new char[tamanho][tamanho];

		for(i=0; i<tamanho; i++) {
			for (k=0; k<tamanho; k++) {
				matrizGerada[i][k] = matriz.get((cont < matriz.size()) ? cont++ : matriz.size());
			}
		}
		
		System.out.println("\n");
		return matrizGerada;
	}

	public static void main(String[] args) {

		//Verifica o numero de loop
		boolean loop = true;

		//Armazenamento da String
		String frase = "";

		//Armazenamento da matriz tempor�ria
		ArrayList<Character> matriz = new ArrayList<>();

		//Entrada de matriz
		Scanner scMatriz = new Scanner(System.in);
		
		do {//Loop para entrada de dados da matriz
			System.out.println("Insira um elemento para a matriz: ");
			String entradaMatriz = scMatriz.nextLine();
			if(!entradaMatriz.toLowerCase().equals("fim")) { //detecta a palavra fim
				for(char entrada: entradaMatriz.toCharArray()) {
					matriz.add(entrada);
				}
			} else {//entrada da String, apos detectado a palavra "FIM"
				Scanner scString = new Scanner(System.in);
				System.out.println("Insira a string para pesquisar: ");
				String entradaString = scString.nextLine();
				frase = entradaString;
				loop = false;
				scString.close();
			}
		}while(loop);

		scMatriz.close();
		System.out.println(verFrase(geraMatriz(matriz),frase));
	}
}