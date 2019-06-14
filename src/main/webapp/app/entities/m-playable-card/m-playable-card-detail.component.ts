import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMPlayableCard } from 'app/shared/model/m-playable-card.model';

@Component({
  selector: 'jhi-m-playable-card-detail',
  templateUrl: './m-playable-card-detail.component.html'
})
export class MPlayableCardDetailComponent implements OnInit {
  mPlayableCard: IMPlayableCard;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPlayableCard }) => {
      this.mPlayableCard = mPlayableCard;
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
