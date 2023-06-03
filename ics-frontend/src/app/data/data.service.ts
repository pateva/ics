import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { PostRequestBody } from '../interfaces/postRequestBody';
import { ImageClassificationResponse } from "../interfaces/imageClassificationResponse";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  baseUrl = 'http://localhost:8080/images';

  constructor(private http: HttpClient) { }

  postImageUrl(imageUrl: string): Observable<ImageClassificationResponse> {

    return this.http.post<ImageClassificationResponse>(this.baseUrl, this.postImageRequestBody(imageUrl));
    
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

  getImageById(imageId: number) : Observable<ImageClassificationResponse> {
    return this.http.get<ImageClassificationResponse>( this.baseUrl + `/${imageId}`);
  }

  getAllImages() : Observable<ImageClassificationResponse[]> {
    return this.http.get<ImageClassificationResponse[]>(this.baseUrl);
  }

  getImagesByLabels(labels: string[]) : Observable<ImageClassificationResponse[]> {
    const queryParams = labels.map(label => `labels=${encodeURIComponent(label)}`).join('&');
    return this.http.get<ImageClassificationResponse[]>(this.baseUrl + `?${queryParams}`);
}
}
