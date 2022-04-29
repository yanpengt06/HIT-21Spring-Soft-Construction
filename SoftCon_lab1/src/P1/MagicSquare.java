package P1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



public class MagicSquare {

	boolean isLegalMagicSquare(String fileName) throws IOException
	{	
		List<String> lines = readFromFile(fileName);
	    List<ArrayList<Integer>> matrix = linesToMatrix(lines);
	    printMatrix(matrix);
	    if(isMatrix(matrix) && isNumValid(matrix) )
	    	return checkRow(matrix) && checkCol(matrix) && checkDia(matrix);
	    return false;
	}
	private void printMatrix(List<ArrayList<Integer>> matrix) {
		for(int i = 0;i < matrix.size();i++)
	    {
	    	for(int j = 0;j < matrix.get(i).size() ;j++)
	    	{
	    		System.out.print(matrix.get(i).get(j)+" ");
	    	}
	    	System.out.println();
	    }
	}
	private List<String> readFromFile(String fileName)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		File file = new File(fileName);
	    List<String> lines = new ArrayList<>();
	    if(file.isFile() && file.exists()) {
	      InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
	      BufferedReader br = new BufferedReader(isr);
	      String lineTxt = null;
	      while ((lineTxt = br.readLine()) != null) 
	      {
	    	  lines.add(lineTxt);
	      }	
	      br.close();
	    } else {
	      System.out.println("文件不存在!");
	    }
		return lines;
	}
	
	List<ArrayList<Integer>> linesToMatrix(List<String> lines)
	{
		List<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
	    for(int i = 0;i<lines.size();i++)
	    {
	    	String[] nums_str = lines.get(i).split("\t");
	    	matrix.add(new ArrayList<Integer>());
	    	for(int j = 0;j < nums_str.length;j++)
	    	{
	    		try
	    		{
	    			matrix.get(i).add(Integer.parseInt(nums_str[j]));
	    		}
	    		catch(NumberFormatException e1)
	    		{
	    			System.out.println("You have input a float number or some blanks(Not \\t)!!!");
	    		}
	    	}
	    }
	    return matrix;
	}
	
	boolean checkRow(List<ArrayList<Integer>> matrix)
	{
	    int cons = 0;
	    boolean row_flag = true;
	    for(int i = 0;i < matrix.size() ;i++)
	    {
	    	int sum = 0;
	    	for(int j = 0;j < matrix.get(i).size() ;j++)
	    	{
	    		sum += matrix.get(i).get(j);
	    	}
	    	if(i == 0)
	    	{	
	    		cons = sum;
	    		continue;
	    	}
	    	else
	    	{
	    		if(sum!=cons)
	    		{
	    			row_flag = false;
	    		}
	    	}
	    }
	    return row_flag;

	}

	boolean checkCol(List<ArrayList<Integer>> matrix)
	{
	    int cons = 0;
	    boolean col_flag = true;
	    for(int j = 0;j < matrix.size();j++)
	    {
	    	int sum = 0;
	    	for(int i = 0;i < matrix.size() ;i++)
	    	{
	    		sum += matrix.get(i).get(j);
	    	}
	    	if(j == 0)
	    	{	
	    		cons = sum;
	    		continue;
	    	}
	    	else
	    	{
	    		if(sum!=cons)
	    		{
	    			col_flag = false;
	    		}
	    	}
	    }
	    return col_flag;

	}

	boolean checkDia(List<ArrayList<Integer>> matrix)
	{
	    int dia1 = 0,dia2 = 0;
	    for(int i = 0;i < matrix.size();i++)
	    {
	    	dia1+=matrix.get(i).get(i);
	    }
	    for(int i = matrix.size()-1;i >= 0;i--)
	    {
	    	dia2 += matrix.get(matrix.size()-1-i).get(i);
	    }
	    boolean dia_flag = dia1 == dia2;
	    return dia_flag;

	}
	
	private boolean isMatrix(List<ArrayList<Integer>> matrix)
	{
		int col = matrix.get(0).size();
		for(int i = 0;i < matrix.size();i++)
		{
			if(matrix.get(i).size() != col)
				return false;
		}
		return col == matrix.size();
	}
	
	private boolean isNumValid(List<ArrayList<Integer>> matrix)
	{
		for(int i = 0;i < matrix.size();i++)
		{
			for(int j = 0;j < matrix.get(i).size();j++)
			{
				if(matrix.get(i).get(j) < 0 )
					return false;
			}
		}
		return true;
	}
	
	public static boolean generateMagicSquare(int n) throws IOException {
		int magic[][];
		try
		{
			magic = new int[n][n];
		}
		catch(NegativeArraySizeException e1)
		{
			System.out.println("You have input a negative n!!!");
			return false;
		}
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) 
		{
			try
			{
				magic[row][col] = i;		
			}
			catch(ArrayIndexOutOfBoundsException e2)
			{
				System.out.println("You have input a even number n!!!");
				return false;
			}
			if (i % n == 0)		//每生成n个数，行数+1
				row++;
			else 
			{
				if (row == 0)
					row = n - 1;	//行数是0，row切换至最后一行
				else
					row--;
				if (col == (n - 1))	//列数是最右边一列，col切换至第一列
					col = 0;
				else
					col++;
			}
		}
		for (i = 0; i < n; i++) 
		{
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");
			System.out.println();
		}
		File file = new File("src/P1/txt/6.txt");
		FileWriter out = new FileWriter(file);
		for( i = 0;i < n;i++ )
		{
			for( j = 0;j < n;j++)
			{
				try {
					out.write(magic[i][j]+"\t");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			out.write("\r\n");
		}
		out.close();
		return true;
		}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MagicSquare ms = new MagicSquare();
//		System.out.println(ms.isLegalMagicSquare("src/P1/txt/1.txt"));
//		System.out.println(ms.isLegalMagicSquare("src/P1/txt/2.txt"));
//		System.out.println(ms.isLegalMagicSquare("src/P1/txt/3.txt"));
//		System.out.println(ms.isLegalMagicSquare("src/P1/txt/4.txt"));
//		System.out.println(ms.isLegalMagicSquare("src/P1/txt/5.txt"));
		MagicSquare.generateMagicSquare(3);
		System.out.println(ms.isLegalMagicSquare("src/P1/txt/6.txt"));
	}

}
