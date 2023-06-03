import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { PostRequestBody } from '../interfaces/postRequestBody';
import { ImageClassificationResponse } from "../interfaces/imageClassificationResponse";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  postImageUrl(imageUrl: string): Observable<ImageClassificationResponse> {

    return this.http.post<ImageClassificationResponse>('http://localhost:8080/images', this.postImageRequestBody(imageUrl));

  }

  postImageRequestBody(imageUrl: string): PostRequestBody {
    const requestBody: PostRequestBody = {
      records: [
        {
          _url: imageUrl
        }
      ]
    };

    return requestBody;
  }
}
