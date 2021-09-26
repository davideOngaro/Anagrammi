package anagramma;

import java.util.HashSet;
import java.util.Set;

public class Anagramma {
	public static void main(String[] args) {
		String lettere = "abcdefghijk";
		long start = System.currentTimeMillis();  
		char[][] prova = start(lettere.toCharArray());
		long time = System.currentTimeMillis() - start; 
		/*start = System.currentTimeMillis();
		System.out.println(prova.length);
			for(int i = 0; i< prova.length ; i++) {
				System.out.println(prova[i]);
			}
		long time2 = System.currentTimeMillis() - start;
		*/ 
		System.out.println();
	System.out.println("runtime per "+ prova.length + " risultati � di " + time+" millis");
	//System.out.println("Tempo di stampa :"+time2+" millis");
	}
	public static char[][] start(char[] toCompile) {
		int inputLength = toCompile.length;
		int matrixResLength = fatt(inputLength);
		return compile(toCompile,matrixResLength,inputLength);
		
	}
	public static char[][] compile(char[] daCompilare , int matrixLength,int rowLength){
		char[][] resultMatrix = new char[matrixLength][rowLength];
		if (rowLength == 4) {
			return assembler(
						threeLettersCompile(
								new char[]{
										daCompilare[1],
										daCompilare[2],
										daCompilare[3]}),
						daCompilare[0],
						resultMatrix);
		}
		
		
		
		return assembler(start(subArray(daCompilare)) , daCompilare[0] , resultMatrix );
	}
	public static char[][] assembler(char[][] compiledMatrix , char scrollingChar , char[][] toCompileMatrix) {
		final int resLength = toCompileMatrix.length;
		final int rowLength = toCompileMatrix[0].length;
		final int rowDelimiter = compiledMatrix.length;
		int rowCounter = 0;
		int columnCounter = 0;
		for(int i = 0 ; i < resLength ; i++) {
			
			toCompileMatrix[i] = buildArray(compiledMatrix[rowCounter],scrollingChar,columnCounter);
			rowCounter++;
			if(rowCounter==rowDelimiter) {
				rowCounter = 0;
				columnCounter++;
			}
			if(columnCounter > rowLength)
				columnCounter = 0;
		}
		return toCompileMatrix;
	}
	public static char[][] threeLettersCompile(char[] lastThree) {
		char[][] compiledMatrix = new char[6][3];
		compiledMatrix[0] = new char[]{lastThree[0] , lastThree[1] , lastThree[2]};
		compiledMatrix[1] = new char[]{lastThree[0] , lastThree[2] , lastThree[1]};
		compiledMatrix[2] = new char[]{lastThree[1] , lastThree[0] , lastThree[2]};
		compiledMatrix[3] = new char[]{lastThree[2] , lastThree[0] , lastThree[1]};
		compiledMatrix[4] = new char[]{lastThree[1] , lastThree[2] , lastThree[0]};
		compiledMatrix[5] = new char[]{lastThree[2] , lastThree[1] , lastThree[0]};
		return compiledMatrix;
	}
	public static int fatt(int N) {
		if(N > 12)
			return -1;
		if(N == 2)
			return 2;
		return  N *  fatt(N-1);
	}
	public static char[] buildArray(char[] original , char ch , int pos) {
		char[] array = new char[original.length+1];
		for(int i = 0; i < array.length ; i++) {
		if(i==pos)
		array[i] = ch;
		if(i<pos)
		array[i] = original[i];
		if(i>pos)
		array[i] = original[i-1];
		}
		return array;
	}
	public static char[] subArray(char[] original) {
		char[] array = new char[original.length-1];
		for(int i = 0 ; i < array.length ; i++) {
			array[i] = original[i+1];
		}
		return array;
	}
}