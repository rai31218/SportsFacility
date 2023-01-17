import { HttpBackend, HttpClient,  HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Availability } from '../_model/availability.mode';
import { Players } from '../_model/players.model';
import { TimeSlots } from '../_model/timeslots.model';
import { map } from 'rxjs/operators';
import { Facility } from '../_model/facility.model';
import { BookingDetails } from '../_model/bookingdetails.model';
import { JwtResponse } from '../_model/jwtResponse.model';


const commonUrl="https://sdbiene5j8.execute-api.ap-northeast-1.amazonaws.com/UAT/";
//const commonUrl = "http://54.168.238.113:8081/sports/";

const slotUrl = commonUrl
const bookFacilityUrl=commonUrl+"bookfacility";
const getBookingsUrl=commonUrl+"getBookings/"


@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private http: HttpClient) { }



  public getSlots(availabilitycheck: Availability): Observable<TimeSlots[]> {

    let queryParams = new HttpParams();
    console.log("Date "+JSON.stringify(availabilitycheck.date))
    queryParams = queryParams.append("facilityId", availabilitycheck.facility.id  );
    queryParams = queryParams.append("facilityName",availabilitycheck.facility.name);
    queryParams = queryParams.append("date", JSON.stringify(availabilitycheck.date));


    return this.http.get<TimeSlots[]>(slotUrl + "availability", { params: queryParams }).pipe(map(response => response));
  }

  public bookService(formData: BookingDetails) {
    return this.http.post(bookFacilityUrl, formData)
  }

  public fetchBooking(currentUser:JwtResponse):Observable<BookingDetails[]> {
    return this.http.get<BookingDetails[]>(getBookingsUrl+currentUser.id);
  }



}
