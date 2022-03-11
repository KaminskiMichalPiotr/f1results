import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../shared/models/user.model";
import {catchError, mapTo, tap} from 'rxjs/operators'
import {JWTTOKEN, PATH} from "../shared/variables";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {
  }

  private static doLoginUser(authorization: string | null) {
    if (authorization)
      localStorage.setItem(JWTTOKEN, authorization);
  }

  private static doLogoutUser() {
    localStorage.removeItem(JWTTOKEN);
  }

  login(user: User): Observable<boolean> {
    return this.http.post(PATH + 'login', user, {
      observe: 'response'
    }).pipe(
      tap(token => LoginService.doLoginUser(token.headers.get('Authorization'))),
      mapTo(true),
      catchError(() => {
          return of(false);
        }
      )
    );
  }

  logout() {
    LoginService.doLogoutUser();
  }

}
