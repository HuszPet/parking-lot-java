package parking;

import parking.facility.Space;
import vehicle.Car;
import vehicle.Size;

public class ParkingLot{
    
    private final Space[][] floorPlan;

    public ParkingLot(int floorNumber, int spaceNumber)
    {
        if (floorNumber < 1 || spaceNumber < 1) {
            throw new IllegalArgumentException();
        }
        floorPlan = new Space[floorNumber][spaceNumber];

        for(int i = 0; i < floorNumber; i++)
        {
            for(int j = 0; j < spaceNumber; j++)
            {
                floorPlan[i][j] = new Space(i,j);
            }
        }
    }

    public Space[][] getFloorPlan()
    {
        return floorPlan;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Space[] floor : floorPlan) {
            for (int j = 0; j < floor.length; j++) {
                Space space = floor[j];
                if (!space.isTaken()) {
                    sb.append("X");
                } else {
                    if (space.getOccupyingCarSize() == Size.SMALL) {
                        sb.append("S");
                    } else {
                        sb.append("L");
                    }
                }
                if (j < floor.length - 1) sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
    }

}

//./check.cmd  /parking/ParkingLotStructureTest.java  .parking.ParkingLotStructureTest

