import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMNpcCard } from 'app/shared/model/m-npc-card.model';

@Component({
  selector: 'jhi-m-npc-card-detail',
  templateUrl: './m-npc-card-detail.component.html'
})
export class MNpcCardDetailComponent implements OnInit {
  mNpcCard: IMNpcCard;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mNpcCard }) => {
      this.mNpcCard = mNpcCard;
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
