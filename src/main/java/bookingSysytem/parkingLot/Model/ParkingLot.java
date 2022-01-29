package bookingSysytem.parkingLot.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ParkingLot {

        String id;

        List<Floor> floorList;
}
