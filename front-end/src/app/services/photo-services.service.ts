import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Photo } from '../models/photo';

@Injectable({
  providedIn: 'root'
})
export class PhotoServicesService {

  constructor(private httpCli: HttpClient) { }

  uploadPhoto(photo: Photo): Observable<Photo>{
    console.log("in the upload new photo method service");
    const fd = new FormData();
    console.log(fd);
    fd.append('image', photo.imageData, photo.photoString);
    return this.httpCli.post<Photo>(`http://localhost:9005/social/api/uploadPhoto`, fd,
    {withCredentials: true})
  }
}
