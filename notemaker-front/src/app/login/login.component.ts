import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user: any = {}; // An object to store user input (email and password)

  constructor(private authService: AuthService,private router:Router,private route:ActivatedRoute) {}

  onSubmit() {
    // Call the login method from AuthService
    this.authService.login(this.user).subscribe(
      (response: any) => {
        // Successful login
        localStorage.setItem('token', response.token);
        localStorage.setItem("email",response.email);
        window.location.href="";
        // Store the token in local storage (you might want to handle this differently in a real app)
        // Redirect to a protected route or any other logic after successful login
        // For example, you can use Angular's Router to navigate to another page.
      },
      (error: any) => {
        // Handle login error, display a message to the user, or perform other actions.
        console.error('Login failed:', error);
        // You can also reset the form or display an error message to the user.
      }
    );
  }
}

