import java.util.*;
// Main Class
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        var rentalSystem = new VehicleRentalSystem();

        while (true) {
            System.out.println("--------------------------------");
            System.out.println("\tVehicle Rental System");
            System.out.println("--------------------------------");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Display Available Vehicles");
            System.out.println("3. Rent Vehicle");
            System.out.println("4. Return Vehicle");
            System.out.println("5. Exit");
            System.out.println("*********************************");
            System.out.print("Select an option: ");
            int choice = input.nextInt();
            input.nextLine(); 
            switch (choice) {
                    // adding vehicle
                case 1:
                System.out.print("Enter vehicle type (car/motorcycle): ");
                String vehicleType = input.nextLine();
                
                System.out.print("Enter vehicle ID: ");
                String vehicleID = input.nextLine();
                
                System.out.print("Enter make: ");
                String make = input.nextLine();
                
                System.out.print("Enter model: ");
                String model = input.nextLine();
                
                System.out.print("Enter manufacturing year: ");
                int year = input.nextInt();
                
                System.out.print("Enter rental rate per day : ");
                double rentalRate = input.nextDouble();
                
                if (vehicleType.equalsIgnoreCase("car")) {
                    System.out.print("Enter number of doors: ");
                    int numberOfDoors = input.nextInt();
                    
                    System.out.print("Does the car have air conditioning? (true/false) : ");
                    boolean hasAC = input.nextBoolean();
                    
                    var car = new Car(vehicleID, make, model, year, rentalRate, true, numberOfDoors, hasAC);
                    rentalSystem.addVehicle(car);
                } else if (vehicleType.equalsIgnoreCase("motorcycle")) {
                    System.out.print("Does the motorcycle have a kick start? (true/false) : ");
                    boolean hasKickStart = input.nextBoolean();
                    
                    System.out.print("Enter engine displacement (in CC) : ");
                    int engineDisplacement = input.nextInt();
                    
                    var motorcycle = new MotorCycle(vehicleID, make, model, year, rentalRate, hasKickStart, true, engineDisplacement);
                    rentalSystem.addVehicle(motorcycle);
                } else {
                    System.out.println("Invalid vehicle type!");
                }
                break;
            
                case 2:
                    // Displaying available Vehicles
                    rentalSystem.displayAvailableVehicles();
                    break;
                case 3:
                    // Rent Vehicle
                    System.out.print("Enter customer ID : ");
                    String customerID = input.nextLine();
                    
                    System.out.print("Enter customer name : ");
                    String customerName = input.nextLine();
                    
                    Customer customer = new Customer(customerID, customerName);
                    
                    System.out.print("Enter vehicle ID to rent : ");
                    String rentVehicleID = input.nextLine();
                    
                    System.out.print("Enter rental period (in days) : ");
                    int rentalPeriod = input.nextInt();
                    
                    rentalSystem.rentVehicle(customer, rentVehicleID, rentalPeriod);
                    break;
                case 4:
                    System.out.print("Enter customer ID : ");
                    String returnCustomerID = input.nextLine();
                    
                    Customer returnCustomer = new Customer(returnCustomerID, "");                    
                    System.out.print("Enter vehicle ID to return : ");
                    String returnVehicleID = input.nextLine();
                    
                    rentalSystem.returnVehicle(returnCustomer, returnVehicleID);
                    break;
                case 5:
                    System.out.println("Exiting the program!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option!");
                    break;
            }    // switch closing

        }       // while loop closing
    }   // main method closing 
}  // main class closing bracket                     
// Vehicle Class
class Vehicle{
    private String vehicleID;
    private String company;
    private String model;
    private int year;
    private double rentalRate;
    private boolean available;
        
        // constructor
    public Vehicle(String vehicleID, String company, String model, int year, double rentalRate, boolean available){
        this.vehicleID = vehicleID;
        this.company = company;
        this.model = model;
        this.year = year;
        this.rentalRate = rentalRate;
        this.available = available;
    } 
    
    //accessor and mutators
    //setter
    public void setAvailability(boolean available){
        this.available = available;
    }
    // getter
    public String getVehicleID(){
        return vehicleID;
    }
    public String getCompany(){
        return company;
    }
    public String getModel(){
        return model;
    }
    public int getYear(){
        return year;
    }
    public double getRentalRate(){
        return rentalRate;
    }
    public boolean isAvailable(){
        return available;
    }
}
// Car class (subclass of vehicle class)
class Car extends Vehicle{
    private int numberofDoors;
    private boolean hasAC;

    // constructor
    public Car(String vehicleID, String company, String model, int year, double rentalRate, boolean available, int numberofDoors, boolean hasAC){
        super(vehicleID, company, model, year, rentalRate, available);
        this.numberofDoors = numberofDoors;
        this.hasAC = hasAC;
    }

    // accessors
    public int numberofDoors(){
        return numberofDoors;
    }
    public boolean hasAC(){
        return hasAC;
    }
}
// Motorcycle class (subclass of vehicle)   
class MotorCycle extends Vehicle{
    private boolean hasKickStart;
    private int engineDisplacement;

    // constructor
    public MotorCycle(String vehicleID, String company, String model, int year, double rentalRate, boolean available, boolean hasKickStart, int engineDisplacement){
        super(vehicleID, company, model, year, rentalRate, available);
        this.hasKickStart = hasKickStart;
        this.engineDisplacement = engineDisplacement;
    }

    // accessors
    public boolean hasKickStart(){
        return hasKickStart;
    }
    public int getEngineDisplacement(){
        return engineDisplacement;
    }
}

/***********************************************/
class Customer{
    private String customerID;
    private String name;
    private Vehicle[] rentedVehicles;
    private int numberOfRentedVehicles;

    // constructor
    public Customer(String customerID, String name){
        this.customerID = customerID;
        this.name = name;
        this.rentedVehicles = new Vehicle[100];
        this.numberOfRentedVehicles = 0;
    }

    // accessors
    public String getCustomerID(){
        return customerID;
    }
    public String getName(){
        return name;
    }

    /*rentVehicle(Vehicle vehicle): to rent a vehicle and add it to the list 
    of rented vehicles. */
    public void rentVehicle(Vehicle vehicle){
        if(numberOfRentedVehicles < rentedVehicles.length){
            rentedVehicles[numberOfRentedVehicles] = vehicle;
            numberOfRentedVehicles++;
            vehicle.setAvailability(false);
            System.out.println("Vehicle Rented : "+vehicle.getCompany() +" "+ vehicle.getModel());
        }else{
            System.out.println("Cannot Rent more Vehicle");
        }
    }

    /* returnVehicle(Vehicle vehicle): to return a vehicle and remove it 
    from the list of rented vehicles */
    public void returnVehicle(Vehicle vehicle) {
        for (int i = 0; i < numberOfRentedVehicles; i++) {
            if (rentedVehicles[i] == vehicle) {
                System.arraycopy(rentedVehicles, i + 1, rentedVehicles, i, numberOfRentedVehicles - i - 1);
                numberOfRentedVehicles--;
                vehicle.setAvailability(true);
                System.out.println("Vehicle returned: " + vehicle.getCompany() + " " + vehicle.getModel());
                return;
            }
        }
        System.out.println("Vehicle not found in rented list!");
    }

    /*  displayRentedVehicles(): to display the list of vehicles rented by the 
    customer. */
    public void displayRentedVehicles(){
        if(numberOfRentedVehicles > 0){
            System.out.println("Rented Vehicle for " +name+" : ");
            for(int i=0; i<numberOfRentedVehicles; i++){
                System.out.println(rentedVehicles[i].getCompany()+" "+rentedVehicles[i].getModel());
            }
        }else{
            System.out.println(name+" has not rented any vehicle");
        }
    }    
}
// vehicle rental system class
class VehicleRentalSystem {
    private Vehicle[] vehicles;
    private int numberOfVehicles;

    // constructor
    public VehicleRentalSystem() {
        vehicles = new Vehicle[50]; // Maximum of 50 vehicles (can be adjusted)
        numberOfVehicles = 0;
    }

    public void addVehicle(Vehicle vehicle) {
        if (numberOfVehicles < vehicles.length) {
            vehicles[numberOfVehicles] = vehicle;
            numberOfVehicles++;
            System.out.println("Vehicle added successfully!");
        } else {
            System.out.println("Cannot add more vehicles. Maximum capacity reached!");
        }
    }

    public void displayAvailableVehicles() {
        System.out.println("Available vehicles:");
        for (int i = 0; i < numberOfVehicles; i++) {
            if (vehicles[i].isAvailable()) {
                System.out.println(vehicles[i].getVehicleID() + " - " + vehicles[i].getCompany() + " " + vehicles[i].getModel());
            }
        }
    }

    public Vehicle findVehicleByID(String vehicleID) {
        for (int i = 0; i < numberOfVehicles; i++) {
            if (vehicles[i].getVehicleID().equals(vehicleID)) {
                return vehicles[i];
            }
        }
        return null;
    }

    public void rentVehicle(Customer customer, String vehicleID, int rentalPeriod) {
        Vehicle vehicle = findVehicleByID(vehicleID);
        if (vehicle != null && vehicle.isAvailable()) {
            customer.rentVehicle(vehicle);
            System.out.println("Rental successful. Total cost: " + (rentalPeriod * vehicle.getRentalRate()));
        } else {
            System.out.println("Vehicle not available for rental.");
        }
    }

    public void returnVehicle(Customer customer, String vehicleID) {
        Vehicle vehicle = findVehicleByID(vehicleID);
        if (vehicle != null && !vehicle.isAvailable()) {
            customer.returnVehicle(vehicle);
        } else {
            System.out.println("Vehicle not found or not rented by this customer!");
        }
    }
}