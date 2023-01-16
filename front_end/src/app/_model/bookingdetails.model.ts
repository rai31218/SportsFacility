import { Facility } from "./facility.model";
import { Players } from "./players.model";
import { TimeSlots } from "./timeslots.model";

export class BookingDetails{
    public id?:number;
    public player?:Players;
    public bookingDate?:Date;
    public bookingSlot?: TimeSlots;
    public facility?: Facility;
    
}