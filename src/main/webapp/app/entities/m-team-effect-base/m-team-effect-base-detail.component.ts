import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMTeamEffectBase } from 'app/shared/model/m-team-effect-base.model';

@Component({
  selector: 'jhi-m-team-effect-base-detail',
  templateUrl: './m-team-effect-base-detail.component.html'
})
export class MTeamEffectBaseDetailComponent implements OnInit {
  mTeamEffectBase: IMTeamEffectBase;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTeamEffectBase }) => {
      this.mTeamEffectBase = mTeamEffectBase;
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
