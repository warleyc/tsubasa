import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCardRarity } from 'app/shared/model/m-card-rarity.model';

@Component({
  selector: 'jhi-m-card-rarity-detail',
  templateUrl: './m-card-rarity-detail.component.html'
})
export class MCardRarityDetailComponent implements OnInit {
  mCardRarity: IMCardRarity;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardRarity }) => {
      this.mCardRarity = mCardRarity;
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
