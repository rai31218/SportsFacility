import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookingComponent } from './booking/booking.component';
import { FacilityComponent } from './facility/facility.component';
import { MybookingComponent } from './mybooking/mybooking.component';
import { MyprofileComponent } from './myprofile/myprofile.component';
import { PlayersComponent } from './players/players.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
{ path: '', component: SigninComponent
},  
{ path: 'signup', component: SignupComponent
},
{ path: 'signin', component: SigninComponent
},
{ path: 'myprofile', component: MyprofileComponent
},
{ path: 'facilities', component: FacilityComponent
},
{ path: 'bookfacility/:id', component: BookingComponent
},
{ path: 'mybooking', component: MybookingComponent
},

];


@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
