import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtResponse } from '../_model/jwtResponse.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Players } from '../_model/players.model';
import { Address } from '../_model/address.model';

//const commonUrl="https://sdbiene5j8.execute-api.ap-northeast-1.amazonaws.com/UAT/";
const commonUrl="http://localhost:8081/sports/";
//const commonUrl="http://54.168.238.113:8081/sports/"

const url = commonUrl+"sign-up";
const urlSignIn = commonUrl+"sign-in"
const playerDetailsUrl = commonUrl + "getDetails/";
const updatePlayerUrl = commonUrl+"update/"
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private currentUserSubject: BehaviorSubject<JwtResponse>;
  public currentUser: Observable<JwtResponse>;

  constructor(private http: HttpClient) {
      this.currentUserSubject = new BehaviorSubject<JwtResponse>(JSON.parse(localStorage.getItem('currentUser')|| '{}'));
      this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): JwtResponse {
    return this.currentUserSubject.value;
}

  register(userdata: Players) {
    return this.http.post(url, userdata);
  }

  login(email: string, password: string){

    return this.http.post(urlSignIn, {email : email, password : password })
    .pipe(map((jwtResponse:any) => {
      // login successful if there's a jwt token in the response
      console.log(jwtResponse);
      if (jwtResponse && jwtResponse.accessToken) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(jwtResponse));
          this.currentUserSubject.next(jwtResponse);
      }

      return jwtResponse;
  }));

  }

  
  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    
}

getCountries(){
  return this.http.get(commonUrl+"getcountries");
}


public getPlayerDetails(value: any): Observable<Players> {
  return this.http.get<Players>(playerDetailsUrl + value)
}

updatePlayerDetails(currentUser: JwtResponse, playerData:Players) {
   return this.http.put(updatePlayerUrl+currentUser.id , playerData)
}

}
