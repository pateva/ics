import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  postImageUrl(imageUrl: string) : Observable<any> {

    return this.http.post('http://localhost:8080/images', imageUrl);
    
    //return of(image);
  }
}
