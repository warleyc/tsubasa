import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGachaRenditionBeforeShootCutInPattern } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-pattern.model';

@Component({
  selector: 'jhi-m-gacha-rendition-before-shoot-cut-in-pattern-detail',
  templateUrl: './m-gacha-rendition-before-shoot-cut-in-pattern-detail.component.html'
})
export class MGachaRenditionBeforeShootCutInPatternDetailComponent implements OnInit {
  mGachaRenditionBeforeShootCutInPattern: IMGachaRenditionBeforeShootCutInPattern;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionBeforeShootCutInPattern }) => {
      this.mGachaRenditionBeforeShootCutInPattern = mGachaRenditionBeforeShootCutInPattern;
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
