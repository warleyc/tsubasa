import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMTeamEffectRarity } from 'app/shared/model/m-team-effect-rarity.model';

@Component({
  selector: 'jhi-m-team-effect-rarity-detail',
  templateUrl: './m-team-effect-rarity-detail.component.html'
})
export class MTeamEffectRarityDetailComponent implements OnInit {
  mTeamEffectRarity: IMTeamEffectRarity;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTeamEffectRarity }) => {
      this.mTeamEffectRarity = mTeamEffectRarity;
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
