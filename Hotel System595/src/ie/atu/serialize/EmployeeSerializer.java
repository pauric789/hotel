package ie.atu.serialize;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ie.atu.hotel.Employee;

public class EmployeeSerializer {
    private ArrayList<Employee> employees;
    private final String FILENAME = "employees.bin";
    private File employeesFile;

    // Default Constructor
    public EmployeeSerializer() {
        // Construct EmployeeList ArrayList
        employees = new ArrayList<>();

        // Initialize employeesFile and physically create the file if it does not exist
        employeesFile = new File(FILENAME);
        if (!employeesFile.exists()) {
            try {
                employeesFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + FILENAME);
            }
        }
    }

    /////////////////////////////////////////////////////////////
    // Method Name : add()                                     //
    // Return Type : void                                      //
    // Parameters : None                                       //
    // Purpose : Reads one Employee record from the user       //
    //           and adds it to the ArrayList called employees //
    /////////////////////////////////////////////////////////////
    public void add() {
        // Create an Employee object
        Employee employee = new Employee();

        // TODO - Update add() so it checks if Cancel was clicked when reading Employee
        // Read its details
        employee.read();

        // Add it to the employees ArrayList
        employees.add(employee);
    }

    ///////////////////////////////////////////////////////
    // Method Name : list()                              //
    // Return Type : void                                //
    // Parameters : None                                 //
    // Purpose : Lists all Employee records in employees //
    ///////////////////////////////////////////////////////
    public void list() {
        String employeesToList = "";

        if (!employees.isEmpty()) {
            // for every Employee object in employees
            for (Employee tmpEmployee : employees) {
                // add it to employeesToList and
                employeesToList += tmpEmployee;
                // add a newline
                employeesToList += "\n";
            }

            // Display employeesToList in a messageDialog
            JOptionPane.showMessageDialog(null, employeesToList, "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);
        } else
            // Display "No Employees stored..." in messageDialog
            JOptionPane.showMessageDialog(null, "No Employees to list.", "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);
    }

    ////////////////////////////////////////////////////////////////////
    // Method Name : view()                                           //
    // Return Type : Employee                                         //
    // Parameters : None                                              //
    // Purpose : Displays the required Employee record on screen      //
    //           And returns it, or null if not found                 //
    ////////////////////////////////////////////////////////////////////
    public Employee view() {
        JOptionPane.showMessageDialog(null, "view() method must be coded!", "NOT IMPLEMENTED",
                JOptionPane.INFORMATION_MESSAGE);

        return null;
    }

    ///////////////////////////////////////////////////////////////////
    // Method Name : delete()                                        //
    // Return Type : void                                            //
    // Parameters : None                                             //
    // Purpose : Deletes the required Employee record from employees //
    ///////////////////////////////////////////////////////////////////
    public void delete() {
        JOptionPane.showMessageDialog(null, "delete() method must be coded!", "NOT IMPLEMENTED",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /////////////////////////////////////////////////////////////////
    // Method Name : edit()                                        //
    // Return Type : void                                          //
    // Parameters : None                                           //
    // Purpose : Edits the required Employee record in employees   //
    /////////////////////////////////////////////////////////////////
    public void edit() {
        JOptionPane.showMessageDialog(null, "edit() method must be coded!", "NOT IMPLEMENTED",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // This method will serialize the employees ArrayList when called,
    // i.e. it will write employees to a file called employees.bin
    public void serializeEmployees() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(employeesFile))) {
            os.writeObject(employees);
            JOptionPane.showMessageDialog(null, "Employees serialized successfully!", "SUCCESS",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error serializing employees: " + e.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // This method will deserialize the Employees ArrayList when called,
    // i.e. it will restore the employees ArrayList from the file employees.bin
    public void deserializeEmployees() {
        if (employeesFile == null || !employeesFile.exists()) {
            System.out.println("File does not exist or is not initialized.");
            return;
        }

        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(employeesFile))) {
            employees = (ArrayList<Employee>) is.readObject();
        } catch (FileNotFoundException fNFE) {
            System.out.println("Cannot open file " + employeesFile.getName() + ".");
        } catch (IOException ioE) {
            System.out.println("Cannot read from " + employeesFile.getName() + ".");
        } catch (ClassNotFoundException e) {
            System.out.println("Error during deserialization: " + e.getMessage());
        }
    }
}
