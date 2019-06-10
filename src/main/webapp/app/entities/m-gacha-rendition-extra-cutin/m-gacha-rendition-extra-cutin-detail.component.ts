import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGachaRenditionExtraCutin } from 'app/shared/model/m-gacha-rendition-extra-cutin.model';

@Component({
  selector: 'jhi-m-gacha-rendition-extra-cutin-detail',
  templateUrl: './m-gacha-rendition-extra-cutin-detail.component.html'
})
export class MGachaRenditionExtraCutinDetailComponent implements OnInit {
  mGachaRenditionExtraCutin: IMGachaRenditionExtraCutin;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionExtraCutin }) => {
      this.mGachaRenditionExtraCutin = mGachaRenditionExtraCutin;
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
