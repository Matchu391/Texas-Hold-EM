import java.util.Scanner;

public class Main {
  public static Deck c;
  public static PointChecker pc;// player1 point checker
  public static PointChecker pt;// player2 point checker/computer's
  public static int betNum = 0; // the bet that everyone has bet to keep playing
  public static Hand player1;
  public static Hand player2;
  public static Table t;
  public static int p1Money = 100;
  public static int p2Money = 100;

  public static void main(String[] args) {
    game();
  }

  public static void program() {
    c.shuffle();
    player1.money = p1Money;
    player2.money = p2Money;

    for (int i = 0; i < 2; i++) {
      player1.draw(c);
      player2.draw(c);
    }

    player1.rHand();
    t.flop(c);
    t.rTable();
    ask();
    if (player1.fold == false && player2.fold != true) {
      t.turn(c);
      clearScreen();
      player1.rHand();
      t.rTable();
      ask();
      if (player1.fold == false && player2.fold != true) {
        t.river(c);
        clearScreen();
        player1.rHand();
        t.rTable();
        ask();
      }
    }
    if (player1.fold != true && player2.fold != true) {
      clearScreen();
      t.rTable();
      System.out.print("Computers ");
      player2.rHand();
      System.out.print("Your ");
      player1.rHand();
      checkPoints();
      System.out.println("your points: " + player1.points);
      System.out.println("computers points: " + player2.points);
      win();
      Scanner scanner = new Scanner(System.in);
      System.out.println("");
      System.out.println("========================");
      System.out.println("Play again, yes(y) no(n)");
      String sc = scanner.nextLine();
      if (!sc.equals("y")) {
        clearScreen();
        System.out.println("\'y\' was not typed, GoodBye");
      }
      if (sc.equals("y")) {
        clearScreen();
        betNum = 0;
        game();
      }

    }
    if (player1.fold || player2.fold) {

      if (player1.fold == true) {
        System.out.println("You folded");
        p2Money -= player2.betting;
        p1Money -= player1.betting;
        p2Money += betNum;
        System.out.println("you lost: " + player1.betting);
      }
      if (player2.fold == true) {
        System.out.println("Computer folded");
        p1Money -= player1.betting;
        p2Money -= player2.betting;
        p1Money += betNum;
        System.out.println("you won: $" + betNum);
      }

      Scanner scanner = new Scanner(System.in);
      System.out.println("");
      System.out.println("========================");
      System.out.println("Play again, yes(y) no(n)");
      String sc = scanner.nextLine();
      if (!sc.equals("y")) {
        clearScreen();
        System.out.println("\'y\' was not typed, GoodBye");
      }
      if (sc.equals("y")) {

        betNum = 0;
        clearScreen();
        game();
      }
    }
  }

  public static void ask() {

    if (player2.fold == false) {
      System.out.println("Pot: " + betNum);
      Scanner scanner = new Scanner(System.in);
      System.out.println("call/check (c), fold(f), raise(r)");
      String str = scanner.nextLine();
      if (str.equals("f")) {
        player1.fold = true;
      }
      if (str.equals("r")) {
        player1.betting = player2.betting;
        bet();
      }
      if (str.equals("c")) {
        player1.betting = player2.betting;

      }

      if (!str.equals("r")) {
        if (!str.equals("c")) {
          if (!str.equals("f")) {
            System.out.println("===================");
            System.out.println("Invalid Response");
            ask();
          }
        }
      }
      compBet();
    }
  }

  public static void checkPoints() {
    pt.setup(t, player2);
    pc.setup(t, player1);

    player1.points = 0;
    player2.points = 0;
    // flush
    if (pc.dia >= 5 || pc.heart >= 5 || pc.club >= 5 || pc.spade >= 5) {
      player1.points = 4;

    } else {

      // full house
      if (pc.threeOf >= 1 && pc.twoOf >= 1) {
        player1.points = 6;
      } else {
        // four of
        if (pc.fourOf >= 1) {
          player1.points = 7;
        }
        // three of
        if (pc.threeOf >= 1) {
          player1.points = 3;
        }
        if (pc.twoOf >= 2) {
          player1.points = 2;
        }
        if (pc.twoOf == 1) {
          player1.points = 1;
        }
      }
    }
    if (pt.dia >= 5 || pt.heart >= 5 || pt.club >= 5 || pt.spade >= 5) {
      player2.points = 4;
    } else {
      // full house
      if (pt.threeOf >= 1 && pt.twoOf >= 1) {
        player2.points = 6;
      } else {
        // four of
        if (pt.fourOf >= 1) {
          player2.points = 7;
        }
        // three of
        if (pt.threeOf >= 1) {
          player2.points = 3;
        }
        if (pt.twoOf >= 2) {
          player2.points = 2;
        }
        if (pt.twoOf == 1) {
          player2.points = 1;
        }
      }
    }

  }

  public static void win() {
    int ptotal = 0; // high card for player
    int ctotal = 0; // high card for computer
    p1Money -= player1.betting;
    p2Money -= player2.betting;
    if (player1.points > player2.points) {
      System.out.println("you win");
      p1Money += betNum;
      p2Money -= player2.betting;
    }
    if (player2.points > player1.points) {
      System.out.println("computer wins");
      p1Money -= player1.betting;
      p2Money += betNum;
    }
    // high card tie
    if (player1.points == player2.points) {
      for (int i = 6; i > 0; i--) {
        if (pc.sorts[i] > pt.sorts[i]) {
          if (pc.sorts[i] > ptotal) {
            ptotal = pc.sorts[i];
          }
        } else if (pc.sorts[i] < pt.sorts[i]) {
          if (pt.sorts[i] > ctotal) {
            ctotal = pt.sorts[i];
          }
        }
      }
      if (ptotal > ctotal) {
        System.out.println("You Win(high card win)");
        p1Money += betNum;
        p2Money -= player2.betting;

      } else if (ctotal > ptotal) {
        System.out.println("Computer Wins(high card win)");
        p1Money -= player1.betting;
        p2Money += betNum;

      } else {
        System.out.println("Tie");
        p1Money += player1.betting;
        p2Money += player2.betting;
      }
    }
    System.out.println("Pot: " + betNum);

  }

  public static void bet() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("How much are you raising");
    int sc = scanner.nextInt();
    betNum += sc;
    player1.betting += sc;
    System.out.println("your bet is: " + betNum);
    compBet();
  }

  public static void compBet() {
    int mx = p2Money;
    checkPoints();
    if (player2.points < 4 && player1.betting > mx / 1.1) {
      System.out.println("you bet too much and computer has a bad hand");
      player2.fold = true;
    }
    if (player2.points < 3 && player1.betting > mx / 1.4) {
      player2.fold = true;
    }
    if (player2.points < 2 && player1.betting > mx / 1.6) {
      player2.fold = true;
    }
    if (player2.points < 1 && player1.betting > mx / 2.8) {
      player2.fold = true;
    }
    if (player2.fold == false) {
      player2.betting = player1.betting;
    }

    // raising if statements
    if (player2.fold == false) {
      if (player2.points > 3 && player1.betting < mx / 2.5) {
        player2.betting += (int) mx / 2.5 - player1.betting;
      }
      if (player2.points > 2 && player1.betting < mx / 3.5) {
        player2.betting += (int) mx / 3.5 - player1.betting;
      }
      if (player2.points > 1 && player1.betting < mx / 4.5) {
        player2.betting += (int) mx / 4.5 - player1.betting;
      }
      if (player2.points == 1 && player1.betting < mx / 10) {
        player2.betting += (int) mx / 10 - player1.betting;
      }
    }

    betNum = player2.betting + player1.betting;
    if (player1.fold != true) {
      if (player1.betting != player2.betting) {
        ask();
      }
    }
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
  }

  public static void game() {
    c = new Deck();
    player1 = new Hand();
    player2 = new Hand();
    t = new Table();
    pc = new PointChecker();
    pt = new PointChecker();
    program();
  }

}