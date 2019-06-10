import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMEmblemParts } from 'app/shared/model/m-emblem-parts.model';

@Component({
  selector: 'jhi-m-emblem-parts-detail',
  templateUrl: './m-emblem-parts-detail.component.html'
})
export class MEmblemPartsDetailComponent implements OnInit {
  mEmblemParts: IMEmblemParts;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEmblemParts }) => {
      this.mEmblemParts = mEmblemParts;
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
