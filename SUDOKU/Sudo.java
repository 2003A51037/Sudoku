import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Sudo implements ActionListener
{
static int[][] matrix=new int[9][9];
JFrame f = new JFrame("Sudoku");
JButton solve = new JButton("Solve");
JLabel lb1=new JLabel("");
int a,b;
int x = 35;
int y= 35;
JTextField[][] arr = new JTextField[9][9]; 
{
     for (b=0; b < 9; b++) 
     {
      x=35;
      for (a =0; a< 9;a++)
      {
           arr[a][b] = new JTextField();
           arr[a][b].setBounds(x, y, 32, 32);
           f.add(arr[a][b]);
           arr[a][b].setHorizontalAlignment(JTextField.CENTER);
           x = x+35;
            }
            y = y+35;
        }
 }
 Sudo()
{        
     solve.setBounds(110,370,70, 30);
     f.add(solve);
     solve.addActionListener(this);
     lb1.setBounds(100,400,100,100);
     f.add(lb1);
     f.setSize(600,600);
     f.setLayout(null);
     f.setVisible(true);
     for (b=0; b < 9; b++)
     {
     for (a=0; a < 9; a++)
    {
         arr[a][b].setText("0");
    }
    }
}
public void actionPerformed(ActionEvent e)
 {
     String s;
     for (b =0; b< 9; b++) 
     {
     for (a=0;a < 9; a++)
     {
          s = arr[a][b].getText();
          matrix[a][b]=Integer.parseInt(s);
     }
     }
     if(e.getSource()== solve)
     {
	 if (checkInput(matrix))
         {
	      if(solve(matrix, 9))
               {
		   for (b=0; b < 9; b++) 
                    {
		       for (a =0;a< 9;a++) 
                        {
			  arr[a][b].setText(""+matrix[a][b]);
		       }
	            }
	       }
            }
	   else
           {
	         lb1.setText("incorrect input");			
           }
     }
}
public static void main(String[] args) 
{
    new Sudo();
}
public boolean solve(int [][]board, int n) 
{
     int i,j=0,value;
     int rowInd = -1 , colInd = -1;
     for(i=0; i<n; i++)
     {
         for(j=0; j<n; j++)
         {
              if( board[i][j]==0 )
              {
                    rowInd=i;
                    colInd=j;
                    break;
                }
            }
            if(rowInd != -1)
            {
                break;
            }
        }
        if( i==n && j==n )
        {
            return true;
        }
        else
        {
            for( value=1; value<10; value++ )
            {
                if(trueVal(board,value,rowInd,colInd))
                {
                    board[rowInd][colInd] = value;
                    if(!solve(board, n))
                    {
                        board[rowInd][colInd]=0;
                    }
                    else
                    {
                        return true;
                    }
                }
            }  
            return false;
        }
}
public boolean trueVal(int [][]board, int value, int rowInd, int colInd) 
{
     for(int i=0; i<9; i++)
     {
            if(board[rowInd][i] == value)
            {
                return false;
            }
        }

        for(int j=0; j<9; j++)
        {
            if(board[j][colInd] == value)
            {
                return false;
            }
        }

        int baseRow = rowInd - ( rowInd % 3 );
        int baseCol = colInd - ( colInd % 3 );
        for(int i=baseRow; i<baseRow+3; i++)
        {
            for(int j=baseCol; j<baseCol+3; j++)
            {
                if(board[i][j] == value)
            {
                    return false;
                }
            }
        }
        return true;
}
public boolean checkInput(int[][]x) 
{
		int count=0;
		int i=0;
		int j=0;
		int value;
		int a;
		for (i=0;i<9;i++)
                  {
			for (j=0; j<9; j++)
                          {
				if (x[i][j] ==0)
                                  {
					count++;
				}
				if ((x[i][j] > 9)||(x[i][j]<0)){
					return false;
				}
			}		
		}
		if (count==81)
                 {
			return false;
		}
		for (i=0;i<9;i++)
                 {
			for (j=0; j<9; j++)
                          {
				if (x[i][j]!=0)
                                   { 
					value = x[i][j];  
					for(a=0; a<i; a++)
                                           {
						if (x[a][j] == value)
                                                    {
							return false; 
						} 
					}
					for(a=i+1; a<9; a++)
                                           {
						if (x[a][j] == value){
							return false; 
						} 
					}
					for(a=0; a<j; a++)
                                           {
						if (x[i][a] == value)
                                                   {
							return false; 
						}
					}
					for(a=j+1; a<9; a++)
                                           {
						if (x[i][a] == value)
                                                    {
							return false;
						}
					}
					int baseRow = i - ( i % 3 );
					int baseCol = j - ( j % 3 );	
					for(int c=baseRow; c<baseRow+3; c++)
                                           {
						for(int b=baseCol; b<baseCol+3; b++)
                                                    {
							if ((c!=i) && (b!=j))
                                                            {
								if (x[c][b]==value)
                                                                     {
									return false; 
								}
							}
						}
					}
				}
			}
		}
		return true; 
	}			
}