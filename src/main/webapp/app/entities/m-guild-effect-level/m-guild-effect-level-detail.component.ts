import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGuildEffectLevel } from 'app/shared/model/m-guild-effect-level.model';

@Component({
  selector: 'jhi-m-guild-effect-level-detail',
  templateUrl: './m-guild-effect-level-detail.component.html'
})
export class MGuildEffectLevelDetailComponent implements OnInit {
  mGuildEffectLevel: IMGuildEffectLevel;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildEffectLevel }) => {
      this.mGuildEffectLevel = mGuildEffectLevel;
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
