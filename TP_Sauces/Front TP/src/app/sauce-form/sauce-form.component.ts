import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SaucesService } from '../services/sauces.service';
import { Sauce } from '../models/Sauce.model';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-sauce-form',
  templateUrl: './sauce-form.component.html',
  styleUrls: ['./sauce-form.component.scss']
})
export class SauceFormComponent implements OnInit {

  sauceForm: FormGroup;
  mode: string;
  loading: boolean;
  sauce: Sauce;
  errorMsg: string;
  imagePreview: string;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private sauces: SaucesService,
              private auth: AuthService) { }

  ngOnInit() {
    this.loading = true;
    this.route.params.subscribe(
      (params) => {
        if (!params.id) {
          this.mode = 'new';
          this.initEmptyForm();
          this.loading = false;
        } else {
          this.mode = 'edit';
          this.sauces.getSauceById(params.id).then(
            (sauce: Sauce) => {
              this.sauce = sauce;
              this.initModifyForm(sauce);
              this.loading = false;
            }
          ).catch(
            (error) => {
              this.errorMsg = JSON.stringify(error);
            }
          );
        }
      }
    );
  }

  initEmptyForm() {
    this.sauceForm = this.formBuilder.group({
      name: [null, Validators.required],
      details: [null, Validators.required],
      rating: [0, Validators.required],
      ratingValue: [{value: 1, disabled: true}]
    });
    this.sauceForm.get('rating').valueChanges.subscribe(
      (value) => {
        this.sauceForm.get('ratingValue').setValue(value);
      }
    );
  }

  initModifyForm(sauce: Sauce) {
    this.sauceForm = this.formBuilder.group({
      name: [this.sauce.name, Validators.required],
      details: [this.sauce.details, Validators.required],
      rating: [this.sauce.rating, Validators.required],
      ratingValue: [{value: this.sauce.rating, disabled: true}]
    });
    this.sauceForm.get('rating').valueChanges.subscribe(
      (value) => {
        this.sauceForm.get('ratingValue').setValue(value);
      }
    );

  }

  onSubmit() {
    this.loading = true;
    const newSauce = new Sauce();
    newSauce.name = this.sauceForm.get('name').value;
    newSauce.rating = this.sauceForm.get('rating').value;
    if (this.mode === 'new') {
      this.sauces.createSauce(newSauce).then(
        (response: { message: string }) => {
          console.log(response.message);
          this.loading = false;
          this.router.navigate(['/sauces']);
        }
      ).catch(
        (error) => {
          console.error(error);
          this.loading = false;
          this.errorMsg = error.message;
        }
      );
    } else if (this.mode === 'edit') {
      this.sauces.modifySauce(this.sauce.id, newSauce).then(
        (response: { message: string }) => {
          console.log(response.message);
          this.loading = false;
          this.router.navigate(['/sauces']);
        }
      ).catch(
        (error) => {
          console.error(error);
          this.loading = false;
          this.errorMsg = error.message;
        }
      );
    }
  }
}
