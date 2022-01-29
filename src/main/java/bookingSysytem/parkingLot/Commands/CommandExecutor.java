package bookingSysytem.parkingLot.Commands;

import bookingSysytem.parkingLot.Model.Command;
import bookingSysytem.parkingLot.Model.Floor;
import bookingSysytem.parkingLot.Model.ParkingLot;

import java.util.List;

public abstract class CommandExecutor {

        ParkingLot parkingLot;

        public CommandExecutor(ParkingLot parkingLot) {
                this.parkingLot = parkingLot;
        }

        public abstract boolean validate(Command command);

        public abstract void execute(Command command);
}
