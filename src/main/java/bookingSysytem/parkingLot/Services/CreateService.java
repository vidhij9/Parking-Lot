package bookingSysytem.parkingLot.Services;

import bookingSysytem.parkingLot.Model.Floor;
import bookingSysytem.parkingLot.Model.Slot;
import bookingSysytem.parkingLot.Model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CreateService {

  public CreateService() {}

  public List<Floor> createFloorList(String floorNumbers, String slotNumbers) {
    //    if (!availabilityService.isFloorAvailable(floorNumbers)) {
    //      System.out.println("Floors already exists.");
    //      return new ArrayList<>();
    //    } else if (!availabilityService.isSlotAvailable(slotNumbers)) {
    //      System.out.println("Slots already exists.");
    //      return new ArrayList<>();
    //    }
    return buildFloors(floorNumbers, slotNumbers);
  }

  public List<Floor> buildFloors(String floorNumbers, String slotNumbers) {
    Integer floorNumber = 1;
    List<Floor> floors = new ArrayList<>();
    while (floorNumber <= Integer.parseInt(floorNumbers)) {
      List<Slot> slots = buildSlots(slotNumbers);
      Floor floor = new Floor(String.valueOf(floorNumber), slots);
      floors.add(floor);
      floorNumber++;
    }
    return floors;
  }

  public List<Slot> buildSlots(String slotNumbers) {
    List<Slot> slots = new ArrayList<>();
    Integer slotNumber = 1;
    while (slotNumber <= Integer.parseInt(slotNumbers)) {
      if (slotNumber == 1) {
        Vehicle vehicle = new Vehicle(null, null, null);
        vehicle.setType("TRUCK");
        Slot slot = new Slot(String.valueOf(slotNumber), vehicle);
        slots.add(slot);
      } else if (slotNumber == 2 || slotNumber == 3) {
        Vehicle vehicle = new Vehicle(null, null, null);
        vehicle.setType("BIKE");
        Slot slot = new Slot(String.valueOf(slotNumber), vehicle);
        slots.add(slot);
      } else {
        Vehicle vehicle = new Vehicle(null, null, null);
        vehicle.setType("CAR");
        Slot slot = new Slot(String.valueOf(slotNumber), vehicle);
        slots.add(slot);
      }
      slotNumber++;
    }
    return slots;
  }
}
