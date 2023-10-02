import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  register(user: any) {
    // Make a POST request to your registration endpoint
    return this.http.post(`${this.apiUrl}/addNewUser`, user);
  }

  login(credentials: any) {
    console.log(credentials);
    // Make a POST request to your login endpoint
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }
  getToken(){
    return localStorage.getItem("token");
  }
}
