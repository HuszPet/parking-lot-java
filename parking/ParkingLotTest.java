package parking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import parking.facility.Gate;
import vehicle.Car;
import vehicle.Size;

public class ParkingLotTest{

    @Test
    public void testConstructorWithInvalidValues()
    {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0, 2));
    }
    
    @Test
    public void testTextualRepresentation()
    {
        ParkingLot parkingLot = new ParkingLot(5, 5);
        Gate gate = new Gate(parkingLot);

        
        Car smallCar1 = new Car("ABC-123", Size.SMALL, 0);
        Car smallCar2 = new Car("DEF-456", Size.SMALL, 1);
        Car largeCar1 = new Car("GHI-789", Size.LARGE, 1);
        Car smallCar3 = new Car("JKL-000", Size.SMALL, 2);
        Car largeCar2 = new Car("MNO-321", Size.LARGE, 3);

        
        gate.registerCar(smallCar1); 
        gate.registerCar(smallCar2); 
        gate.registerCar(largeCar1);
        gate.registerCar(smallCar3);
        gate.registerCar(largeCar2);
        
        gate.deRegisterCar(smallCar2.getTicketId());

        String expected = 
            "S X X X X\n" +
            "X L L X X\n" +
            "S X X X X\n" +
            "L L X X X\n" +
            "X X X X X\n";

        assertEquals(expected, parkingLot.toString());
    }
    
}

