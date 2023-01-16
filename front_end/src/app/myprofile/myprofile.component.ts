import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Country } from '../_model/country.model';
import { JwtResponse } from '../_model/jwtResponse.model';
import { Players } from '../_model/players.model';
import { State } from '../_model/state.model';
import { BookingService } from '../_service/booking.service';
import { UserService } from '../_service/user.service';

@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.css']
})
export class MyprofileComponent implements OnInit {
  currentUser: JwtResponse
  playerDetails: Players;
  updateForm: any;
  submitted = false;
  errorInFetchingPlayerDetails: any;
  nextPage: boolean;
  playerid: string;
  updatedPlayer: Players;
  updateMessage: string;
  updationErrorMessage: any;
  states: State[];
  countries: Country[];
  originatorState: any;
 
  constructor(private formBuilder: FormBuilder,
    private playerService: UserService, private bookingService: BookingService) {

   
    this.currentUser = this.playerService.currentUserValue;

  }

  ngOnInit(): void {
  
    this.fetchInitialPlayerData();

    this.updateForm = this.formBuilder.group({
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
      playerId:[],


    });
  }

  get f() { return this.updateForm.controls; }
 
  get f1(){ return (this.f['address'] as FormGroup).controls}


  public fetchInitialPlayerData(){
    var playerObs = this.playerService.getPlayerDetails(this.currentUser.id);
    playerObs.subscribe({
      next: (data: Players) => {
        this.playerDetails = data;
        this.states=this.playerDetails.address.country.state;
        this.originatorState=this.playerDetails.address.state.id;
        this.playerid=this.playerDetails.id;
        console.log("firstname: " + this.playerDetails.firstName)
        this.updateForm = this.formBuilder.group({
          firstName: this.playerDetails.firstName,
          lastName: this.playerDetails.lastName,
          email: [this.playerDetails.email, Validators.required],
          pan: [this.playerDetails.pan, Validators.required],
          age: this.playerDetails.age,
          contact: this.playerDetails.contact,
          address: this.formBuilder.group({
            homeAddress: this.playerDetails.address.homeAddress,
            state: this.originatorState,
            country: this.playerDetails.address.country.name,
            
          }),
          playerId: this.playerDetails.id,
         

        });
       
      },
      error: (err) => { this.errorInFetchingPlayerDetails = err; this.nextPage = false }
    })
  }

  public onSubmit(){
    this.submitted = true;

    // stop here if form is invalid
    if (this.updateForm.invalid) {
      return;
    }
     console.log("new state value: "+this.f1['state'].value);
    const playerData: Players = {
      email: this.f['email'].value,  
      address: {
        state: new State(this.f1['state'].value),
        homeAddress: this.f1['homeAddress'].value,
        
      },
      pan: this.f['pan'].value,
      contact: this.f['contact'].value
    };

    var obs = this.playerService.updatePlayerDetails(this.currentUser, playerData);
    obs.subscribe({
      next:(data:Players)=>{
         this.updatedPlayer=data;
         this.updateMessage="Player details are successfully updated"
      },
      error:(err)=>{
        this.updationErrorMessage=err;
      }
    })

  }


  onChange(countryId:number){
    console.log("Event: "+countryId);
    this.states = this.countries.find(con => con.id == countryId).state
  }

}
