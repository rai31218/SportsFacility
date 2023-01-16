import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Address } from '../_model/address.model';
import { Country } from '../_model/country.model';
import { MessageResponse } from '../_model/messageresponse.model';
import { Players } from '../_model/players.model';
import { State } from '../_model/state.model';
import { UserService } from '../_service/user.service';

import {DateAdapter, MAT_DATE_FORMATS,MAT_DATE_LOCALE } from '@angular/material/core';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
   
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
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  providers: [
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS }
  ]
})
export class SignupComponent implements OnInit {
  hide = true;
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  gotRegistrationMessage: MessageResponse;
  registrationErrorMessage: any;
  countries: Country[];
  states: State[];
  countryAr: Country;


  constructor(private formBuilder: FormBuilder, private router: Router, private userService: UserService) {
    this.userService.getCountries().subscribe({

      next: (data: any) => {
        this.countries = data;
      },
      error: err => { console.log("no data" + err) }

    })
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      email: ['', Validators.required],
      pan: ['', Validators.required],
      dob:  ['', Validators.required],
      contact:  ['', Validators.required],
      address: this.formBuilder.group({
        homeAddress: ['',Validators.required],
        state: ['',Validators.required],
        country: ['',Validators.required]
      })


    });
  }


  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }
 
  get f1(){ return (this.f['address'] as FormGroup).controls}
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    // building JSON for form data
    const formData: Players = {
      firstName: this.f['firstName'].value,
      lastName: this.f['lastName'].value,
      password: this.f['password'].value,
      email: this.f['email'].value,
      dob: this.f['dob'].value,
      address: {
        state: new State(this.f1['state'].value),
        homeAddress: this.f1['homeAddress'].value,
        country: new Country(this.f1['country'].value)
      },
      pan: this.f['pan'].value,
      contact: this.f['contact'].value
    };

    this.loading = true;
    this.gotRegistrationMessage = null;
    this.registrationErrorMessage = null
    this.userService.register(formData)
      .subscribe({
        next: (data: MessageResponse) => {
          console.log("success: " + data.message)
          this.gotRegistrationMessage = data;
          // this.alertService.success('Registration successful', true);
          this.router.navigate(['/signin']);
        },
        error: (err: string) => {
          //this.alertService.error(error);
          //this.loading = false;
          console.log("error: " + err);
          this.registrationErrorMessage = err;
        }

      });
  }


  getCountries() {
    var observable = this.userService.getCountries();
    observable.subscribe({
      next:(data:any)=>{
        this.countries=data;
      },
      error:err=>{console.log("do data"+err)}

    })
  }

  onChange(countryId:number){
    console.log("Event: "+countryId);
    this.states = this.countries.find(con => con.id == countryId).state
  }

}
