import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeServiceService } from '../employee-service.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees:Employee[]=[];
  constructor(private employeeService:EmployeeServiceService,private router:Router){
  }
  ngOnInit():void{
    this.getEmployees();
  }

  private getEmployees(){
    this.employeeService.getEmployeeList().subscribe ({ next:(data)=>{
      this.employees=data;
     },error: (error) => {
      console.error("Error fetching employees:", error);
    }
    });
  }
  updateEmployee(id:number){
    this.router.navigate(['update-employee',id]);
  }

  deleteEmployee(id:number){
    this.employeeService.deleteEmployee(id).subscribe ( data=>{
      console.log(data);
      this.getEmployees();
     });
  }
  EmployeeDetails(id:number){
    this.router.navigate(['employee-details',id]);
  }
}
