package parking.facility;

import java.util.*;

import vehicle.Car;
import parking.ParkingLot;
import parking.facility.Space;
import vehicle.Size;

public class Gate{
    
    private final ArrayList<Car> cars = new ArrayList<>();
    private final ParkingLot parkingLot;

    public Gate(ParkingLot parkingLot)
    {
        this.parkingLot = parkingLot;
    }

    private Space findTakenSpaceByCar(Car c)
    {
        Space[][] floors = parkingLot.getFloorPlan();
        for (Space[] f : floors) {
            for (int j = 0; j < f.length; j++) 
            {
                if(f[j].getCarLicensePlate().equals(c.getLicensePlate()))
                {
                    return f[j];
                }
            }
        }
        throw new NullPointerException();
    }

    private Space findAvailableSpaceOnFloor(int floor, Car c)
    {
        Space[][] floors = parkingLot.getFloorPlan();
        for (Space[] f : floors) {
            for(int i = 0; i < f.length; i++)
            {
                if(f[i].getFloorNumber() == floor)
                {
                    if(c.getSpotOccupation() == Size.SMALL)
                    {
                        if(!f[i].isTaken())
                        {
                            return f[i];
                        }
                    }
                    else
                    {
                        if(i + 1 < f.length && !f[i].isTaken() && !f[i+1].isTaken())
                        {
                            return f[i];
                        }
                    }
                }
            }
            
        }
        return null;
    }


    public Space findAnyAvailableSpaceForCar(Car c)
    {
        Space[][] floors = parkingLot.getFloorPlan();
        for (Space[] f : floors) {
                if(c.getSpotOccupation() == Size.SMALL)
                {
                    for (int j = 0; j < f.length; j++) 
                    {
                        if(!f[j].isTaken())
                        {
                            return f[j];
                        }
                    }
                }
                else
                {
                    for (int j = 0; j < f.length-1; j++) 
                    {
                        if(!f[j].isTaken() && !f[j+1].isTaken())
                        {
                            return f[j+1];
                        }
                    }
                }            
        }
        throw new NullPointerException();
    }

    public Space findPreferredAvailableSpaceForCar(Car c)
    {
        int rows = parkingLot.getFloorPlan().length;
        int prefered = c.getPreferredFloor();
        Space found = null;
        int counter = 0;
        int latest = 0;

        int i = 0;
        while((prefered + i < rows && prefered + i >= 0) && found == null)
        {
            found = findAvailableSpaceOnFloor(prefered, c);
            latest = i;
            i=(i + 1) / 2 * (i % 2 == 0 ? 1 : -1);
            counter++;
        }
        if(counter>rows)
        {
            throw new NullPointerException();
        }
        if(i<0)
        {
            latest++;
            while(prefered + latest < rows || found == null)
            {
                found = findAvailableSpaceOnFloor(prefered, c);
                latest++;
            }
        }
        if(i>rows)
        {
            latest--;
            while(prefered + latest >= 0 || found == null)
            {
                found = findAvailableSpaceOnFloor(prefered, c);
                latest--;
            }
        }
        if(found!=null)
        {
            return found;
        }
        else
        {
            throw new NullPointerException();
        }

    }

    public boolean registerCar(Car c)
    {
        try{
            Space found = findPreferredAvailableSpaceForCar(c);
            c.setTicketId(Integer.toString(found.getFloorNumber())+Integer.toString(found.getSpaceNumber()));
            cars.add(c);
            found.addOccupyingCar(c);
            if(c.getSpotOccupation() == Size.LARGE)
            {
                parkingLot.getFloorPlan()[found.getFloorNumber()][found.getSpaceNumber()+1].addOccupyingCar(c);
            }
            return true;   
            }
        catch(NullPointerException e)
        {
            return false;
        }
        
    }

    public void registerCars(Car ... c)
    {
        try{
            for(Car car : c)
            {
                Space found = findAnyAvailableSpaceForCar(car);
                cars.add(car);
                found.addOccupyingCar(car);
            }
        }
        catch(NullPointerException e)
        {
            System.err.println("Hiba: Az auto szamara nem talalhato hely!");
        }
    }

    public void deRegisterCar(String ticketId)
    {
        Space[][] floors = parkingLot.getFloorPlan();
        int rows = floors.length;
        int colums = floors[0].length;
        Car c = null;
        for(int i = 0; i < cars.size(); i++)
        {
            if(cars.get(i).getTicketId().equals(ticketId))
            {
                c = cars.get(i);
            }
        }
        if(c!=null)
        {
            for(int i = 0; i < rows; i++)
            {
                for(int j = 0; j < colums; j++)
                {
                    if((floors[i][j].isTaken()) && floors[i][j].getCarLicensePlate().equals(c.getLicensePlate()))
                    {
                        floors[i][j].removeOccupyingCar();
                        cars.remove(c);
                    }
                }
            }
        }
        else throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
    }
}

//./check.cmd beadando/parking/facility/GateStructureTest.java parking.facility.GateStructureTest

