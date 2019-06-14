import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMShoot } from 'app/shared/model/m-shoot.model';

@Component({
  selector: 'jhi-m-shoot-detail',
  templateUrl: './m-shoot-detail.component.html'
})
export class MShootDetailComponent implements OnInit {
  mShoot: IMShoot;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mShoot }) => {
      this.mShoot = mShoot;
    });
  }

  previousState() {
    window.history.back();
  }
}
