package Implementation;

import Object.Node;
import Object.Train;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class linkedList {
    public Node head;
    public Node tail;
    private final Scanner sc = new Scanner(System.in);
    
    public boolean checkArrivalPlace(Train trains) {
        return !trains.getArrivalPlace().equalsIgnoreCase(trains.getDeparturePlace());
    }
    
    public boolean checkArivvalTime(Train trains) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date depature = sdf.parse(trains.getDepartureTime());
        Date arrival = sdf.parse(trains.getArrivalTime());
        return depature.before(arrival);
    }
    
    public void addFirst(Train in) throws ParseException {
        if(checkArrivalPlace(in) && checkArivvalTime(in)) {
            Node newNode = new Node(in);
            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
            System.out.println("Added Successfully. ");
        }
        else {
            System.err.println("Added failed. The Depature place must be different from the Arrival place.");
        }
    }

    public void addLast(Train in) throws ParseException  {
        if(checkArrivalPlace(in) && checkArivvalTime(in)) {
            Node newNode = new Node(in);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        else {
            System.err.println("Added failed. The Depature place must be different from the Arrival place.");
        }
    }

    public Node search(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.getTCode().equals(id)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void addAfterPosition(Train in, int position) throws ParseException {
        Node newNode = new Node(in);

        if (position == 1) {
            addFirst(in);
            return;
        }

        Node current = head;
        int index = 0;
        while (current != null && index < position - 1) {
            current = current.next;
            index++;
        }

        if (current == null) {
            System.out.println("Position out of bounds");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;

        if (current == tail) {
            tail = newNode;
        }
    }

    public void deleteAfterByCode(String id) {
        Node current = head;

        while (current != null && !current.data.getTCode().equals(id)) {
            current = current.next;
        }

        if (current != null && current.next != null) {
            current.next = current.next.next;

            if (current.next == null) {
                tail = current;
            }
        } else if (current == null) {
            System.out.println("Node with id " + id + " not found.");
        } else {
            System.out.println("No node exists after the node with id " + id + ".");
        }
    }

    public int sizeOfList(linkedList trainList) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void sortByCode(linkedList trainList) {
        int n = sizeOfList(trainList);
        if (trainList.head == null) {
            System.err.println("List is empty.");
            return;
        }
        boolean swapped;
        Node current;
        Node last = tail.next;
        do {
            swapped = false;
            current = trainList.head;

            while (current.next != last) {
                if (current.data.getTCode().compareTo(current.next.data.getTCode()) > 0) {
                    Train temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
            last = current;
        } while (swapped);
    }

    public void loadDataFromFile(String fileName) throws ParseException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                Train train = new Train(data[0].trim(), data[1].trim(), Integer.parseInt(data[2].trim()), Integer.parseInt(data[3].trim()), data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim());
                addLast(train);               
            }
            System.out.println("Load Successfully.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void displayData() {
        Node current = head;
        System.out.println("    tcode    |   name   |  seat  |  booked  | departure_place |  arrival_place  |    departure_time    |  arrival_time");
        System.out.println("--------------------------------------------------------------------------------------------");
        while (current != null) {
            Train train = current.data;
            System.out.printf("%-12s | %-8s | %-6d | %-8d | %-15s | %-15s | %-20s | %-20s%n",
                    train.getTCode(), train.getTrainName(), train.getSeat(), train.getBooked(), train.getDeparturePlace(), train.getArrivalPlace(), train.getDepartureTime(), train.getArrivalTime());
            current = current.next;
        }
    }

    public void saveDataToFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Node current = head;
            while (current != null) {
                Train train = current.data;
                bw.write(String.format("%s|%s|%d|%d|%s|%s|%s|%s%n",
                        train.getTCode(), train.getTrainName(), train.getSeat(), train.getBooked(), train.getDeparturePlace(), train.getArrivalPlace(), train.getDepartureTime(), train.getArrivalTime()));
                current = current.next;
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}