import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDeckRarityConditionDescription } from 'app/shared/model/m-deck-rarity-condition-description.model';

@Component({
  selector: 'jhi-m-deck-rarity-condition-description-detail',
  templateUrl: './m-deck-rarity-condition-description-detail.component.html'
})
export class MDeckRarityConditionDescriptionDetailComponent implements OnInit {
  mDeckRarityConditionDescription: IMDeckRarityConditionDescription;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDeckRarityConditionDescription }) => {
      this.mDeckRarityConditionDescription = mDeckRarityConditionDescription;
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
