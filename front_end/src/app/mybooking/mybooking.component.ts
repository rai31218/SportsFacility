import { Component, OnInit } from '@angular/core';
import { BookingDetails } from '../_model/bookingdetails.model';
import { JwtResponse } from '../_model/jwtResponse.model';
import { BookingService } from '../_service/booking.service';
import { UserService } from '../_service/user.service';

@Component({
  selector: 'app-mybooking',
  templateUrl: './mybooking.component.html',
  styleUrls: ['./mybooking.component.css']
})
export class MybookingComponent implements OnInit {
  currentUser:JwtResponse;
  bookingList: BookingDetails[];
  noBookingMessage: string;
  errormessage: any;
  constructor(private bookingService:BookingService, private userService:UserService) { }

  ngOnInit(): void {
    this.currentUser = this.userService.currentUserValue;
    this.bookingService.fetchBooking(this.currentUser).subscribe({
      next: (data:BookingDetails[])=>{
        this.bookingList = data
        if(this.bookingList.length==0){
          this.noBookingMessage="No Booking available"
        }
      },
      error:err=>{
        this.errormessage=err;
      }
    })
  }

}
