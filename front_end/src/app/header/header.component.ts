


import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { UserService } from '../_service/user.service';
import { JwtResponse } from '../_model/jwtResponse.model';
import { Router } from '@angular/router';
import { OnInit } from '@angular/core'

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  // isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
  //   .pipe(
  //     map(result => result.matches),
  //     shareReplay()
  //   );
  isHandset$=true;
  currentUser: JwtResponse;
  userLoggedIn: boolean=false;
  userName:string

  constructor(private userService:UserService,
    private router:Router) {
      console.log("Came in header: "+this.currentUser )
    

  }

  ngOnInit(){
    console.log("Came in heder ngoninit: "+this.currentUser )
    this.currentUser = this.userService.currentUserValue;
    console.log("Came in heder: "+this.currentUser )
    if(this.currentUser!=null && this.currentUser.accessToken!=null){
      console.log("currentuser: "+ this.currentUser.accessToken)
      this.userLoggedIn=true;
      this.userName=this.currentUser.id
    }
  }
  public logout(){
    this.userService.logout();
    this.userLoggedIn = false;
    this.router.navigate(['/']);

  }

}