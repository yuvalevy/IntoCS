import java.util.Scanner;

public class EnglishCheckers {

	// Global constants
	public static final int RED = 1;
	public static final int BLUE = -1;
	public static final int EMPTY = 0;

	public static final int SIZE = 8;

	// You can ignore these constants
	public static final int MARK = 3;
	public static EnglishCheckersGUI grid;

	public static Scanner getPlayerFullMoveScanner = null;
	public static Scanner getStrategyScanner = null;

	public static final int RANDOM = 1;
	public static final int DEFENSIVE = 2;
	public static final int SIDES = 3;
	public static final int CUSTOM = 4;

	// Task 8
	public static boolean canJump(int[][] board, int player) {
		boolean ans = false;

		if (getAllBasicJumps(board, player).length > 0) {
			ans = true;
		}

		return ans;
	}

	// helper function, similar to canJump
	public static boolean canMove(int[][] board, int player) {
		boolean ans = false;

		if (getAllBasicMoves(board, player).length > 0) {
			ans = true;
		}

		return ans;
	}

	public static void changeBoardAfterJump(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {

		// remove opponent from board
		int opponentRow = (fromRow + toRow) / 2;
		int opponentCol = (fromCol + toCol) / 2;
		board[opponentRow][opponentCol] = 0;

		// change player location
		changeDiscPosition(board, player, fromRow, fromCol, toRow, toCol);
	}

	public static void changeDiscPosition(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {

		board[toRow][toCol] = board[fromRow][fromCol];
		board[fromRow][fromCol] = 0;

		// checking for new queen
		if (!isQueen(board, player, toRow, toCol)) {
			if (((player == 1) && (toRow == (board.length - 1))) || ((player == -1) && (toRow == 0))) {
				board[toRow][toCol] = board[toRow][toCol] * 2;
			}
		}
	}

	// Assumes board is not null, coordinates are valid, and player is valid
	// Check if there is an opponent disc in the way
	public static boolean checkForOppentDisc(int[][] board, int player, int fromRow, int fromCol, int toRow,
			int toCol) {
		int atRow = (fromRow + toRow) / 2; // We know the gap between From to
											// To, is alwatys a single squre
		int atCol = (fromCol + toCol) / 2;
		if (!containsPlayer(board, player * -1, atRow, atCol)) {
			return false;
		}
		return true;
	}

	// Assumes board is not null, Checks if starting position contains a player
	// owned disc
	public static boolean containsPlayer(int[][] board, int player, int fromRow, int fromCol) {

		if ((board[fromRow][fromCol] == player) | (board[fromRow][fromCol] == (player * 2))) {
			return true;
		}
		return false;
	}

	public static int[][] copyBoard(int[][] originBoard) {
		int[][] newBoard = new int[originBoard.length][originBoard[0].length];

		for (int i = 0; i < originBoard.length; i = i + 1) {
			for (int j = 0; j < originBoard[i].length; j = j + 1) {
				newBoard[i][j] = originBoard[i][j];
			}
		}

		return newBoard;
	}

	public static int countPoints(int[][] board, int player) {

		int count = 0;

		for (int i = 0; i < board.length; i = i + 1) {
			for (int j = 0; j < board[i].length; j = j + 1) {

				if (containsPlayer(board, player, i, j)) {
					count = count + board[i][j];
				}

			}
		}
		return Math.abs(count);
	}

	// Task 1
	public static int[][] createBoard() {
		int board[][] = { { 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, -1, 0, -1, 0, -1, 0, -1 },
				{ -1, 0, -1, 0, -1, 0, -1, 0 }, { 0, -1, 0, -1, 0, -1, 0, -1 } };

		return board;
	}

	// Task 15
	public static int[][] defensivePlayer(int[][] board, int player) {

		int[][] newBoard = copyBoard(board);

		if (hasValidMoves(board, player)) {

			// first choosing jump
			int[][] validMoves = getAllBasicJumps(board, player);
			int moveIndex;
			boolean jumped = false;

			if (validMoves.length != 0) { // trying to jump

				moveIndex = (int) (Math.random() * (validMoves.length - 1));
				jumped = true;

			} else { // trying to find the best move with minimal risk

				validMoves = getAllBasicMoves(board, player);

				moveIndex = getBestDefensiveMove(board, validMoves, player);
			}

			newBoard = playMove(board, player, validMoves[moveIndex][0], validMoves[moveIndex][1],
					validMoves[moveIndex][2], validMoves[moveIndex][3]);

			if (jumped) {
				newBoard = keepEating(newBoard, player, validMoves[moveIndex][2], validMoves[moveIndex][3]);
			}
		}

		return newBoard;
	}

	// Task 13
	public static int findTheLeader(int[][] board) {
		int ans = 0;

		int player1 = countPoints(board, 1);
		int player2 = countPoints(board, -1);

		if (player1 > player2) {
			ans = 1;
		} else if (player1 < player2) {
			ans = -1;
		}

		return ans;
	}

	// Task 12
	public static boolean gameOver(int[][] board, int player) {
		boolean ans = false;

		if (!hasValidMoves(board, player)) {
			ans = true;
		}

		if ((playerDiscs(board, player).length == 0) || (playerDiscs(board, player * -1).length == 0)) {
			ans = true;
		}

		return ans;
	}

	// Task 7
	public static int[][] getAllBasicJumps(int[][] board, int player) {
		int[][] moves = new int[0][4];
		int[][] discsPositions = playerDiscs(board, player);

		for (int i = 0; i < discsPositions.length; i = i + 1) {
			int[][] currentDiscJumps = getRestrictedBasicJumps(board, player, discsPositions[i][0],
					discsPositions[i][1]);
			moves = merge2DArrays(moves, currentDiscJumps);
		}

		return moves;
	}

	// Assumes board isn't null, and player is 1 or -1, Task 4
	public static int[][] getAllBasicMoves(int[][] board, int player) {
		int[][] moves = null;
		int[][] tempMoves = new int[48][4]; // Max possible moves when player
											// has 12 discs, each may have 4
											// moves
		int movesCount = 0;
		int[][] discsPositions = playerDiscs(board, player);

		for (int i = 0; i < discsPositions.length; i = i + 1) { // we know there
																// is only 2
																// columns,
																// isBasicStep
																// check all
																// coordinates
																// are valid
			int fromRow = discsPositions[i][0];
			int fromCol = discsPositions[i][1];
			if (isBasicMoveValid(board, player, fromRow, fromCol, fromRow + 1, fromCol + 1)) {
				insertMoveInRow(tempMoves, movesCount, fromRow, fromCol, fromRow + 1, fromCol + 1);
				movesCount = movesCount + 1;
			}
			if (isBasicMoveValid(board, player, fromRow, fromCol, fromRow - 1, fromCol + 1)) {
				insertMoveInRow(tempMoves, movesCount, fromRow, fromCol, fromRow - 1, fromCol + 1);
				movesCount = movesCount + 1;
			}
			if (isBasicMoveValid(board, player, fromRow, fromCol, fromRow + 1, fromCol - 1)) {
				insertMoveInRow(tempMoves, movesCount, fromRow, fromCol, fromRow + 1, fromCol - 1);
				movesCount = movesCount + 1;
			}
			if (isBasicMoveValid(board, player, fromRow, fromCol, fromRow - 1, fromCol - 1)) {
				insertMoveInRow(tempMoves, movesCount, fromRow, fromCol, fromRow - 1, fromCol - 1);
				movesCount = movesCount + 1;
			}
		}

		moves = shrink2DArray(tempMoves, movesCount, 4);

		return moves;
	}

	// returns the index of the best defensive move of of validMoved array
	public static int getBestDefensiveMove(int[][] board, int[][] validMoves, int player) {

		int minimizer = getAllBasicJumps(board, player * -1).length;

		// if no move will reduce the number of possible opponent jumps, a
		// random move will be chosen
		int moveIndex = (int) (Math.random() * (validMoves.length - 1));

		for (int i = 0; i < validMoves.length; i++) {

			int[][] possible = playMove(board, player, validMoves[i][0], validMoves[i][1], validMoves[i][2],
					validMoves[i][3]);

			int opponentCurrentJumpOptions = getAllBasicJumps(possible, player * -1).length;

			if (opponentCurrentJumpOptions <= minimizer) {
				minimizer = opponentCurrentJumpOptions;
				moveIndex = i;
			}
		}
		return moveIndex;
	}

	public static int getBestSidesMove(int[][] board, int[][] validMoves) {

		// if no move will reduce the number of possible opponent jumps, a
		// random move will be chosen
		int moveIndex = (int) (Math.random() * (validMoves.length - 1));

		int miniDistance = getMinDistanceFromSide(validMoves[moveIndex][3]);

		for (int i = 0; i < validMoves.length; i++) {

			int currentDistance = getMinDistanceFromSide(validMoves[i][3]);

			if (currentDistance < miniDistance) {
				miniDistance = currentDistance;
				moveIndex = i;
			}
		}
		return moveIndex;

	}

	// returns the distance of the disc from one of the sides
	public static int getMinDistanceFromSide(int colIndex) {

		/*
		 * returns the minimum distance from of of the sides - distance from
		 * right side - (i); distance from left side - (7-i)
		 */
		return Math.min(colIndex, 7 - colIndex);
	}

	/*
	 * --------------------------------------------------------- * Get a
	 * complete (possibly a sequence of jumps) move * from a human player. *
	 * ---------------------------------------------------------
	 */
	public static int[][] getPlayerFullMove(int[][] board, int player) {
		// Get first move/jump
		int fromRow = -1, fromCol = -1, toRow = -1, toCol = -1;
		boolean jumpingMove = canJump(board, player);
		boolean badMove = true;
		getPlayerFullMoveScanner = new Scanner(System.in);// I've modified it
		while (badMove) {
			if (player == 1) {
				System.out.println("Red, Please play:");
			} else {
				System.out.println("Blue, Please play:");
			}

			fromRow = getPlayerFullMoveScanner.nextInt();
			fromCol = getPlayerFullMoveScanner.nextInt();

			int[][] moves = jumpingMove ? getAllBasicJumps(board, player) : getAllBasicMoves(board, player);
			markPossibleMoves(board, moves, fromRow, fromCol, MARK);
			toRow = getPlayerFullMoveScanner.nextInt();
			toCol = getPlayerFullMoveScanner.nextInt();
			markPossibleMoves(board, moves, fromRow, fromCol, EMPTY);

			badMove = !isMoveValid(board, player, fromRow, fromCol, toRow, toCol);
			if (badMove) {
				System.out.println("\nThis is an illegal move");
			}
		}

		// Apply move/jump
		board = playMove(board, player, fromRow, fromCol, toRow, toCol);
		showBoard(board);

		// Get extra jumps
		if (jumpingMove) {
			boolean longMove = (getRestrictedBasicJumps(board, player, toRow, toCol).length > 0);
			while (longMove) {
				fromRow = toRow;
				fromCol = toCol;

				int[][] moves = getRestrictedBasicJumps(board, player, fromRow, fromCol);

				boolean badExtraMove = true;
				while (badExtraMove) {
					markPossibleMoves(board, moves, fromRow, fromCol, MARK);
					System.out.println("Continue jump:");
					toRow = getPlayerFullMoveScanner.nextInt();
					toCol = getPlayerFullMoveScanner.nextInt();
					markPossibleMoves(board, moves, fromRow, fromCol, EMPTY);

					badExtraMove = !isMoveValid(board, player, fromRow, fromCol, toRow, toCol);
					if (badExtraMove) {
						System.out.println("\nThis is an illegal jump destination :(");
					}
				}

				// Apply extra jump
				board = playMove(board, player, fromRow, fromCol, toRow, toCol);
				showBoard(board);

				longMove = (getRestrictedBasicJumps(board, player, toRow, toCol).length > 0);
			}
		}
		return board;
	}

	// Assumes board isn't null, and player is valid. Task 6
	public static int[][] getRestrictedBasicJumps(int[][] board, int player, int row, int col) {
		int[][] moves = null;

		int[][] tempMoves = new int[4][4]; // Max of 4 possible moves for one
											// disc
		int movesCount = 0;

		if (isBasicJumpValid(board, player, row, col, row + 2, col + 2)) {
			insertMoveInRow(tempMoves, movesCount, row, col, row + 2, col + 2);
			movesCount = movesCount + 1;
		}
		if (isBasicJumpValid(board, player, row, col, row - 2, col + 2)) {
			insertMoveInRow(tempMoves, movesCount, row, col, row - 2, col + 2);
			movesCount = movesCount + 1;
		}
		if (isBasicJumpValid(board, player, row, col, row + 2, col - 2)) {
			insertMoveInRow(tempMoves, movesCount, row, col, row + 2, col - 2);
			movesCount = movesCount + 1;
		}
		if (isBasicJumpValid(board, player, row, col, row - 2, col - 2)) {
			insertMoveInRow(tempMoves, movesCount, row, col, row - 2, col - 2);
			movesCount = movesCount + 1;
		}

		moves = shrink2DArray(tempMoves, movesCount, 4);

		return moves;
	}

	/*
	 * --------------------------------------------------------- * Get a
	 * strategy choice before the game. *
	 * ---------------------------------------------------------
	 */
	public static int getStrategyChoice() {
		int strategy = -1;
		getStrategyScanner = new Scanner(System.in);
		System.out.println("Choose the strategy of your opponent:" + "\n\t(" + RANDOM + ") - Random player" + "\n\t("
				+ DEFENSIVE + ") - Defensive player" + "\n\t(" + SIDES + ") - To-the-Sides player player");
		while ((strategy != RANDOM) & (strategy != DEFENSIVE) & (strategy != SIDES)) {
			strategy = getStrategyScanner.nextInt();
		}
		return strategy;
	}

	/*
	 * --------------------------------------------------------- * Get a
	 * complete (possibly a sequence of jumps) move * from a strategy. *
	 * ---------------------------------------------------------
	 */
	public static int[][] getStrategyFullMove(int[][] board, int player, int strategy) {
		if (strategy == RANDOM) {
			board = randomPlayer(board, player);
		} else if (strategy == DEFENSIVE) {
			board = defensivePlayer(board, player);
		} else if (strategy == SIDES) {
			board = sidesPlayer(board, player);
		}

		showBoard(board);
		return board;
	}

	// Task 10
	public static boolean hasValidMoves(int[][] board, int player) {
		boolean ans = false;

		if (canJump(board, player)) {
			ans = true;
		} else if (canMove(board, player)) {
			ans = true;
		}

		return ans;
	}

	public static void insertMoveInRow(int[][] moves, int atRow, int fromRow, int fromCol, int toRow, int toCol) {
		moves[atRow][0] = fromRow;
		moves[atRow][1] = fromCol;
		moves[atRow][2] = toRow;
		moves[atRow][3] = toCol;
	}

	/*
	 * ---------------------------------------------------------- * Play an
	 * interactive game between the computer and you *
	 * ----------------------------------------------------------
	 */
	public static void interactivePlay() {
		int[][] board = createBoard();
		showBoard(board);

		System.out.println("Welcome to the interactive Checkers Game !");

		int strategy = getStrategyChoice();
		System.out.println("You are the first player (RED discs)");

		boolean oppGameOver = false;
		while (!gameOver(board, RED) || !oppGameOver) {
			board = getPlayerFullMove(board, RED);

			oppGameOver = gameOver(board, BLUE);
			if (!oppGameOver) {
				EnglishCheckersGUI.sleep(200);

				board = getStrategyFullMove(board, BLUE, strategy);
			}
		}

		int winner = 0;
		if ((playerDiscs(board, RED).length == 0) | (playerDiscs(board, BLUE).length == 0)) {
			winner = findTheLeader(board);
		}

		if (winner == RED) {
			System.out.println();
			System.out.println("\t *************************");
			System.out.println("\t * You are the winner !! *");
			System.out.println("\t *************************");
		} else if (winner == BLUE) {
			System.out.println("\n======= You lost :( =======");
		} else {
			System.out.println("\n======= DRAW =======");
		}
	}

	// Assumes board is not null, Task 5
	public static boolean isBasicJumpValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {
		boolean ans = true;

		if (!isValidCoordinates(fromRow, fromCol, toRow, toCol)) {
			ans = false;
		} else if (!containsPlayer(board, player, fromRow, fromCol)) {
			ans = false;
		} else if (!isDestenationEmpty(board, toRow, toCol)) {
			ans = false;
		} else if (!isJumpStep(fromRow, fromCol, toRow, toCol)) {
			ans = false;
		} else if (!isQueen(board, player, fromRow, fromCol) && !isForwardJump(player, fromRow, toRow)) {
			ans = false;
		} else if (!checkForOppentDisc(board, player, fromRow, fromCol, toRow, toCol)) {
			ans = false;
		}

		return ans;
	}

	// Assumes board is nut null, and player is valid - Task 3
	public static boolean isBasicMoveValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {
		boolean ans = true;

		if (!isValidCoordinates(fromRow, fromCol, toRow, toCol)) {
			ans = false;
		} else if (!containsPlayer(board, player, fromRow, fromCol)) {
			ans = false;
		} else if (!isDestenationEmpty(board, toRow, toCol)) {
			ans = false;
		} else if (!isBasicStep(fromRow, fromCol, toRow, toCol)) {
			ans = false;
		} else if (!isQueen(board, player, fromRow, fromCol) && !isForwardMove(player, fromRow, toRow)) {
			ans = false;
		}

		return ans;
	}

	// Assumes coordinates are valid
	public static boolean isBasicStep(int fromRow, int fromCol, int toRow, int toCol) {
		boolean basic = true;
		if (Math.abs(fromRow - toRow) != 1) {
			basic = false;
		}
		if (Math.abs(fromCol - toCol) != 1) {
			basic = false;
		}

		return basic;
	}

	// Assumes board is not null, Checks if destination is empty
	public static boolean isDestenationEmpty(int[][] board, int toRow, int toCol) {
		if (board[toRow][toCol] != 0) {
			return false;
		}
		return true;
	}

	// Assumes player is valid, and coordinates are valid
	// Checks if forward jump, no need to check for queen
	public static boolean isForwardJump(int player, int fromRow, int toRow) {
		if ((toRow - fromRow) != (player * 2)) {
			return false;
		}
		return true;
	}

	// Assumes Player is valid, and coordinates are valid,
	// Check if move is valid for player/piece, no need to check for queen
	public static boolean isForwardMove(int player, int fromRow, int toRow) {
		if ((toRow - fromRow) != player) {
			return false;
		}
		return true;
	}

	// Assumes coordinates are valid
	public static boolean isJumpStep(int fromRow, int fromCol, int toRow, int toCol) {
		boolean basic = true;
		if (Math.abs(fromRow - toRow) != 2) {
			basic = false;
		}
		if (Math.abs(fromCol - toCol) != 2) {
			basic = false;
		}

		return basic;
	}

	// Task 9
	public static boolean isMoveValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {
		boolean ans = false;

		if (isBasicJumpValid(board, player, fromRow, fromCol, toRow, toCol)) {
			ans = true;
		}
		if (isBasicMoveValid(board, player, fromRow, fromCol, toRow, toCol) && !canJump(board, player)) {
			ans = true;
		}

		return ans;
	}

	// Assumes board is not null, and coordinates are valid, and player is valid
	// Checks For Queen
	public static boolean isQueen(int[][] board, int player, int atRow, int atCol) {
		if (board[atRow][atCol] == (player * 2)) {
			return true;
		}
		return false;
	}

	// Checks coordinates are inside board
	public static boolean isValidCoordinates(int fromRow, int fromCol, int toRow, int toCol) {
		if ((fromRow < 0) || (fromRow > 7) || (fromCol < 0) || (fromCol > 7) || (toRow < 0) || (toRow > 7)
				|| (toCol < 0) || (toCol > 7)) {
			return false;
		}
		return true;
	}

	public static int[][] keepEating(int[][] board, int player, int row, int col) {

		int[][] newBoard = copyBoard(board);

		int[][] jumps = getRestrictedBasicJumps(board, player, row, col);

		if (jumps.length != 0) {

			int moveIndex = (int) (Math.random() * (jumps.length - 1));

			newBoard = playMove(newBoard, player, row, col, jumps[moveIndex][2], jumps[moveIndex][3]);
			newBoard = keepEating(newBoard, player, jumps[moveIndex][2], jumps[moveIndex][3]);
		}

		return newBoard;
	}

	public static void main(String[] args) {

		// ******** Don't delete *********
		// CREATE THE GRAPHICAL GRID
		grid = new EnglishCheckersGUI(SIZE);
		// *******************************

		// int[][] board = createBoard();
		// showBoard(board);
		// printMatrix(board);

		interactivePlay();
		// twoPlayers();

		/* ******** Don't delete ********* */
		if (getPlayerFullMoveScanner != null) {
			getPlayerFullMoveScanner.close();
		}
		if (getStrategyScanner != null) {
			getStrategyScanner.close();
		}
		/* ******************************* */

	}

	// ******************************************************************************//

	/*
	 * --------------------------------------- * Mark/unmark the possible moves
	 * * ---------------------------------------
	 */
	public static void markPossibleMoves(int[][] board, int[][] moves, int fromRow, int fromColumn, int value) {
		for (int i = 0; i < moves.length; i = i + 1) {
			if ((moves[i][0] == fromRow) & (moves[i][1] == fromColumn)) {
				board[moves[i][2]][moves[i][3]] = value;
			}
		}

		showBoard(board);
	}

	public static int[][] merge2DArrays(int[][] arr1, int[][] arr2) {

		if (arr1.length == 0) {
			return arr2;
		}

		int[][] result = new int[arr1.length + arr2.length][arr1[0].length];

		for (int i = 0; i < arr1.length; i = i + 1) {
			for (int j = 0; j < arr1[i].length; j = j + 1) {
				result[i][j] = arr1[i][j];
			}
		}

		for (int i = 0; i < arr2.length; i = i + 1) {
			for (int j = 0; j < arr2[i].length; j = j + 1) {
				result[i + arr1.length][j] = arr2[i][j];
			}
		}

		return result;
	}

	// assumes board is not null, and player is 1 or -1, Task - 2
	public static int[][] playerDiscs(int[][] board, int player) {
		int[][] positions = null;
		int[][] tempPositions = new int[12][2]; // Max Possible positions, when
												// all player discs are on board
		int size = 0; // Number of remaining player discs
		for (int i = 0; i < 8; i = i + 1) {
			for (int j = 0; j < 8; j = j + 1) {
				if (containsPlayer(board, player, i, j)) { // Check of
															// reguler/Queen
															// discs
					tempPositions[size][0] = i;
					tempPositions[size][1] = j;
					size = size + 1;
				}
			}
		}

		positions = shrink2DArray(tempPositions, size, 2);

		return positions;
	}

	public static int[][] playMove(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {
		// Task 11

		int[][] newBoard = copyBoard(board);

		if (isJumpStep(fromRow, fromCol, toRow, toCol)) { // If step is played
															// assume its valid,
															// just need to
															// check if its
															// jump/move
			changeBoardAfterJump(newBoard, player, fromRow, fromCol, toRow, toCol);
		} else {
			changeDiscPosition(newBoard, player, fromRow, fromCol, toRow, toCol);
		}

		return newBoard;
	}

	/*
	 * -------------------------------------------------------------------------
	 * -- * Print the board * you can use it without understanding how it works.
	 * *
	 * -------------------------------------------------------------------------
	 * --
	 */
	public static void printMatrix(int[][] matrix) {
		for (int i = matrix.length - 1; i >= 0; i = i - 1) {
			for (int j = 0; j < matrix.length; j = j + 1) {
				System.out.format("%4d", matrix[i][j]);
			}
			System.out.println();
		}
	}

	/*
	 * --------------------------------------- * Print the possible moves *
	 * ---------------------------------------
	 */
	public static void printMoves(int[][] possibleMoves) {
		for (int i = 0; i < 4; i = i + 1) {
			for (int j = 0; j < possibleMoves.length; j = j + 1) {
				System.out.print(" " + possibleMoves[j][i]);
			}
			System.out.println();
		}
	}

	// Task 14
	public static int[][] randomPlayer(int[][] board, int player) {
		int[][] newBoard = copyBoard(board);

		if (hasValidMoves(board, player)) {

			// first choosing jump
			int[][] validMoves = getAllBasicJumps(board, player);
			int moveIndex;
			boolean jumped = false;

			if (validMoves.length != 0) {
				moveIndex = (int) (Math.random() * (validMoves.length - 1));
				jumped = true;
			} else {

				validMoves = getAllBasicMoves(board, player);

				moveIndex = (int) (Math.random() * (validMoves.length - 1));
			}

			newBoard = playMove(newBoard, player, validMoves[moveIndex][0], validMoves[moveIndex][1],
					validMoves[moveIndex][2], validMoves[moveIndex][3]);

			if (jumped) {
				newBoard = keepEating(newBoard, player, validMoves[moveIndex][2], validMoves[moveIndex][3]);
			}
		}

		return newBoard;
	}

	/*
	 * -------------------------------------------------------------------------
	 * -- * Shows the board in a graphic window * you can use it without
	 * understanding how it works. *
	 * -------------------------------------------------------------------------
	 * --
	 */
	public static void showBoard(int[][] board) {
		grid.showBoard(board);
	}

	// copies data from 2D array, to new one
	public static int[][] shrink2DArray(int[][] source, int newRowSize, int newColSize) {

		int[][] dest = new int[newRowSize][newColSize];

		for (int i = 0; i < newRowSize; i = i + 1) {
			for (int j = 0; j < newColSize; j = j + 1) {
				dest[i][j] = source[i][j];
			}
		}
		return dest;
	}

	// Task 16
	public static int[][] sidesPlayer(int[][] board, int player) {

		int[][] newBoard = copyBoard(board);

		if (hasValidMoves(board, player)) {

			// first choosing jump
			int[][] validMoves = getAllBasicJumps(board, player);
			int moveIndex;
			boolean jumped = false;

			if (validMoves.length != 0) { // trying to jump

				moveIndex = (int) (Math.random() * (validMoves.length - 1));
				jumped = true;

			} else { // trying to find the best move

				validMoves = getAllBasicMoves(board, player);

				moveIndex = getBestSidesMove(board, validMoves);
			}

			newBoard = playMove(board, player, validMoves[moveIndex][0], validMoves[moveIndex][1],
					validMoves[moveIndex][2], validMoves[moveIndex][3]);

			if (jumped) {
				newBoard = keepEating(newBoard, player, validMoves[moveIndex][2], validMoves[moveIndex][3]);
			}
		}

		return newBoard;

	}

	/*
	 * --------------------------------------------------------- * A game
	 * between two players *
	 * ---------------------------------------------------------
	 */
	public static void twoPlayers() {
		int[][] board = createBoard();
		showBoard(board);

		System.out.println("Welcome to the 2-player Checkers Game !");

		boolean oppGameOver = false;
		while (!gameOver(board, RED) & !oppGameOver) {
			System.out.println("\nRED's turn");
			board = getPlayerFullMove(board, RED);

			oppGameOver = gameOver(board, BLUE);
			if (!oppGameOver) {
				System.out.println("\nBLUE's turn");
				board = getPlayerFullMove(board, BLUE);
			}
		}

		int winner = 0;
		if ((playerDiscs(board, RED).length == 0) | (playerDiscs(board, BLUE).length == 0)) {
			winner = findTheLeader(board);
		}

		System.out.println();
		System.out.println("\t ************************************");
		if (winner == RED) {
			System.out.println("\t * The red player is the winner !!  *");
		} else if (winner == BLUE) {
			System.out.println("\t * The blue player is the winner !! *");
		} else {
			System.out.println("\t * DRAW !! *");
		}
		System.out.println("\t ************************************");
	}

}
