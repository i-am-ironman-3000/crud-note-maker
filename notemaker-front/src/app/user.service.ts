import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  header_object!:HttpHeaders;
  baseUrl="http://localhost:8080/api/notes";
  constructor(private http:HttpClient) {
    this.header_object=new HttpHeaders().set("Authorization","Bearer "+localStorage.getItem("token"));
  }
  getData(){
    return this.http.get(`${this.baseUrl}/recent`,{headers:this.header_object});
  }
  save(data:any){
    return this.http.post(`${this.baseUrl}/create`,data,{headers:this.header_object});
  }
  delete(id:number){
    console.log(id);
    return this.http.delete(`${this.baseUrl}/delete?id=${id}`,{headers:this.header_object});
  }
}
