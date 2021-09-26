package anagramma;

import java.util.HashSet;
import java.util.Set;
//This Code is very fast, lets try it.

//you cannot insert more then 12 characters or it full off heap memory
public class Anagramma {
	public static void main(String[] args) {
		String lettere = "abcdefghijk";
		long start = System.currentTimeMillis();  
		char[][] prova = start(lettere.toCharArray());
		long time = System.currentTimeMillis() - start; 
		
	System.out.println("runtime for "+ prova.length + " results is " + time+" millis");
	
	}
	public static char[][] start(char[] toCompile) {
		int inputLength = toCompile.length;
		int matrixResLength = fatt(inputLength);
		return compile(toCompile,matrixResLength,inputLength);
		
	}
	public static char[][] compile(char[] toCompile , int matrixLength,int rowLength){
		char[][] resultMatrix = new char[matrixLength][rowLength];
		if (rowLength == 4) {
			return assembler(
						threeLettersCompile(
								new char[]{
										toCompile[1],
										toCompile[2],
										toCompile[3]}),
						toCompile[0],
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
