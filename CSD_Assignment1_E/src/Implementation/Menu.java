package Implementation;

import Object.Node;
import Object.Train;
import Validation.checkInput;
import java.text.ParseException;

public class Menu {
    private final linkedList trainList;
    private final checkInput input;

    public Menu() {
        trainList = new linkedList();
        input = new checkInput();
    }

    public void run() throws ParseException {
        while (true) {
            displayMenu();
            int choice = input.getValidInteger("Enter your choice: ", 1, 11);
            switch (choice) {
                case 1:
                    trainList.loadDataFromFile("D:\\FA24_S3\\CSD_Ex\\CSD_Assignment1_E\\src\\train_data.txt");
                    break;
                case 2:
                    trainList.displayData();
                    break;
                case 3:
                    Train train = new Train(
                            input.getValidID("Enter tcode: ", trainList),
                            input.getValidName("Enter train name: "),
                            input.getValidInteger("Enter seat: ", 1, 1000),
                            input.getValidInteger("Enter booked: ", 0, 1000),
                            input.getValidName("Enter departure place: "),
                            input.getValidName("Enter arrival place: "),
                            input.getValidTime("Enter departure time: "),
                            input.getValidTime("Enter arrival time: ")
                    );
                    trainList.addLast(train);
                    break;
                case 4:
                    trainList.saveDataToFile("src/train_data.txt");
                    break;
                case 5:
                    String tcode = input.getValidID("Enter tcode to search: ", trainList);
                    Node node = trainList.search(tcode);
                    if (node != null) {
                        System.out.println("Found train with tcode " + tcode);
                    } else {
                        System.out.println("Train with tcode " + tcode + " not found.");
                    }
                    break;
                case 6:
                    tcode = input.getValidID("Enter tcode to delete: ", trainList);
                    trainList.deleteAfterByCode(tcode);
                    break;
                case 7:
                    trainList.sortByCode(trainList);
                    break;
                case 8:
                    train = new Train(
                            input.getValidID("Enter tcode: ", trainList),
                            input.getValidName("Enter train name: "),
                            input.getValidInteger("Enter seat: ", 1, 1000),
                            input.getValidInteger("Enter booked: ", 0, 1000),
                            input.getValidName("Enter departure place: "),
                            input.getValidName("Enter arrival place: "),
                            input.getValidTime("Enter departure time: "),
                            input.getValidTime("Enter arrival time: ")
                    );
                    trainList.addFirst(train);
                    break;
                case 9:
                    int position = input.getValidInteger("Enter position to add after: ", 1, 1000);
                    train = new Train(
                            input.getValidID("Enter tcode: ", trainList),
                            input.getValidName("Enter train name: "),
                            input.getValidInteger("Enter seat: ", 1, 1000),
                            input.getValidInteger("Enter booked: ", 0, 1000),
                            input.getValidName("Enter departure place: "),
                            input.getValidName("Enter arrival place: "),
                            input.getValidTime("Enter departure time: "),
                            input.getValidTime("Enter arrival time: ")
                    );
                    trainList.addAfterPosition(train, position);
                    break;
                case 10:
                    tcode = input.getValidID("Enter tcode to delete after: ", trainList);
                    trainList.deleteAfterByCode(tcode);
                    break;
                case 11:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    private void displayMenu() {
        System.out.println("==========MENU===========");
        System.out.println("1. Load data from file");
        System.out.println("2. Display data");
        System.out.println("3. Input & add to the end");
        System.out.println("4. Save train list to file");
        System.out.println("5. Search by tcode");
        System.out.println("6. Delete by tcode");
        System.out.println("7. Sort by tcode");
        System.out.println("8. Input & add to beginning");
        System.out.println("9. Add after position k");
        System.out.println("10. Delete the node after the node having tcode = x");
        System.out.println("11. Exit");
    }
}