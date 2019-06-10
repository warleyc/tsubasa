import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMArousal } from 'app/shared/model/m-arousal.model';

@Component({
  selector: 'jhi-m-arousal-detail',
  templateUrl: './m-arousal-detail.component.html'
})
export class MArousalDetailComponent implements OnInit {
  mArousal: IMArousal;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mArousal }) => {
      this.mArousal = mArousal;
    });
  }

  previousState() {
    window.history.back();
  }
}
