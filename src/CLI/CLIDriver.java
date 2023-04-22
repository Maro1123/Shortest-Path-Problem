package CLI;

import java.util.Scanner;

public class CLIDriver {
    public void run(){
        int choice, src = 0, dest = 0;
        Scanner sc = new Scanner(System.in);
        CLI cli = new CLI();
        cli.initialize();
        cli.setArrays();

        while(true){
            try{
                if(cli.getState() == State.INIT){
                    cli.mainMenu();
                }else if (cli.getState() == State.SSSP || cli.getState() == State.FSSSP) {
                    System.out.println("""

                            1. Get distance between source and target node\s
                            2. Get the shortest path from source to target node
                            3. Main Menu""".indent(1));
                    choice = sc.nextInt();
                    if(choice == 1 || choice == 2) {
                        System.out.print("Enter the target node: ");
                        dest = sc.nextInt();
                    }
                    switch (choice) {
                        case 1 -> cli.sourceDistance(dest);
                        case 2 -> cli.sourcePath(dest);
                        case 3 -> cli.setArrays();
                        default -> System.out.println("Invalid input! Try again.");
                    }
                }else if (cli.getState() == State.APSP){
                    System.out.println("""

                            1. Get distance between two nodes\s
                            2. Get the shortest path from one node to another
                            3. Main Menu""".indent(1));
                    choice = sc.nextInt();
                    if(choice == 1 || choice == 2) {
                        System.out.print("Enter the start node: ");
                        src = sc.nextInt();
                        System.out.print("Enter the end node: ");
                        dest = sc.nextInt();
                    }
                    switch (choice) {
                        case 1 -> cli.pairDistance(src,dest);
                        case 2 -> cli.pairPath(src,dest);
                        case 3 -> cli.setArrays();
                        default -> System.out.println("Invalid input! Try again.");
                    }
                }
            }catch(Exception e){
                System.out.println("Invalid input! Try again.");
            }
        }
    }
}
