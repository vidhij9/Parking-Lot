package bookingSysytem.parkingLot.Commands;

import bookingSysytem.parkingLot.Model.Command;
import bookingSysytem.parkingLot.Model.ParkingLot;
import bookingSysytem.parkingLot.Model.Ticket;
import bookingSysytem.parkingLot.Services.AvailabilityService;

import java.util.List;

public class ParkCommandExecutor extends CommandExecutor {
  public static final String COMMAND_NAME = "PARK_VEHICLE";
  private static AvailabilityService availabilityService;

  public ParkCommandExecutor(ParkingLot floorList, AvailabilityService availabilityService) {
    super(floorList);
    ParkCommandExecutor.availabilityService = availabilityService;
  }

  @Override
  public boolean validate(Command command) {
    List<String> params = command.getParams();
    return availabilityService.isVehicleAlreadyPresent(
            params.get(0), params.get(1), params.get(2), parkingLot);
  }

  @Override
  public void execute(Command command) {
    List<String> params = command.getParams();
    Ticket ticket = availabilityService.parkVehicle(params, parkingLot);
    if (ticket.getTicketId() == null) {
      System.out.println("Parking Lot Full");
    } else {
      System.out.println("Parked vehicle. Ticket ID: " + ticket.getTicketId());
    }
  }
}
