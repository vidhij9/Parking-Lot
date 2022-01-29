package bookingSysytem.parkingLot.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class Command {

  private static final String SPACE = " ";
  private String commandName;
  private final List<String> params;

  public Command(String input) {
    input = input.toUpperCase();
    final List<String> bookingSystem =
        Arrays.stream(input.trim().split(SPACE))
            .map(String::trim)
            .filter(booking -> (booking.length() > 0))
            .collect(Collectors.toList());

    if(bookingSystem.size() == 0){
      System.out.println("INVALID COMMAND");
    }

    commandName = bookingSystem.get(0);
    bookingSystem.remove(0);
    params = bookingSystem;
  }
}
