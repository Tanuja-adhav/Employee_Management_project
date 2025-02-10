import { Component } from '@angular/core';
import { Employee } from '../employee';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeServiceService } from '../employee-service.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent {
  id:number
  employee:Employee
  constructor(private route:ActivatedRoute,private empService:EmployeeServiceService){
    this.id=this.route.snapshot.params['id'];
    this.employee=new Employee();
    this.empService.getEmployeeById(this.id).subscribe(data=>{
      this.employee=data;
    })
  }
}
