package bookingSysytem.parkingLot;

import bookingSysytem.parkingLot.Commands.CommandExecutor;
import bookingSysytem.parkingLot.Commands.CommandExecutorFactory;
import bookingSysytem.parkingLot.Commands.ExitCommandExecutor;
import bookingSysytem.parkingLot.Model.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Run implements CommandLineRunner {

  @Autowired private CommandExecutorFactory commandExecutorFactory;

  @Override
  public void run(String... args) throws Exception {
    mainRun(args);
  }

  public void mainRun(String[] args) throws IOException {
    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      final String input = bufferedReader.readLine();
      final Command command = new Command(input);
      processCommand(command);
      if (command.getCommandName().equals(ExitCommandExecutor.COMMAND_NAME)) {
        break;
      }
    }
  }

  protected void processCommand(final Command command) {
    final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
    if (commandExecutor.validate(command)) {
      commandExecutor.execute(command);
    } else {
      System.out.println("INVALID COMMAND");
    }
  }
}
