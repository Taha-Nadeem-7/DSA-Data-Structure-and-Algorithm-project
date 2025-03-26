import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Project_RockPaperScissor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random ra = new Random();
        Stack stacksingle = new Stack();
        Stack stacktwo = new Stack();
        boolean run = true;
        while (run) {
            LinearQueue queue = new LinearQueue();
            System.out.println("");
            System.out.println("Player VS CPU       - press 1 : ");
            System.out.println("Player VS Player    - press 2 : ");
            System.out.println("History & Top Score - press 3 : ");
            System.out.println("About               - press 4 : ");
            System.out.println("Exit                - press 5 : ");
            System.out.print("Enter your option : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.print("Enter your name : ");
                String name = sc.next();
                int i = 0;
                while (i < 3) {
                    System.out.print("Enter your number - press 0 for Rock, press 1 for Scissor, press 2 for Paper : ");
                    int input = sc.nextInt();
                    if (input == 0 || input == 1 || input == 2) {
                        queue.enqueue(input);
                        queue.enqueue(ra.nextInt(3));
                        i++;
                    } else {
                        System.out.println("Wrong!. Please, choose correct option.");
                    }
                }
                int pwin = 0;
                int cwin = 0;
                for (i = 0; i < 3; i++) {
                    int player = queue.dequeue();
                    int cpu = queue.dequeue();
                    if (player == 0 && cpu == 1 || player == 1 && cpu == 2 || player == 2 && cpu == 0) {
                        pwin++;
                    } else if (cpu == 0 && player == 1 || cpu == 1 && player == 2 || cpu == 2 && player == 0) {
                        cwin++;
                    }
                }
                if (pwin > cwin) {
                    System.out.println(name + " Win : [" + name + " point] " + pwin + ", [CPU point] " + cwin);
                    stacksingle.push(pwin + " " + name + " win and " + cwin + " CPU loss");
                } else if (pwin < cwin) {
                    System.out.println("CPU Win : [" + name + " point] " + pwin + ", [CPU point] " + cwin);
                    stacksingle.push(cwin + " CPU" + " win and " + cwin + " " + name + " loss");
                } else {
                    System.out.println("Draw : [" + name + " point] " + pwin + ", [CPU point] " + cwin);
                    stacksingle.push(pwin + " " + name + " draw and " + cwin + " CPU draw");
                }
            } else if (choice == 2) {
                System.out.print("Enter Player 1 name : ");
                String name1 = sc.next();
                System.out.print("Enter Player 2 name : ");
                String name2 = sc.next();
                for (int i = 0; i < 3; i++) {
                    int input;
                    do {
                        System.out.print(name1 + " - press 0 for Rock, press 1 for Scissor, press 2 for Paper: ");
                        input = sc.nextInt();
                        if (input < 0 || input > 2) {
                            System.out.println("Invalid input. Please enter 0, 1, or 2.");
                        }
                    } while (input < 0 || input > 2);
                    queue.enqueue(input);
                    clearConsole();
                    do {
                        System.out.print(name2 + " - press 0 for Rock, press 1 for Scissor, press 2 for Paper: ");
                        input = sc.nextInt();
                        if (input < 0 || input > 2) {
                            System.out.println("Invalid input. Please enter 0, 1, or 2.");
                        }
                    } while (input < 0 || input > 2);
                    queue.enqueue(input);
                    clearConsole();
                }
                int p1win = 0;
                int p2win = 0;
                for (int i = 0; i < 3; i++) {
                    int player1 = queue.dequeue();
                    int player2 = queue.dequeue();
                    if (player1 == 0 && player2 == 1 || player1 == 1 && player2 == 2 || player1 == 2 && player2 == 0) {
                        p1win++;
                    } else if (player2 == 0 && player1 == 1 || player2 == 1 && player1 == 2
                            || player2 == 2 && player1 == 0) {
                        p2win++;
                    }
                }
                if (p1win > p2win) {
                    System.out.println(
                            name1 + " Win : [" + name1 + " point] " + p1win + ", [" + name2 + " point] " + p2win);
                    stacktwo.push(p1win + " win " + name1 + " and " + p2win + " loss " + name2);
                } else if (p1win < p2win) {
                    System.out.println(
                            name2 + " Win : [" + name1 + " point] " + p1win + ", [" + name2 + " point] " + p2win);
                    stacktwo.push(p2win + " win " + name2 + " and " + p1win + " loss " + name1);
                } else {
                    System.out.println("Draw : [" + name1 + " point] " + p1win + ", [" + name2 + " point] " + p2win);
                    stacktwo.push(p1win + " draw " + name1 + " and " + p2win + " draw " + name2);
                }
            } else if (choice == 3) {
                save(stacksingle);
                save(stacktwo);
                System.out.println("\n---------------Single Player History---------------");
                try (BufferedReader reader = new BufferedReader(new FileReader("SinglePlayerHistory.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                System.out.println("\n---------------Two Player History---------------");
                try (BufferedReader reader = new BufferedReader(new FileReader("TwoPlayerHistory.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                System.out.println("\n---------------Top Score of Single Player---------------");
                String inputfile1 = "SinglePlayerHistory.txt";
                sort(inputfile1);
                System.out.println("\n---------------Top Score of Two Player---------------");
                String inputfile2 = "TwoPlayerHistory.txt";
                sort(inputfile2);
            } else if (choice == 4) {
                System.out.println("\n------------------------------About------------------------------");
                System.out.println("\n---------------Data Structure Used---------------");
                System.out.println("Array, Stack, Queue & ArrayList");
                System.out.println("\n---------------Conclusion:---------------");
                System.out.println("Rock-Paper-Scissors game in Java.");
                System.out.println("Uses stacks, queues, and arrays for game data and history.");
                System.out.println("Modes: player vs CPU, player vs player.");
                System.out.println("Shows game history and top scores.");
                System.out.println("\n---------------Developed By---------------");
                System.out.println("Syed Muhammad Abdul Moiz    23FA-001-CS");
                System.out.println("Muhammad Taha Nadeem        23FA-003-CS");
                System.out.println("Prince Irshad               23FA-035-CS");
                System.out.println("Shahrif Rahman              23FA-025-CS");
            } else if (choice == 5) {
                save(stacksingle);
                save(stacktwo);
                sc.close();
                run = false;
                System.out.println("---------------Program has been closed.---------------");
            } else {
                System.out.println("Wrong!. Please, choose correct option.");
            }
        }
    }

    public static void save(Stack mysingle) {
        if (!mysingle.stack.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("SinglePlayerHistory.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    list.add(line);
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            try (FileWriter writer = new FileWriter("SinglePlayerHistory.txt")) {
                writer.write(mysingle.pop());
                while (mysingle.stack.size() > 0) {
                    writer.write("\n" + mysingle.pop());
                }
                int i = 0;
                while (list.size() > i) {
                    writer.write("\n" + list.get(i));
                    i++;
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static void sort(String inputfile) {
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputfile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                data.add(parts);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int score1 = Integer.parseInt(data.get(j)[0]);
                int score2 = Integer.parseInt(data.get(j + 1)[0]);
                if (score1 < score2) {
                    String[] temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                }
            }
        }
        for (String[] entry : data) {
            System.out.println(String.join(" ", entry));
        }
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class LinearQueue {
    int size = 6;
    int queue[] = new int[size];
    int front = -1;
    int rear = -1;

    public void enqueue(int element) {
        if (rear == size - 1) {
            System.out.println("Queue is full");
            return;
        }
        if (front == -1) {
            front = 0;
        }
        queue[++rear] = element;
    }

    public int dequeue() {
        if (front == -1 || front > rear) {
            System.out.println("Queue is empty");
            return -1;
        }
        return queue[front++];
    }
}

class Stack {
    ArrayList<String> stack = new ArrayList<>();

    public void push(String value) {
        stack.add(value);
    }

    public String pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }
}