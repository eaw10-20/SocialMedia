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
  let fd = new FormData();
  fd.append('imageData', photo.imageData, photo.imageData.name);
  fd.append('photoString', photo.photoString);
  console.log("The name taken is "+fd.get("photoString"))
  
  return this.httpCli.post<Photo>(`http://localhost:9005/social/api/uploadPhoto`, fd,
    {withCredentials: true})
  }

  // uploadPhoto(photo: Photo){
  //   console.log("in the upload new photo method service");
    
  //   const promise = this.httpCli.post(`http://localhost:9005/social/api/uploadPhoto`, photo.imageData
  //   ).toPromise()
  //   promise.then((data) => {
  //   console.log("Uploaded photo (hopefully)");
  //   })
  // } 
}
