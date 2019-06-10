import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMAchievement } from 'app/shared/model/m-achievement.model';

@Component({
  selector: 'jhi-m-achievement-detail',
  templateUrl: './m-achievement-detail.component.html'
})
export class MAchievementDetailComponent implements OnInit {
  mAchievement: IMAchievement;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAchievement }) => {
      this.mAchievement = mAchievement;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
