import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGuildEffect } from 'app/shared/model/m-guild-effect.model';

@Component({
  selector: 'jhi-m-guild-effect-detail',
  templateUrl: './m-guild-effect-detail.component.html'
})
export class MGuildEffectDetailComponent implements OnInit {
  mGuildEffect: IMGuildEffect;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildEffect }) => {
      this.mGuildEffect = mGuildEffect;
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
