import { Component } from '@angular/core';
import { EmployeeServiceService } from '../employee-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent {
  employee :Employee=new Employee();
  id:number=0
    constructor(private employeeService:EmployeeServiceService,private router:ActivatedRoute,private route:Router){
  
    }
    ngOnInit():void{
      this.id=this.router.snapshot.params['id'];
      this.employeeService.getEmployeeById(this.id).subscribe(data=>{
        this.employee=data
      },
      error=>console.log(error)
    );
    }
   
  onSubmit(){
    this.employeeService.updateEmployee(this.id,this.employee).subscribe(data=>{
      this.gotoEmployeeList();
    },
    error=>console.log(error));
  }

  gotoEmployeeList(){
    this.route.navigate(['/employees']);
  }

}
