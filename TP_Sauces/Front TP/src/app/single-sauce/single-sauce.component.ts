import { Component, OnInit } from '@angular/core';
import { Sauce } from '../models/Sauce.model';
import { SaucesService } from '../services/sauces.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-single-sauce',
  templateUrl: './single-sauce.component.html',
  styleUrls: ['./single-sauce.component.scss']
})
export class SingleSauceComponent implements OnInit {

  loading: boolean;
  sauce: Sauce;
  errorMessage: string;

  constructor(private sauces: SaucesService,
              private route: ActivatedRoute,
              private auth: AuthService,
              private router: Router) { }

  ngOnInit() {
    this.loading = true;
    this.route.params.subscribe(
      (params) => {
        this.sauces.getSauceById(params.id).then(
          (sauce: Sauce) => {
            this.sauce = sauce;
            this.loading = false;
          }
        );
      }
    );
  }

  onBack() {
    this.router.navigate(['/sauces']);
  }

  onModify() {
    this.router.navigate(['/modify-sauce', this.sauce.id]);
  }

  onDelete() {
    this.loading = true;
    this.sauces.deleteSauce(this.sauce.id).then(
      (response: { message: string }) => {
        console.log(response.message);
        this.loading = false;
        this.router.navigate(['/sauces']);
      }
    ).catch(
      (error) => {
        this.loading = false;
        this.errorMessage = error.message;
        console.error(error);
      }
    );
  }
}
