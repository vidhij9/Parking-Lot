package bookingSysytem.parkingLot.Model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Slot {

        String id;

        Vehicle vehicle;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public Vehicle getVehicle() {
                return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
                this.vehicle = vehicle;
        }
}
