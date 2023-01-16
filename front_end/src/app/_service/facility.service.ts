import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Facility } from '../_model/facility.model';



const commonUrl="http://localhost:8081/sports/";
const url = commonUrl+"getfacilities";

@Injectable({
  providedIn: 'root'
})
export class FacilityService {

  constructor(private http: HttpClient) { }


  getFacilities():Observable<Facility[]>{
    return this.http.get<Facility[]>(url);
  }
}
