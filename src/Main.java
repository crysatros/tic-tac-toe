import java.util.Scanner;
public class Main {
    public static void  clear(){
        for(int i = 0; i < 20; i++) System.out.println();
    }

    public static void Draw(char field[]){
        int f_size = 0;
        int size = 0;
        for(int i=3; i<8; i++){
            if (i%3 == 0 || i == 7){
                System.out.print("   ");
            if(i == 3){
            System.out.print("A   B   C");
            System.out.println();
            }
            System.out.println();
            size++;
            System.out.print(size + "  ");
            System.out.print(" " + field[(0 + f_size*3)] + " | " + field[(1 + f_size*3)] + " | " + field[(2 + f_size*3)]);
            f_size++;
            System.out.println();
            System.out.print("   ");
            }
            for(int j = 0; j < 9; j++){
                if(i % 3 == 0){
                    if(j % 3 == 0 && j != 0) System.out.print("|");
                    System.out.print("-");
                }
            }
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        char[] symb1 = new char[9];
        char[] symb2 = new char[9];
        final String menu = "menu";
        final String game = "game";
        final String end = "end";
        String state = "game";
        char player = 'x';
        char code = 'o';
        int move = 1;
        symb1[0] = '1';
        symb1[1] = '2';
        symb1[2] = '3';
        symb1[3] = '4';
        symb1[4] = '5';
        symb1[5] = '6';
        symb1[6] = '7';
        symb1[7] = '8';
        symb1[8] = '9';
        symb2 = symb1;
        int[] pMoves = {5, 1, 3, 7, 9, 2, 4, 8, 6};
        while(state != end){
            clear();
            switch(state){
                case game:
                    Draw(symb1);
                    if(move%2 != 0){
                        System.out.print("Your turn, player! Write pool number (for example: 1 or 4, or 7)");
                        String player_move = sc.nextLine();
                        int chosenPos = 0;
                        boolean appTrue = false;
                        boolean empty = false;

                        if(player_move != "0"){
                            char player_char = player_move.charAt(0);

                            for (int i = 0; i < 9; i++){
                                if(player_char == symb2[i]){
                                    chosenPos = i;
                                    appTrue = true;
                                    empty = true;

                                    if(symb1[i] == 'o' || symb1[i] == 'x') empty = false;
                                    break;
                                }
                            }
                        }
                        if(appTrue != true){
                            System.out.println("Wrong position . . .");
                            String wp = sc.nextLine();
                        } else if(empty != true){
                            System.out.println("Position isn't empty . . .");
                            String wp = sc.nextLine();
                        } else {
                            symb1[chosenPos] = player;
                            move++;
                        }
                    } else {
                        for (int i = 0; i < 9; i++){
                            if(symb1[pMoves[i]-1] != 'o' && symb1[pMoves[i]-1] != 'x'){
                                symb1[pMoves[i]-1] = code;
                                break;
                            }
                        }
                        move++;
                    }
                    boolean win = false;
                    String winner = "null";
                    for (int i = 0; i < 3; i++){
                        if(symb1[0+(i*3)] == code && symb1[1+(i*3)] == code && symb1[2+(i*3)] == code){
                            win = true;
                            winner = "You lose!";
                        } else if(symb1[0+(i*3)] == player && symb1[1+(i*3)] == player && symb1[2+(i*3)] == player){
                            win = true;
                            winner = "You won!";
                        } else if(symb1[0+(i*3)] == player && symb1[3] == player && symb1[6] == player){
                            win = true;
                            winner = "You won!";
                        } else if(symb1[0+(i*3)] == code && symb1[3] == code && symb1[6] == code){
                            win = true;
                            winner = "You lose!";
                        } else if(symb1[0+(i*3)] == code && symb1[4] == code && symb1[8] == code){
                            win = true;
                            winner = "You lose!";
                        } else if(symb1[0+(i*3)] == player && symb1[4] == player && symb1[8] == player){
                            win = true;
                            winner = "You won!";
                        } else if(symb1[0+(i*3)] == code && symb1[4] == code && symb1[6] == code){
                            win = true;
                            winner = "You lose!";
                        } else if(symb1[0+(i*3)] == player && symb1[4] == player && symb1[6] == player){
                            win = true;
                            winner = "You won!";
                        }
                    }
                    int tmp = 0;
                    for(int i = 0; i < 9; i++){
                        if(symb1[i] == 'o' || symb1[i] == 'x') tmp++;
                    }
                    if(tmp == 9 && win == false){
                        tmp = 0;
                        win = true;
                        winner = "A tie!";
                    }
                    if (win == true){
                        clear();
                        System.out.println("game over! " + winner);
                        System.out.print("One more time? Write '+' or '-' : ");
                        String answ = sc.nextLine();
                        if(answ.length() > 0){
                            char buf = answ.charAt(0);
                            if (buf == '+'){
                                win = false;
                                symb1[0] = '1';
                                symb1[1] = '2';
                                symb1[2] = '3';
                                symb1[3] = '4';
                                symb1[4] = '5';
                                symb1[5] = '6';
                                symb1[6] = '7';
                                symb1[7] = '8';
                                symb1[8] = '9';
                                move = 1;
                            } else state = end;
                        } else state = end;
                    }
                    break;
            }
        }
    }
}