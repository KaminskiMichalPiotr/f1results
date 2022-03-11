import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {JWTTOKEN} from "../shared/variables";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  private static addToken(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        'Authorization': `${token}`
      }
    });
  }

  // private handle401Error(req: HttpRequest<any>, next: HttpHandler) {
  //   return undefined;
  // }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = localStorage.getItem(JWTTOKEN);
    if (token)
      req = TokenInterceptor.addToken(req, token);
    //TODO handle 401 error
    // return next.handle(req).pipe(catchError( error => {
    //   if (error instanceof HttpErrorResponse && error.status === 401){
    //       return this.handle401Error(req, next);
    //   } else {
    //     return throwError(error);
    //   }
    // }));
    return next.handle(req);
  }

}
