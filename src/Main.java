import java.util.Random;
import java.util.Scanner;
        public class Main {
            static Scanner input = new Scanner(System.in);//we can use these in all of our method
             static Random random = new Random();
            public static void main(String[] args) {
                welcome();
                String turn = "X";//turns are X , O , #

                boolean computerTurn =false;

                int player = incorrectPlayer();//0<player<3 is correct player
                if (player == 1) {
                    String finish = "R";///for end of game  and restart the game
                    while (finish.equals("R")) {
                        String[][] gamePage = new String[4][4];

                        initializeMatrix(gamePage);

                        randomLocking(gamePage);

                        finish = whenPlayer1(gamePage, turn, computerTurn, finish);
                    }
                } else {
                    String finish2 = "R";///for end of game and restart the game

                    while (finish2.equals("R")) {
                        String[][] game_page = new String[4][4];

                        initializeMatrix(game_page);

                        randomLocking(game_page);

                        finish2 = whenPlayer2(game_page,turn,computerTurn,finish2);
                    }
                }
            }
            public static void initializeMatrix(String[][] gamepage) {
                int number = 0;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        number += 1;
                        gamepage[i][j] = String.format("%d", number);//chang the number into a string and initialize the mattrix
                    }
                }
            }
            public static String whenPlayer1(String[][] game_page, String turn, boolean computerTurn, String finish) {

                int you = 0;// in end of the game if you = 0 computer or another player wins!
                int computer = 0;
                int number;//table house number that player1 or player2 wants

                boolean turnOfplayer1 = false;//if turnOfplayer1 = true it is its turn
                boolean turnOfplayer2 = false;
                boolean flag = false;//if flag = true the game is tie

                System.out.println("let's start ");
                System.out.println("you are X and computer is O :");

                tie://this is label of first while loop
                while (you== 0 && computer == 0) {
                    while (!turnOfplayer1) {
                        System.out.println("its player1 turn X");
                        turn = "X";

                        computerTurn = false;
                        turnOfplayer1 = true;
                        turnOfplayer2 = false;

                        System.out.println("Enter the house number you want");

                        number = input.nextInt();
                        number = incorrectNumber(number);///check 0<number<17
                        number = non_duplicationString(number, game_page, computerTurn);//check the number is duplicate or not

                        find_i_j(number, turn, game_page);//place X int matrix
                        game_show(game_page);//use showpage
                        you = winOrlose(game_page, number, turn, you);/// check ifs you=1 or no

                        flag = checkTie1(game_page,flag);
                        if (flag) {
                            break tie;
                        }
                    }
                    while (!turnOfplayer2) {
                        System.out.println("its player2 turn O");
                        turn = "O";

                        computerTurn = true;
                        turnOfplayer2 = true;
                        turnOfplayer1 = false;

                        number =random_number();
                        number = incorrectNumber(number);
                        number = non_duplicationString(number, game_page, computerTurn);//chek not duplicated

                        System.out.println("computer chose :"+number);

                        find_i_j(number, turn, game_page);
                        game_show(game_page);
                        computer = winOrlose(game_page, number, turn, computer);// check ifs computer=1 or no

                        flag = checkTie1(game_page,flag);
                        if (flag) {
                            break tie;
                        }
                    }
                }
                if (you== 1 && computer== 0) {
                    finish = toWin(finish);
                } else if (you== 0 && computer == 1) {
                    finish = toLose(finish);
                } else {
                    finish = tie(finish);
                }
                finish = end(finish);
                return finish;
            }
            public static String whenPlayer2(String[][] game_page, String turn, boolean computerTurn, String finish) {

                int player1 = 0;// in end of the game if you = 0 computer or another player wins!
                int player2 = 0;
                int number;//table house number

                boolean turnOfplayer1 = false;//if turnOfplayer1 = true it is its turn
                boolean turnOfplayer2 = false;
                boolean flag = false;//if flag = true the game is tie

                System.out.println("let's start ");
                System.out.println("you are X and computer is O :");
                tie://this is label of first while loop
                while (player1== 0 && player2 == 0) {
                    while (!turnOfplayer1) {
                        System.out.println("its player1 turn X");
                        turn = "X";

                        computerTurn = false;
                        turnOfplayer1 = true;
                        turnOfplayer2 = false;

                        System.out.println("Enter the house number you want");
                        number = input.nextInt();
                        number = incorrectNumber(number);//check 0<number<17
                        number = non_duplicationString(number, game_page, computerTurn);//check the number is duplicate or not

                        find_i_j(number, turn, game_page);//place X in matrix
                        game_show(game_page);//use showpage
                        player1 = winOrlose(game_page, number, turn, player1);// check ifs you=1 or no

                        flag = checkTie1(game_page,flag);
                        if (flag) {
                            break tie;
                        }
                    }
                    while (!turnOfplayer2) {
                        System.out.println("its player2 turn O");
                        turn = "O";

                        computerTurn = false;
                        turnOfplayer2 = true;
                        turnOfplayer1 = false;

                        System.out.println("Enter the house number you want");
                        number = input.nextInt();
                        number = incorrectNumber(number);
                        number = non_duplicationString(number, game_page, computerTurn);//chek not duplicated

                        find_i_j(number, turn, game_page);//place O in matrix
                        game_show(game_page);
                        player2 = winOrlose(game_page, number, turn, player2);// check ifs computer=1 or no

                        flag = checkTie1(game_page,flag);
                        if (flag) {
                            break tie;
                        }
                    }
                }
                if (player1== 1 && player2== 0) {
                    finish = toWin(finish);
                } else if (player1== 0 && player2 == 1) {
                    finish = toLose(finish);
                } else {
                    finish = tie(finish);
                }
                finish = end(finish);
                return finish;
            }
            public static int winOrlose(String[][] gamepage, int number, String turn,int you) {

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if ((4 * i) + j == number - 1) {
                            if (i == 2 && j == 2 ) {
                                if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i + 1][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j + 1].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j - 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i - 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j + 1].equals(turn) && gamepage[i + 1][j - 1].equals(turn)) {
                                    you = 1;
                                }else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j - 1].equals(turn) && gamepage[i + 1][j + 1].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j - 1].equals(turn) && gamepage[i - 2][j - 2].equals(turn)) {
                                    you = 1;
                                }
                            } else if (j == 0 && i == 0) {
                                if (gamepage[i][j].equals(turn) && gamepage[i][j + 1].equals(turn) && gamepage[i][j + 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j + 1].equals(turn) && gamepage[i + 2][j + 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j].equals(turn) && gamepage[i + 2][j].equals(turn)) {
                                    you = 1;
                                }
                            } else if (j == 0 && i == 1) {
                                if (gamepage[i][j].equals(turn) && gamepage[i + 1][j + 1].equals(turn) && gamepage[i + 2][j + 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j].equals(turn) && gamepage[i + 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j + 1].equals(turn) && gamepage[i][j + 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i + 1][j].equals(turn)) {
                                    you = 1;
                                }
                            } else if (j == 1 && i == 0) {
                                if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j + 1].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j + 1].equals(turn) && gamepage[i][j + 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j].equals(turn) && gamepage[i + 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j + 1].equals(turn) && gamepage[i + 2][j + 2].equals(turn)) {
                                    you = 1;
                                }
                            } else if (j == 1 && i == 1) {
                                if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i + 1][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j + 1].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j + 1].equals(turn) && gamepage[i][j + 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j].equals(turn) && gamepage[i + 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j + 1].equals(turn) && gamepage[i + 1][j - 1].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j + 1].equals(turn) && gamepage[i + 2][j + 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j - 1].equals(turn) && gamepage[i + 1][j + 1].equals(turn)) {
                                    you = 1;
                                }
                            } else if (i==0 && j==2) {
                                if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j + 1].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j - 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j].equals(turn) && gamepage[i + 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j - 1].equals(turn) && gamepage[i + 2][j - 2].equals(turn)) {
                                    you = 1;
                                }
                            } else if (i==2 && j==0) {
                                if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i + 1][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j + 1].equals(turn) && gamepage[i][j + 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i - 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j + 1].equals(turn) && gamepage[i - 2][j + 2].equals(turn)) {
                                    you = 1;
                                }
                            } else if (i==0 && j==3) {
                                if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j - 2].equals(turn)) {
                                    you = 1;
                                }else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j].equals(turn) && gamepage[i + 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j - 1].equals(turn) && gamepage[i + 2][j - 2].equals(turn)) {
                                    you = 1;
                                }
                            } else if (i==3 && j==0) {
                                if (gamepage[i][j].equals(turn) && gamepage[i][j + 1].equals(turn) && gamepage[i][j + 2].equals(turn)) {
                                    you = 1;
                                }else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i - 2][j].equals(turn)) {
                                    you = 1;
                                }else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j + 1].equals(turn) && gamepage[i - 2][j + 2].equals(turn)) {
                                    you = 1;
                                }
                            } else if (i==1 && j==2) {
                                if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i + 1][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j + 1].equals(turn)) {
                                    you = 1;
                                }else if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j - 2].equals(turn)) {
                                    you = 1;
                                }else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j].equals(turn) && gamepage[i + 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j + 1].equals(turn) && gamepage[i + 1][j - 1].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j - 1].equals(turn) && gamepage[i + 2][j - 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j - 1].equals(turn) && gamepage[i + 1][j + 1].equals(turn)) {
                                    you = 1;
                                }
                            } else if (i==2 && j==1) {
                                if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i + 1][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j + 1].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j + 1].equals(turn) && gamepage[i][j + 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i - 2][j].equals(turn)) {
                                    you = 1;
                                }else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j + 1].equals(turn) && gamepage[i - 2][j + 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j + 1].equals(turn) && gamepage[i + 1][j - 1].equals(turn)) {
                                    you = 1;
                                }else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j - 1].equals(turn) && gamepage[i + 1][j + 1].equals(turn)) {
                                    you = 1;
                                }
                            } else if (i==1 && j==3) {
                                if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i + 1][j].equals(turn)) {
                                    you = 1;
                                }else if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j - 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j].equals(turn) && gamepage[i + 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i + 1][j - 1].equals(turn) && gamepage[i + 2][j - 2].equals(turn)) {
                                    you = 1;
                                }
                            } else if (i==3 && j==1) {
                                if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j + 1].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j + 1].equals(turn) && gamepage[i][j + 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i - 2][j].equals(turn)) {
                                    you = 1;
                                }else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j + 1].equals(turn) && gamepage[i - 2][j + 2].equals(turn)) {
                                    you = 1;
                                }
                            } else if (i==3 && j==3) {
                                if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j - 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i - 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j - 1].equals(turn) && gamepage[i - 2][j - 2].equals(turn)) {
                                    you = 1;
                                }
                            } else if (i==2 && j==3) {
                                if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i + 1][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j - 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i - 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j - 1].equals(turn) && gamepage[i - 2][j - 2].equals(turn)) {
                                    you = 1;
                                }
                            } else if (j==2 && i==3) {
                                if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j + 1].equals(turn)) {
                                    you = 1;
                                }else if (gamepage[i][j].equals(turn) && gamepage[i][j - 1].equals(turn) && gamepage[i][j - 2].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j].equals(turn) && gamepage[i - 2][j].equals(turn)) {
                                    you = 1;
                                } else if (gamepage[i][j].equals(turn) && gamepage[i - 1][j - 1].equals(turn) && gamepage[i - 2][j - 2].equals(turn)) {
                                    you = 1;
                                }
                            }
                        }
                    }
                }
                return you;
            }
            public static boolean checkTie1(String[][]gamepage ,boolean flag) {
                int count = 0;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (gamepage[i][j].equals("#")|| gamepage[i][j].equals("X") || gamepage[i][j].equals("O")) {
                            count +=1;
                        }
                    }
                }
                if (count == 16){
                    flag = true;
                }
                return flag;
            }
            public static String tie(String finish) {
                System.out.println("It is a tie!");
                System.out.print("Enter R if you want to play again"+"Otherwise enter another word");
                finish = input.next();
                return finish;
            }
            public static int non_duplicationString(int number, String[][] gamePage, boolean computer) {
                boolean falg = false;

                if (computer) {
                    while (!falg) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if ((4 * i) + j == number-1) {
                                    if (gamePage[i][j].equals("X")||gamePage[i][j].equals("O") || gamePage[i][j].equals("#")) {
                                        number = random_number();
                                    } else {
                                        falg = true;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    while (!falg) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if ((4 * i) + j == number-1) {
                                    if ((!gamePage[i][j].equals("X")) && (!gamePage[i][j].equals("O")) && (!gamePage[i][j].equals("#"))) {
                                        falg = true;
                                    } else {
                                        System.out.println("The number is duplicate,please enter a new number");
                                        number = input.nextInt();
                                    }
                                }
                            }
                        }
                    }
                }
                return number;
            }
            public static void find_i_j(int number, String turn, String[][] gamePage) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if ((4 * i) + j == number - 1) {
                            gamePage[i][j] = turn;
                        }
                    }
                }
            }
            public static int checkNon_duplication(int number1, int number2) {
                while (number2 == number1) {
                    number2 = random_number() ;
                }
                return number2;
            }
            public static int random_number() {
                int number = random.nextInt(16)+1;
                return number;
            }
            public static void randomLocking(String[][] gamePage) {
                String turn = "#";

                int number1 = random_number() ;
                int number2 = random_number() ;
                number2 = checkNon_duplication(number1, number2);

                int number3 = random_number();
                number3 = checkNon_duplication(number2, number3);
                number3 = checkNon_duplication(number1, number3);

                find_i_j(number1, turn, gamePage);
                find_i_j(number2, turn, gamePage);
                find_i_j(number3, turn, gamePage);

                System.out.println("Houses No." + number1 + ", " + number2 + " and " + number3 + " were locked");
            }
            public static String toLose(String finish) {
                System.out.println("player1 lost!");
                System.out.println("Enter R if you want to play again" + "Otherwise, enter another word");
                finish = input.next();//if we use nextline >>finish = newline ! so we use next
                return finish;
            }
            public static String toWin(String finish) {
                System.out.println("player1 win!");
                System.out.println("Enter R if you want to play again"+"Otherwise, enter another word");
                finish = input.next();//if we use nextline >>finish = newline ! so we use next
                return finish;
            }
            public static String end(String finish) {
                    System.out.println("Thanks for playing !");
                    System.out.println("End");
                    return finish;
            }
            public static void game_show(String[][] gamePage) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (gamePage[i][j].equals("10")||gamePage[i][j].equals("11") ||gamePage[i][j].equals("12") ||gamePage[i][j].equals("13")||gamePage[i][j].equals("14")||gamePage[i][j].equals("15")||gamePage[i][j].equals("16")){
                            System.out.print(gamePage[i][j] + " |"); //
                        }
                        else {
                            System.out.print(gamePage[i][j] + "  |");
                        }
                    }
                    System.out.println();//print newline for new row
                    System.out.println("----------------");
                }
            }
            public static int incorrectNumber(int number ){
                while (0>number || number>17){
                    System.out.println("the number is incorrect please try again!:");
                    number = input.nextInt();
                }
                return number;
            }
            public static void welcome(){
                System.out.println("Hello welcome to Tic_Tac_Toe game !");
                System.out.println("let's start ");
                System.out.println("Enter the number of players");
            }
            public static int incorrectPlayer(){
                int player = input.nextInt();
                while (player != 1 && player != 2) {
                    System.out.println("The number is incorrect, please try again");
                    player = input.nextInt();
                }
                return player;
            }
        }
