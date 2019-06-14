import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMTrainingCard } from 'app/shared/model/m-training-card.model';

@Component({
  selector: 'jhi-m-training-card-detail',
  templateUrl: './m-training-card-detail.component.html'
})
export class MTrainingCardDetailComponent implements OnInit {
  mTrainingCard: IMTrainingCard;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTrainingCard }) => {
      this.mTrainingCard = mTrainingCard;
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
