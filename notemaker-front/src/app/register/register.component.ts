import { Component } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user: any = {}; // An object to store user input (email and password)

  constructor(private authService: AuthService) {}

  onSubmit() {
    this.user['role']="ROLE_USER";
    console.log(this.user);
    // Call the register method from AuthService
    this.authService.register(this.user).subscribe(
      (response: any) => {
        alert("register successfull");
      },
      (error: any) => {
        // Handle registration error, display a message to the user, or perform other actions.
        console.error('Registration failed:', error);
        // You can also reset the form or display an error message to the user.
      }
    );
  }
}
