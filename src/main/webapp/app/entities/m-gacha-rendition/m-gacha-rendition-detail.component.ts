import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGachaRendition } from 'app/shared/model/m-gacha-rendition.model';

@Component({
  selector: 'jhi-m-gacha-rendition-detail',
  templateUrl: './m-gacha-rendition-detail.component.html'
})
export class MGachaRenditionDetailComponent implements OnInit {
  mGachaRendition: IMGachaRendition;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRendition }) => {
      this.mGachaRendition = mGachaRendition;
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
