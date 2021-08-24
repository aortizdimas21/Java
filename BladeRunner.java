/*
Alison Ortiz Dimas 
Program that takes parameters from user and uses them to simulate interactions like those
in Blade Runner
 */

/*
the results of running your program with 100 for the population size,
1000 for the number of encounters,
0.2 for the probability of being a replicant,
and 0.2 for the probability of being a harasser.

RESULTS:
Welcome to the Blade Runner Simulation!

Please provide a population size. Integer please! 100
Population size: 100

Now, please provide a number of encounters to simulate. Integer please! 1000
Number of encounters to simulate: 1000

Now, please provide the probability that a newly generated human will be a replicant. Double please! 0.2
Probability that a newly generated human will be a replicant: 20.0%

Now, please provide the probability that a newly generated human will be a harasser. Double please! 0.2
Probability that a newly generated human will be a harasser: 20.0%



Number of Humans: 74
Number of Replicants: 26


Here is the data for Humans
Minimum amount of times a human was harassed: 0
Maximum amount of times a human was harassed: 3
Average amount of times a human was harassed: 0.7027027027027027


Here is the data for Replicants
Minimum amount of times a replicant was harassed: 1
Maximum amount of times a replicant was harassed: 7
Average amount of times a replicant was harassed: 3.5

*/


public class BladeRunner {

    public static void main(String[] args) {

        // gather the 4 parameters
        System.out.println("Welcome to the Blade Runner Simulation!\n");
        int popSize = getUserInt("Please provide a population size. Integer please! ");
        System.out.println("Population size: " + popSize + "\n");
        int sim = getUserInt("Now, please provide a number of encounters to simulate. Integer please! ");
        System.out.println("Number of encounters to simulate: " + sim + "\n");
        double repProb = getUserDbl("Now, please provide the probability that a newly " +
                "generated human will be a replicant. Double please! ");
        System.out.println("Probability that a newly generated human will be a replicant: " + (repProb * 100) + "% \n");
        double harasserProb = getUserDbl("Now, please provide the probability that a newly " +
                "generated human will be a harasser. Double please! ");
        System.out.println("Probability that a newly generated human will be a harasser: " + (harasserProb * 100) +
                "% \n");


        //generate the population
        Person[] population = new Person[popSize];
        for (int i = 0; i < population.length; i++) {
            population[i] = new Person(assignStatus(repProb),
                    assignHarasser(harasserProb),
                    0);
        }

        for (int i = 0; i <= sim; i++) {
            Person a = population[(int) (Math.random() * popSize)];
            Person b = population[(int) (Math.random() * popSize)];
            if (!(a == b)) {
                if (!(a.getStatus() == b.getStatus())) {
                    if (a.isHarasser() == 1 & b.isHarasser() == 1) {
                        a.wasHarassed();
                        b.wasHarassed();
                    }
                    if (a.isHarasser() == 0 & b.isHarasser() == 1) {
                        a.wasHarassed();
                    }
                    if (a.isHarasser() == 1 & b.isHarasser() == 0) {
                        b.wasHarassed();
                    }

                }
            }

        }

        System.out.println("\n");
        System.out.println("Number of Humans: " + countHumans(population));
        System.out.println("Number of Replicants: " + countReplicants(population));
        System.out.println("\n");

        System.out.println("Here is the data for Humans");
        System.out.println("Minimum amount of times a human was harassed: " + minHarassedHum(population));
        System.out.println("Maximum amount of times a human was harassed: " + maxHarassedHum(population));
        System.out.println("Average amount of times a human was harassed: " + totHarassedHum(population)/countHumans(population));
        System.out.println("\n");

        System.out.println("Here is the data for Replicants");
        System.out.println("Minimum amount of times a replicant was harassed: " + minHarassedRep(population));
        System.out.println("Maximum amount of times a replicant was harassed: " + maxHarassedRep(population));
        System.out.println("Average amount of times a replicant was harassed: " + totHarassedRep(population)/countReplicants(population));
        System.out.println("\n\n");




    }


    static int getUserInt(String prompt) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        while (true) {
            try {
                System.out.print(prompt);
                return scan.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Bad answer... try again.");
                scan.nextLine();     // to clear the rest of the line
            }
        }
    }

    static double getUserDbl(String prompt) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        while (true) {
            try {
                System.out.print(prompt);
                return scan.nextDouble();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Bad answer... try again.");
                scan.nextLine();     // to clear the rest of the line
            }
        }

    }

    static int assignStatus(double repProb) {
        int j = (int) (101 * (Math.random()));
        if (j <= (repProb * 100))
            return 1;
        else return 0;
    }

    static int assignHarasser(double harasserProb ) {
        int j = (int) (101 * (Math.random()));
        if (j <= (harasserProb * 100))
            return 1;
        else return 0;
    }

  static int countReplicants(Person population[]) {
      int totalRep = 0;

      for (int memberNum = 0; memberNum < population.length; memberNum++) {
          Person member = population[memberNum];

          if (member.getStatus() == 1) {
              totalRep++;}

      }
      return totalRep;
  }

    static int countHumans(Person population[]) {
        int totalHum = 0;

        for (int memberNum = 0; memberNum < population.length; memberNum++) {
            Person member = population[memberNum];

            if (member.getStatus() == 0) {
                totalHum++;
            }

        }
        return totalHum;
    }

    static int minHarassedRep(Person population[]) {
        int minHarass = 2147483647; // largest int that java can represent

        for (int memberNum = 0; memberNum < population.length; memberNum++) {
            Person member = population[memberNum];
            if (member.getStatus() == 1) {
                if (member.timesHarassed() < minHarass) minHarass = member.timesHarassed();
            }
        }
        return minHarass;
    }


    static int minHarassedHum(Person population[]) {
        int minHarass = 2147483647; // largest int that java can represent

        for (int memberNum = 0; memberNum < population.length; memberNum++) {
            Person member = population[memberNum];
            if (member.getStatus() == 0) {
                if (member.timesHarassed() < minHarass) minHarass = member.timesHarassed();
            }
        }
        return minHarass;
    }

    static int maxHarassedRep(Person population[]) {
        int maxHarass = 0;
        for (int memberNum = 0; memberNum < population.length; memberNum++) {
            Person member = population[memberNum];
            if (member.getStatus() == 1) {
                if (member.timesHarassed() > maxHarass) maxHarass = member.timesHarassed();
            }
        }
        return maxHarass;
    }


    static int maxHarassedHum(Person population[]) {
        int maxHarass = 0;
        for (int memberNum = 0; memberNum < population.length; memberNum++) {
            Person member = population[memberNum];
            if (member.getStatus() == 0) {
                if (member.timesHarassed() > maxHarass) maxHarass = member.timesHarassed();
            }
        }
        return maxHarass;
    }

    static double totHarassedHum(Person population[]) {
        int total = 0;
        for (int memberNum = 0; memberNum < population.length; memberNum++) {
            Person member = population[memberNum];
            if (member.getStatus() == 0) {
                total = total + member.timesHarassed();
            }
        }
        return total;
    }

    static double totHarassedRep(Person population[]) {
        int total = 0;
        for (int memberNum = 0; memberNum < population.length; memberNum++) {
            Person member = population[memberNum];
            if (member.getStatus() == 1) {
                total = total + member.timesHarassed();
            }
        }
        return total;
    }

}

class Person {
    /////////////// instance variables //////////////////////////////////////
    private int status; // 0 for human and 1 for replicant
    private int harasser; // 0 if not harasser 1 for harasser
    private int numHarassed; // num times harassed


    /////////////// constructor /////////////////////////////////////////////
    public Person(int s, int h, int n) {
        status = s;
        harasser = h;
        numHarassed = n;}
    /////////////// public methods /////////////////////////////////////////

    public int getStatus() {
        return status;
    }
    public int isHarasser() {
        return harasser;
    }

    public int wasHarassed() {
        return (numHarassed += 1);
    }

    public int timesHarassed() {
        return numHarassed;
    }

    public String toString () {
        if (status == 0 & harasser == 0) {
            return "Status: Human" + ", Harasser: No" + ", Times Harassed: " + numHarassed;
        }

        if (status == 1 & harasser == 0) {
            return "Status: Replicant" + ", Harasser: No" + ", Times Harassed: " + numHarassed;
        }

        if (status == 0 & harasser == 1) {
            return "Status: Human" + ", Harasser: Yes" + ", Times Harassed: " + numHarassed;
        }

        if (status == 1 & harasser == 1) {
            return "Status: Replicant" + ", Harasser: Yes" + ", Times Harassed: " + numHarassed;
        }
        else return "false";
    }

}



