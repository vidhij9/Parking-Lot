package bookingSysytem.parkingLot.Commands;

import bookingSysytem.parkingLot.Model.Command;
import bookingSysytem.parkingLot.Model.Floor;
import bookingSysytem.parkingLot.Model.ParkingLot;
import bookingSysytem.parkingLot.Services.CreateService;

import java.util.List;

public class CreateCommandExecutor extends CommandExecutor {
  public static final String COMMAND_NAME = "CREATE_PARKING_LOT";
  private static CreateService createService;


  public CreateCommandExecutor(ParkingLot parkingLot, CreateService createService) {
    super(parkingLot);
    CreateCommandExecutor.createService = createService;
  }

  @Override
  public boolean validate(Command command) {
    if (parkingLot.getId() != null) {
      if (parkingLot.getId().compareTo(command.getParams().get(0)) == 0) {
        System.out.println("PARKING LOT ALREADY EXISTS");
        return false;
      }
      else
        return true;
    }
    return true;
  }

  @Override
  public void execute(Command command) {
    List<String> params = command.getParams();
    parkingLot.setId(params.get(0));
    List<Floor> floorList = createService.createFloorList(params.get(1), params.get(2));
    parkingLot.setFloorList(floorList);
    System.out.println(
        "Created parking lot with "
            + params.get(1)
            + " floors and "
            + params.get(2)
            + " slots per floor");
  }
}
