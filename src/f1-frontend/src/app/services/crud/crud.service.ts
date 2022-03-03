import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

export abstract class CrudService<T> {

  protected constructor(protected url: string, protected http: HttpClient) {
  }

  public getAll(): Observable<T[]> {
    return this.http.get<T[]>(this.url);
  }

  public getById(id: number): Observable<T> {
    return this.http.get<T>(`${this.url}/${id}`);
  }

  public save(t: T): Observable<T> {
    return this.http.post<T>(this.url, t);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`)
  }

}
