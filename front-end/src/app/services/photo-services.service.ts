import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Photo } from '../models/photo';

@Injectable({
  providedIn: 'root'
})
export class PhotoServicesService {

  constructor(private httpCli: HttpClient) { }

  uploadPhoto(photo: Photo){
    console.log("in the upload new photo method service");
    const promise = this.httpCli.post(`http://localhost:9005/social/api/uploadPhoto`, photo
    ).toPromise()
    promise.then((data) => {
    console.log("Uploaded photo (hopefully)");
    })
  }
}
