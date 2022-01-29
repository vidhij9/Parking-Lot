package bookingSysytem.parkingLot.Commands;

import bookingSysytem.parkingLot.Model.Command;
import bookingSysytem.parkingLot.Model.ParkingLot;
import bookingSysytem.parkingLot.Services.AvailabilityService;
import bookingSysytem.parkingLot.Services.CreateService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandExecutorFactory {

  private final CreateService createService = new CreateService();
  private final AvailabilityService availabilityService = new AvailabilityService();
  private final Map<String,
      CommandExecutor> commandExecutorMap = new HashMap<>();
  private ParkingLot parkingLot = new ParkingLot(null, null);

  public CommandExecutorFactory() {
    commandExecutorMap.put(
        CreateCommandExecutor.COMMAND_NAME, new CreateCommandExecutor(parkingLot, createService));
    commandExecutorMap.put(
        DisplayCommandExecutor.COMMAND_NAME,
        new DisplayCommandExecutor(parkingLot, availabilityService));
    commandExecutorMap.put(
        ParkCommandExecutor.COMMAND_NAME, new ParkCommandExecutor(parkingLot, availabilityService));
    commandExecutorMap.put(
        UnParkCommandExecutor.COMMAND_NAME, new UnParkCommandExecutor(parkingLot, availabilityService));
    commandExecutorMap.put(ExitCommandExecutor.COMMAND_NAME, new ExitCommandExecutor(parkingLot));
  }

  public CommandExecutor getCommandExecutor(final Command command) {
    final CommandExecutor commandExecutor = commandExecutorMap.get(command.getCommandName());
    if (commandExecutor == null) {
      System.out.println("INVALID COMMAND");
    }
    return commandExecutor;
  }
}
