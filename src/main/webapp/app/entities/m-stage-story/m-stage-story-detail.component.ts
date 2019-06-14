import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMStageStory } from 'app/shared/model/m-stage-story.model';

@Component({
  selector: 'jhi-m-stage-story-detail',
  templateUrl: './m-stage-story-detail.component.html'
})
export class MStageStoryDetailComponent implements OnInit {
  mStageStory: IMStageStory;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mStageStory }) => {
      this.mStageStory = mStageStory;
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
