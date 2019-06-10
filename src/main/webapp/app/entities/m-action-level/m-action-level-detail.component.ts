import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMActionLevel } from 'app/shared/model/m-action-level.model';

@Component({
  selector: 'jhi-m-action-level-detail',
  templateUrl: './m-action-level-detail.component.html'
})
export class MActionLevelDetailComponent implements OnInit {
  mActionLevel: IMActionLevel;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mActionLevel }) => {
      this.mActionLevel = mActionLevel;
    });
  }

  previousState() {
    window.history.back();
  }
}
