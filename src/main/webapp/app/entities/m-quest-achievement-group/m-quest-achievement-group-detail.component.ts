import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMQuestAchievementGroup } from 'app/shared/model/m-quest-achievement-group.model';

@Component({
  selector: 'jhi-m-quest-achievement-group-detail',
  templateUrl: './m-quest-achievement-group-detail.component.html'
})
export class MQuestAchievementGroupDetailComponent implements OnInit {
  mQuestAchievementGroup: IMQuestAchievementGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestAchievementGroup }) => {
      this.mQuestAchievementGroup = mQuestAchievementGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
