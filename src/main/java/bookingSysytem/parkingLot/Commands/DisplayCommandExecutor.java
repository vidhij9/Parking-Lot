package bookingSysytem.parkingLot.Commands;

import bookingSysytem.parkingLot.Model.Command;
import bookingSysytem.parkingLot.Model.ParkingLot;
import bookingSysytem.parkingLot.Services.AvailabilityService;

import java.util.List;
import java.util.Objects;

public class DisplayCommandExecutor extends CommandExecutor {
  public static final String COMMAND_NAME = "DISPLAY";
  private static AvailabilityService availabilityService;

  public DisplayCommandExecutor(ParkingLot parkingLot, AvailabilityService availabilityService) {
    super(parkingLot);
    DisplayCommandExecutor.availabilityService = availabilityService;
  }

  @Override
  public boolean validate(Command command) {
    List<String> params = command.getParams();
    return (Objects.equals(params.get(0), "FREE_COUNT")
            || Objects.equals(params.get(0), "FREE_SLOTS")
            || Objects.equals(params.get(0), "OCCUPIED_SLOTS"))
        && (Objects.equals(params.get(1), "CAR")
            || Objects.equals(params.get(1), "BIKE")
            || Objects.equals(params.get(1), "TRUCK"));
  }

  @Override
  public void execute(Command command) {
    List<String> params = command.getParams();
    if (Objects.equals(params.get(0), "OCCUPIED_SLOTS")) {
      List<List<String>> floorAndSlots =
          availabilityService.getOccupiedSlots(params.get(1), parkingLot);
      Integer floorNumber = 1;
      for (List<String> slots : floorAndSlots) {
        System.out.printf("Occupied slots for CAR on Floor " +  floorNumber + ": ");
        for (String slot : slots) {
          System.out.print(slot + ",");
        }
        System.out.println("\n");
        floorNumber++;
      }
    } else {
      List<List<String>> floorAndSlots =
          availabilityService.getSlotsList(params.get(1), parkingLot);
      Integer floorNumber = 1;
      if (Objects.equals(params.get(0), "FREE_COUNT")) {
        for (List<String> slots : floorAndSlots) {
          System.out.printf(
              "No. of free slots for"
                  + params.get(1)
                  + " on Floor "
                  + floorNumber
                  + ": "
                  + slots.size()
                  + "\n");
          floorNumber++;
        }
      } else {
        for (List<String> slots : floorAndSlots) {
          System.out.print("Free slots for" + params.get(1) + " on Floor " + floorNumber + ": ");
          for (String slot : slots) {
            System.out.print(slot + ",");
          }
          System.out.println("\n");
          floorNumber++;
        }
      }
    }
  }
}
