package bookingSysytem.parkingLot.Commands;

import bookingSysytem.parkingLot.Model.Command;
import bookingSysytem.parkingLot.Model.ParkingLot;

public class ExitCommandExecutor extends CommandExecutor {
  public static final String COMMAND_NAME = "EXIT";

  public ExitCommandExecutor(ParkingLot floorList) {
    super(floorList);
  }

  @Override
  public boolean validate(Command command) {
    return command.getParams().isEmpty();
  }

  @Override
  public void execute(Command command) {
    System.out.println("Thanks for using Parking-Lot booking system.");
  }
}
