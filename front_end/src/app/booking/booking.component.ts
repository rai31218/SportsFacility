import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_LOCALE, MAT_DATE_FORMATS } from '@angular/material/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Availability } from '../_model/availability.mode';
import { BookingDetails } from '../_model/bookingdetails.model';
import { Country } from '../_model/country.model';
import { Facility } from '../_model/facility.model';
import { MessageResponse } from '../_model/messageresponse.model';
import { Players } from '../_model/players.model';
import { State } from '../_model/state.model';
import { TimeSlots } from '../_model/timeslots.model';
import { BookingService } from '../_service/booking.service';
import { FacilityService } from '../_service/facility.service';
import { UserService } from '../_service/user.service';



export const MY_FORMATS = {
  parse: {
      dateInput: 'LL'
  },
  display: {
      dateInput: 'YYYY-MM-DD',
      monthYearLabel: 'YYYY',
      dateA11yLabel: 'LL',
      monthYearA11yLabel: 'YYYY'
  }
};


@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css'],
  providers: [
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS }
  ]
})
export class BookingComponent implements OnInit {
  //@ViewChild('facilityName', { static: true }) facilityName: ElementRef;
  
  facilityName:string;
  bookingForm: FormGroup;
  playerIdForm: FormGroup;
  loading = false;
  submitted = false;
  gotRegistrationMessage: MessageResponse;
  registrationErrorMessage: any;

  slots: TimeSlots[];
  countryAr: Country;
  nextPage: boolean=false;
  playerDetails: Players;
  errorInFetchingPlayerDetails: any;
  facilityList: Facility[];
  facilityId: number;
  bookFacilityMessage: any;
  bookFacilityErrorMessage: any;


  constructor(private formBuilder: FormBuilder, private router: Router, private bookingService: BookingService,
    private playerService:UserService,
    private route: ActivatedRoute,
    private facilityService: FacilityService) { 

        this.facilityId=+this.route.snapshot.paramMap.get('id');

      }

  ngOnInit(): void {


    this.playerIdForm = this.formBuilder.group({
      playerid : ['', Validators.required]
    });

    this.facilityService.getFacilities().subscribe({
      next:(data:Facility[])=>{
        this.facilityList = data;
      },
      error:(err)=>{console.log("error: "+err)}
    })

    this.bookingForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      email: ['', Validators.required],
      pan: ['', Validators.required],
      age:  ['', Validators.required],
      contact:  ['', Validators.required],
      address: this.formBuilder.group({
        homeAddress: ['',Validators.required],
        state: ['',Validators.required],
        country: ['',Validators.required]
      }),
      bookingDate: ['', Validators.required],
      availability: ['', Validators.required],
      slot: ['', Validators.required],
      facility: ['', Validators.required] ,  
      playerId:[]


    });

  }
  get f() { return this.bookingForm.controls; }
 
  get f1(){ return (this.f['address'] as FormGroup).controls}

  get f2() { return this.playerIdForm.controls; }

  public next(){
    this.nextPage=true;
    console.log("Player Id: "+this.f2['playerid'].value);
    var playerObs = this.playerService.getPlayerDetails(this.f2['playerid'].value);
    playerObs.subscribe({
      next:(data:Players)=>{
         this.playerDetails=data;
          console.log("firstname: "+this.playerDetails.firstName)
         this.bookingForm = this.formBuilder.group({
          firstName: this.playerDetails.firstName,
          lastName: this.playerDetails.lastName,
          email: this.playerDetails.email,
          pan: this.playerDetails.pan,
          age: this.playerDetails.age,
          contact: this.playerDetails.contact,
          address: this.formBuilder.group({
            homeAddress: this.playerDetails.address.homeAddress,
            state: this.playerDetails.address.state.name,
            country: this.playerDetails.address.country.name
          }),
          playerId: this.playerDetails.id,
          bookingDate:null,
          facility:this.facilityId,
          slot: null
     })
        },
      error:(err)=>{this.errorInFetchingPlayerDetails=err; this.nextPage=false }
    })
    
  }
  
  public onSubmit(){
  //  console.log("Facility Id: "+ this.f['facility'].value.split("-")[0]+" "+this.f['facility'].value.split("-")[1])
    const formData: BookingDetails = {
      player: new Players( this.f['playerId'].value),
      bookingDate: this.f['bookingDate'].value,
      bookingSlot: new TimeSlots(this.f['slot'].value.split(":")[0],this.f['slot'].value.split(":")[1]),
      //facility: new Facility(this.f['facility'].value.split("-")[0],this.f['facility'].value.split("-")[1])
      facility: new Facility(this.f['facility'].value)
    };

    this.bookingService.bookService(formData).subscribe({
      next:(data:MessageResponse)=>{console.log("saved");
      this.bookFacilityMessage = data.message},
      error:(err)=>{console.log(err)
      this.bookFacilityErrorMessage=err}
    })

  }

  public onDateChange(){
    console.log("what r u saying? :" +this.f['bookingDate'].value+ " "+this.f['facility'].value)
    let availability:Availability = {
      "facility":{
        "id": this.f['facility'].value,
        //"name": this.f['facility'].value.split("-")[1]
      },
      "date":this.f['bookingDate'].value
    }
    this.bookingService.getSlots(availability).subscribe({
      next:(data:TimeSlots[])=>{ this.slots = data},
      error:(err)=>{console.log(err)}
    })
  }


    
}

