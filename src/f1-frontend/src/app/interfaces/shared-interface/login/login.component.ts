import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {noWhitespaceValidator} from "../../../shared/whitespace.validator";
import {Router} from "@angular/router";
import {LoginService} from "../../../services/login.service";
import {User} from "../../../shared/models/user.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  form = new FormGroup({
    username: new FormControl(null, [Validators.required, noWhitespaceValidator]),
    password: new FormControl(null, [Validators.required, noWhitespaceValidator])
  })

  constructor(private router: Router, private loginService: LoginService) {
  }

  submitForm() {
    let user = this.extractData();

    this.loginService.login(user).subscribe(() => this.router.navigate(['/']))

  }

  private extractData(): User {
    return this.form.value as User;
  }
}



