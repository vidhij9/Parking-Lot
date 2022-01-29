package bookingSysytem.parkingLot.Services;

import bookingSysytem.parkingLot.Model.Floor;
import bookingSysytem.parkingLot.Model.ParkingLot;
import bookingSysytem.parkingLot.Model.Slot;
import bookingSysytem.parkingLot.Model.Ticket;
import bookingSysytem.parkingLot.Model.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AvailabilityService {

  public AvailabilityService() {}

  //        public boolean isFloorAvailable(String floorNumbers){
  //
  //        }
  //
  //        public boolean isSlotAvailable(String slotNumbers){
  //
  //        }

  public boolean isVehicleAlreadyPresent(
      String type, String id, String color, ParkingLot parkingLot) {
    List<Floor> floorList = parkingLot.getFloorList();
    for (Floor floor : floorList) {
      for (Slot slot : floor.getSlotList()) {
        Vehicle vehicle = slot.getVehicle();
        if (vehicle.getRegId() != null) {
          if (vehicle.getType().compareTo(type) == 0
              && vehicle.getColor().compareTo(color) == 0
              && vehicle.getRegId().compareTo(id) == 0) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean isVehicleParked(String params, ParkingLot parkingLot) {
    final List<String> ticketList =
        Arrays.stream(params.trim().split("_"))
            .map(String::trim)
            .filter(ticket -> (ticket.length() > 0))
            .collect(Collectors.toList());
    String floorId = ticketList.get(1);
    String slotId = ticketList.get(2);
    if (parkingLot.getFloorList().size() < Integer.parseInt(floorId)) {
      return false;
    }
    if (parkingLot.getFloorList().get(0).getSlotList().size() < Integer.parseInt(slotId)) {
      return false;
    }
    if (parkingLot
            .getFloorList()
            .get(Integer.parseInt(floorId)-1)
            .getSlotList()
            .get(Integer.parseInt(slotId)-1)
            .getVehicle()
            .getRegId()
        == null) {
      return false;
    }
    return true;
  }

  public Ticket parkVehicle(List<String> params, ParkingLot parkingLot) {
    Ticket ticket = new Ticket(null);
    String vehicleType = params.get(0);
    for (Floor floor : parkingLot.getFloorList()) {
      for (Slot slot : floor.getSlotList()) {
        if (slot.getVehicle().getRegId() == null
            && slot.getVehicle().getType().compareTo(vehicleType) == 0) {
          slot.getVehicle().setRegId(params.get(1));
          slot.getVehicle().setColor(params.get(2));
          ticket.setTicketId(parkingLot.getId() + "_" + floor.getId() + "_" + slot.getId());
          return ticket;
        }
      }
    }
    return ticket;
  }

  public List<List<String>> getOccupiedSlots(String type, ParkingLot parkingLot) {
    List<List<String>> floorAndSlots = new ArrayList<>();
    for (Floor floor : parkingLot.getFloorList()) {
      List<String> slots = new ArrayList<>();
      for (Slot slot : floor.getSlotList()) {
        Vehicle vehicle = slot.getVehicle();
        if (vehicle.getType().compareTo(type) == 0 && vehicle.getRegId() != null) {
          slots.add(slot.getId());
        }
      }
      floorAndSlots.add(slots);
    }
    return floorAndSlots;
  }

  public List<List<String>> getSlotsList(String type, ParkingLot parkingLot) {
    List<List<String>> floorAndSlots = new ArrayList<>();
    for (Floor floor : parkingLot.getFloorList()) {
      List<String> slots = new ArrayList<>();
      for (Slot slot : floor.getSlotList()) {
        Vehicle vehicle = slot.getVehicle();
        if (vehicle.getRegId() == null && vehicle.getType().compareTo(type) == 0) {
          slots.add(slot.getId());
        }
      }
      floorAndSlots.add(slots);
    }
    return floorAndSlots;
  }
}
