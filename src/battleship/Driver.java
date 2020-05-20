package battleship;
import java.util.Scanner;

public class Driver {

	Space[][] attack1 = new Space[10][10];
	Space[][] defend1 = new Space[10][10];
	Space[][] attack2 = new Space[10][10];
	Space[][] defend2 = new Space[10][10];

	public static void main(String[] args)
	{
		Driver a = new Driver();
		a.initBoards();
		a.initGame();
		System.out.println("testing commits");
		//a.printBoard(a.attack1);
		//a.printBoard(a.attack2);
	}

	public void initBoards()
	{
		for(int i = 0; i < attack1.length; i++)
		{
			for(int j = 0; j < attack1[i].length; j++)
			{
				attack1[i][j] = new Space(false);
			}
		}
		for(int i = 0; i < defend1.length; i++)
		{
			for(int j = 0; j < defend1[i].length; j++)
			{
				defend1[i][j] = new Space(false);
			}
		}
		for(int i = 0; i < attack2.length; i++)
		{
			for(int j = 0; j < attack2[i].length; j++)
			{
				attack2[i][j] = new Space(false);
			}
		}
		for(int i = 0; i < defend2.length; i++)
		{
			for(int j = 0; j < defend2[i].length; j++)
			{
				defend2[i][j] = new Space(false);
			}
		}
	}

	public void initGame()
	{
		Scanner input = new Scanner(System.in);
		printBoard(attack1);
		String[] names = {"Destroyer", "Submarine", "Cruiser", "Battleship", "Carrier"};
		int[] sizes = {2, 3, 3, 4, 5};
		for(int i = 0; i < 5; i++) // loops through each ship
		{
			boolean check1 = true;
			while(check1) // loops until placement of ship contains no errors
			{
				check1 = false;
				System.out.println("Player 1, place your ships:");
				Ship ship = new Ship(names[i], sizes[i]);
				//ship.printSize();
				System.out.println(names[i] + " x: ");
				int ycoord = Integer.parseInt(input.nextLine()); //I accidentally put x and y backwards so
				System.out.println("y: ");						 //I switched x and y coords here
				int xcoord = Integer.parseInt(input.nextLine());
				boolean check = true;
				String alignment = "";
				while(check) // check to make sure player inputs h or v
				{
					check = false;
					System.out.println("Vertical (V) or Horizontal (H)");
					alignment = input.nextLine();
					if(!alignment.toUpperCase().equals("H") && !alignment.toUpperCase().equals("V"))
					{
						System.out.println("Must be 'V' or 'H'.");
						check = true;
					}
				}

				boolean isViable = true;
				if(alignment.toUpperCase().equals("H")) //HORIZONTAL
				{
					if(xcoord > 9 || ycoord + ship.getSize().length > 9)// check if coordinates are out of bounds
					{
						System.out.println("Coordinate out of boudns.");
						check1 = true;
					}
					else
					{
						for(int j = 0; j < ship.getSize().length; j++)
						{
							if(attack1[xcoord][ycoord + j].getIsShip())		
							{	
								System.out.println("Space is already taken.");
								check1 = true;
								isViable = false;
								break;
							}
						}
						if(isViable)
						{
							check1 = false;
							for(int j = 0; j < ship.getSize().length; j++)
							{
								Space[] spaces = ship.getSize();
								attack1[xcoord][ycoord + j] = spaces[j];
							}
						}
					}
				}
				else if(alignment.toUpperCase().equals("V")) //VERTICAL
				{
					if(xcoord + ship.getSize().length > 9 || ycoord > 9)
					{
						System.out.println("Coordinate out of bounds.");
						check1 = true;
					}
					else
					{
						for(int j = 0; j < ship.getSize().length; j++)
						{
							//System.out.println("x: " + (xcoord + j) + " y: " + ycoord + " stuff: " + attack1[xcoord + j][ycoord].getIsShip());
							if(attack1[xcoord + j][ycoord].getIsShip())		
							{	
								System.out.println("Space is already taken.");
								isViable = false;
								check1 = true;
								break;
							}
						}
						if(isViable)
						{
							check1 = false;
							for(int j = 0; j < ship.getSize().length; j++)
							{
								Space[] spaces = ship.getSize();
								attack1[xcoord + j][ycoord] = spaces[j];
								//System.out.println("End: " + " x: " + (xcoord + j) + " y: " + ycoord + attack1[xcoord + j][ycoord].getIsShip());
							}
						}
					}
				}
				printBoard(attack1);
			}
		}
		input.close();

	}

	public void printBoard(Space[][] board)
	{
		String[] letters = {"  ", "A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I ", "J"};
		for(int i = 0; i < board.length + 1; i++)
		{
			System.out.print(letters[i]);
		}
		System.out.println();
		for(int i = 0; i < board.length; i++)
		{
			System.out.print(i + " ");
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[i][j].getIsShip())
				{
					System.out.print("X ");
				}
				else
					System.out.print("O ");
			}
			System.out.println();
		}
	}
}
