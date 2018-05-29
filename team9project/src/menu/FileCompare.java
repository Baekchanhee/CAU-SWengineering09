package menu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;

public class FileCompare {
	/*private ArrayList<String> leftContent, rightContent;*/
	private int lcsLength = 0;
	
	public FileCompare() {
		
	}
	
	public int getLcsLength() {
		return lcsLength;
	}
	public int[][] makeLCSTable(String[] left, String[] right) {
		int[][] table = null;
		int max;
		
		// make table
		table = new int[right.length][];
		for(int i = 0; i < right.length; i++)
			table[i] = new int[left.length];
		
		// table initialize(1)
		for(int i = 0; i < left.length; i++)
			table[0][i] = 0;
		
		// Calculate Table Index and LCS length
		// ���⼭���� �ε����� ����� ���� ������ ���ٰ� ����ȴ�. ( 1��° �ε��� = 1(0�� �ƴ�) )
		for(int i = 1; i < right.length; i++) {
			max = 0;
			table[i][0] = 0;	// table initialize(2)
			for(int j = 1; j < left.length; j++) {
				if(left[j].equals(right[i]))	// ������ ���� ���� ���
				{
					max = table[i - 1][j - 1] + 1;
					table[i][j] = max;
				}
				else	// ������ ���� �ٸ����						 ... 2 3 4 5 �� j
				{											//  . ..........
					if(table[i][j - 1] > table[i - 1][j])	//	. ..........
	                    table[i][j] = table[i][j-1];		//	2 .... y ...
	                else									//	3 .. z k ...	
	                    table[i][j] = table[i-1][j];		//	�� 			�� k : y z �� ū �༮
				}											//  i
			}
			if(lcsLength < max)
				lcsLength = max;
		}
		return table;
	}
	
	public ArrayList<Integer> getDifferentLineNumberIndex(ArrayList<String> left, ArrayList<String> right) {
		ArrayList<Integer> differ_index = new ArrayList<Integer>();
		
		for(int i = 0; i < left.size(); i++) {
			if(left.get(i).equals(right.get(i)))	
				continue;
			else									// �ٸ� ���, �׺κ��� �ε����� ����
				differ_index.add(i);
		}
		return differ_index;
	}
	
	public ArrayList<String> makeLCSString(int leftLength, int rightLength, int lcsLength, int[][] table, String[] str2) // 0 �� ���Ե� ���ڿ�����
	{
		ArrayList<String> lcs = new ArrayList<String>();
		int temp0, temp1, for_j;
		temp1 = lcsLength;
		temp0 = temp1 - 1;
		for_j = leftLength - 1;
		
		for(int i = rightLength - 1 ; i > 0 ; i--) {
			for(int j = for_j; j > 0; j--) {
				if (table[i][j] == temp1 && table[i][j - 1] == temp0 && table[i - 1][j - 1] == temp0 && table[i - 1][j] == temp0) {
	                temp0--;
	                temp1--;
	                lcs.add(0, str2[i]); // lcs = str2[i] + lcs;
	                for_j = j;
	                break;
				}
			}
		}
		
		return lcs;
	}
	
	public void synchronizingTextContent(ArrayList<String> left, ArrayList<String> right, ArrayList<String> lcs) {
		/*int originLeftLength = left.size(), originRightLength = right.size();*/
		for(int i = 0, j = 0, k = 0 ; ; ) {
			if(k == lcs.size()) {
				if(i == left.size() && j == right.size())
					break;
				if(i == left.size()) // ������ ������ ���
				{
					left.add(i, "");
					i++;
					j++;
				}
				else if(j == right.size()) // �������� ������ ���
				{
					right.add(i, "");
					i++;
					j++;
				}
				else // �Ѵ� �ȳ��� ���
				{
					i++;
					j++;
				}
			}
			else if(left.get(i).equals(lcs.get(k)) && right.get(j).equals(lcs.get(k))) {
				i++; j++; k++; 
			}
			else if(left.get(i).equals(lcs.get(k))) {
				left.add(i, "");
				i++;
				j++;
			}
			else if(right.get(j).equals(lcs.get(k))) {
				right.add(j, "");
				i++;
				j++;
			}
			else {
				i++;
				j++;
			}
		}
	}
}
