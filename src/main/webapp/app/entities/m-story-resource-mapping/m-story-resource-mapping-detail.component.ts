import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMStoryResourceMapping } from 'app/shared/model/m-story-resource-mapping.model';

@Component({
  selector: 'jhi-m-story-resource-mapping-detail',
  templateUrl: './m-story-resource-mapping-detail.component.html'
})
export class MStoryResourceMappingDetailComponent implements OnInit {
  mStoryResourceMapping: IMStoryResourceMapping;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mStoryResourceMapping }) => {
      this.mStoryResourceMapping = mStoryResourceMapping;
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
