import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Facility } from '../_model/facility.model';
import { FacilityService } from '../_service/facility.service';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}


@Component({
  selector: 'app-facility',
  templateUrl: './facility.component.html',
  styleUrls: ['./facility.component.css']
})
export class FacilityComponent implements OnInit {

  displayedColumns: string[] = ['sport', 'facility', 'generalAvailability', 'action'];
  // dataSource = ELEMENT_DATA;
  facilityList: Facility[];
  dataSource: Facility[];

  constructor(private facilityService: FacilityService, private router: Router) {
    this.facilityService.getFacilities().subscribe({
      next: (data: Facility[]) => {
        console.log("data: " + data[0].name)
        this.dataSource = data
      },
      error: (err) => { console.log(err) }
    });


  }

  ngOnInit(): void {

  }

  public bookfacility(facilityId:number) {
    console.log("Go to booK facility now "+facilityId)
    
    this.router.navigate(['/bookfacility', facilityId]);
  }

}
