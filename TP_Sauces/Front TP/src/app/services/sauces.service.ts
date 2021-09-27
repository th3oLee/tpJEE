import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Sauce } from '../models/Sauce.model';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SaucesService {

  sauces$ = new Subject<Sauce[]>();

  constructor(private http: HttpClient,
    private auth: AuthService) { }

  getSauces() {
    this.http.get(environment.endpoint + 'sauces').subscribe(
      (sauces: Sauce[]) => {
        this.sauces$.next(sauces);
      },
      (error) => {
        this.sauces$.next([]);
        console.error(error);
      }
    );
  }

  getSauceById(id: string) {
    return this.http.get(environment.endpoint + 'sauces/' + id).toPromise();
  }

  createSauce(sauce: Sauce) {
    return this.http.post(environment.endpoint + 'sauces', sauce).toPromise();
  }

  modifySauce(id: string, sauce: Sauce) {
    return this.http.put(environment.endpoint + 'sauces/' + id, sauce).toPromise();
  }

  deleteSauce(id: string) {
    return this.http.delete(environment.endpoint + 'sauces/' + id).toPromise();
  }
}
