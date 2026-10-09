package parking.facility;

import vehicle.Car;
import vehicle.Size;

public class Space{

    private final int floorNumber;
    private final int spaceNumber;
    private Car occupyingCar; //betöltött szerepű kocsi

//----Getters/Setters

    public int getFloorNumber()
    {
        return floorNumber;
    }

    public int getSpaceNumber() 
    {
        return spaceNumber;
    }

    public String getCarLicensePlate()
    {
        return occupyingCar.getLicensePlate();
    }

    public Size getOccupyingCarSize()
    {
        return occupyingCar.getSpotOccupation();
    }
//--------------------

    public Space(int floorNumber, int spaceNumber)
    {
        this.spaceNumber = spaceNumber;
        this.floorNumber = floorNumber;
    }

    public boolean isTaken()
    {
        if(occupyingCar==null) return false;
        else return true;
    }

    public void addOccupyingCar(Car c)
    {
        occupyingCar = c;
    }

    public void removeOccupyingCar()
    {
        occupyingCar = null;
    }
}

//./check.cmd beadando/parking/facility/SpaceStructureTest.java parking.facility.SpaceStructureTest
