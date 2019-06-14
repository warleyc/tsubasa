import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMQuestCoop } from 'app/shared/model/m-quest-coop.model';

@Component({
  selector: 'jhi-m-quest-coop-detail',
  templateUrl: './m-quest-coop-detail.component.html'
})
export class MQuestCoopDetailComponent implements OnInit {
  mQuestCoop: IMQuestCoop;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestCoop }) => {
      this.mQuestCoop = mQuestCoop;
    });
  }

  previousState() {
    window.history.back();
  }
}
