import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMCardLevel } from 'app/shared/model/m-card-level.model';

@Component({
  selector: 'jhi-m-card-level-detail',
  templateUrl: './m-card-level-detail.component.html'
})
export class MCardLevelDetailComponent implements OnInit {
  mCardLevel: IMCardLevel;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardLevel }) => {
      this.mCardLevel = mCardLevel;
    });
  }

  previousState() {
    window.history.back();
  }
}
