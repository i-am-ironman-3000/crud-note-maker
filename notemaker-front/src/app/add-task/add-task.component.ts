import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent {
  content='';
  form!:FormGroup;
  constructor(private userService:UserService
    ,private formBuilder:FormBuilder,
    private router:Router
    ){
    this.form=this.formBuilder.group({
      content:['']
    });
  }
  save(){
    this.form.controls['content'].setValue(this.content);
    this.userService.save(this.form.value).subscribe(
      (response:any)=>{
        alert("saved");
        this.router.navigateByUrl("/home/success");
      },
      (error:any)=>{
        alert("LOGIN AGAIN");
        this.router.navigateByUrl("");
      }
    );
  }
}
