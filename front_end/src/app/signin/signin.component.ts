import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtResponse } from '../_model/jwtResponse.model';
import { MessageResponse } from '../_model/messageresponse.model';
import { UserService } from '../_service/user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  hide = true;
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  gotLogInMessage: MessageResponse;
  loInErrorMessage: any;
  loginErrorMessage: any;



  constructor(private formBuilder: FormBuilder, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ["", Validators.required],
      password: ["", Validators.required]
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.loginErrorMessage=null
    this.userService
      .login(this.f['email'].value, this.f['password'].value)
      .subscribe({
        next: (data:JwtResponse) => {

          
            this.router.navigate(['/facilities']);
           
          
         
      },
        error: err=>{this.loginErrorMessage= err}
      })
    
      
  }

}
