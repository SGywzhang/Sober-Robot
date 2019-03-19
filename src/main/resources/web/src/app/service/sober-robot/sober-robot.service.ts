import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SoberRobotService {

  private baseUrl = 'http://localhost:8080/upload';

  constructor(private httpClient: HttpClient) { }

  public upload(fd: FormData): Observable<any> {
    return this.httpClient.post(this.baseUrl, fd);
  }
}
