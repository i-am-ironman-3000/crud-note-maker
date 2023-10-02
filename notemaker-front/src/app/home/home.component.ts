import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  data:any=[];
  currentDate=new Date();
  constructor(private userService:UserService,private route:Router,private router:ActivatedRoute){
    this.data=[];
  }
  ngOnInit(){
    this.router.paramMap.subscribe(
      ()=>{
        if(this.route.url==="/home/success"){
          this.route.navigateByUrl("/home");
        }
        this.userService.getData().subscribe(
          (response:any)=>{
            console.log(response);
            this.data=response;
          },
          (error:any)=>{
            alert("Login again");
            this.route.navigateByUrl("");
          }
        );
      }
    );
  }
  getDay(data:any){
    var data2=new Date(data);
    return Math.floor(
      (this.currentDate.getTime() - data2.getTime()) /
        (1000 * 60 * 60 * 24)
    );
  }
  delete(id:any){
    this.userService.delete(id).subscribe(
      (response:any)=>{
        alert(response);
        this.route.navigateByUrl("/home/success");
      },
      (error:any)=>{
        alert(error.error.text);
        this.route.navigateByUrl("/home/success");
      }
    );
  }
  logout(){
    localStorage.clear();
    window.location.href="";
  }
}
