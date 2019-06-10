import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMDeckCondition } from 'app/shared/model/m-deck-condition.model';

@Component({
  selector: 'jhi-m-deck-condition-detail',
  templateUrl: './m-deck-condition-detail.component.html'
})
export class MDeckConditionDetailComponent implements OnInit {
  mDeckCondition: IMDeckCondition;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDeckCondition }) => {
      this.mDeckCondition = mDeckCondition;
    });
  }

  previousState() {
    window.history.back();
  }
}
