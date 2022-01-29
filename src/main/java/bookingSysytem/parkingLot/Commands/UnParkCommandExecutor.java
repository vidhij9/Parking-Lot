package bookingSysytem.parkingLot.Commands;

import bookingSysytem.parkingLot.Model.Command;
import bookingSysytem.parkingLot.Model.ParkingLot;
import bookingSysytem.parkingLot.Services.AvailabilityService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UnParkCommandExecutor extends CommandExecutor {
  public static final String COMMAND_NAME = "UNPARK_VEHICLE";
  private static AvailabilityService availabilityService;

  public UnParkCommandExecutor(ParkingLot parkingLot, AvailabilityService availabilityService) {
    super(parkingLot);
    UnParkCommandExecutor.availabilityService = availabilityService;
  }

  @Override
  public boolean validate(Command command) {
    return availabilityService.isVehicleParked(command.getParams().get(0), parkingLot);
  }

  @Override
  public void execute(Command command) {
    String params = command.getParams().get(0);
    final List<String> ticketList =
            Arrays.stream(params.trim().split("_"))
                    .map(String::trim)
                    .filter(ticket -> (ticket.length() > 0))
                    .collect(Collectors.toList());
    String floorId = ticketList.get(1);
    String slotId = ticketList.get(2);
    parkingLot
        .getFloorList()
        .get(Integer.parseInt(floorId)-1)
        .getSlotList()
        .get(Integer.parseInt(slotId)-1)
        .getVehicle()
        .setRegId(null);
    parkingLot
        .getFloorList()
        .get(Integer.parseInt(floorId)-1)
        .getSlotList()
        .get(Integer.parseInt(slotId)-1)
        .getVehicle()
        .setColor(null);
  }
}
