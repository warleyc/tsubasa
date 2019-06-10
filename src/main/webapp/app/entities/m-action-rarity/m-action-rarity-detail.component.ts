import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMActionRarity } from 'app/shared/model/m-action-rarity.model';

@Component({
  selector: 'jhi-m-action-rarity-detail',
  templateUrl: './m-action-rarity-detail.component.html'
})
export class MActionRarityDetailComponent implements OnInit {
  mActionRarity: IMActionRarity;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mActionRarity }) => {
      this.mActionRarity = mActionRarity;
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
