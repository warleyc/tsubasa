import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMQuestRewardGroup } from 'app/shared/model/m-quest-reward-group.model';

@Component({
  selector: 'jhi-m-quest-reward-group-detail',
  templateUrl: './m-quest-reward-group-detail.component.html'
})
export class MQuestRewardGroupDetailComponent implements OnInit {
  mQuestRewardGroup: IMQuestRewardGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestRewardGroup }) => {
      this.mQuestRewardGroup = mQuestRewardGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
