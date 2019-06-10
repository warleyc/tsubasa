import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGachaRenditionKicker } from 'app/shared/model/m-gacha-rendition-kicker.model';

@Component({
  selector: 'jhi-m-gacha-rendition-kicker-detail',
  templateUrl: './m-gacha-rendition-kicker-detail.component.html'
})
export class MGachaRenditionKickerDetailComponent implements OnInit {
  mGachaRenditionKicker: IMGachaRenditionKicker;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionKicker }) => {
      this.mGachaRenditionKicker = mGachaRenditionKicker;
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
