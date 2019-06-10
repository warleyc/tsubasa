import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGachaRenditionSwipeIcon } from 'app/shared/model/m-gacha-rendition-swipe-icon.model';

@Component({
  selector: 'jhi-m-gacha-rendition-swipe-icon-detail',
  templateUrl: './m-gacha-rendition-swipe-icon-detail.component.html'
})
export class MGachaRenditionSwipeIconDetailComponent implements OnInit {
  mGachaRenditionSwipeIcon: IMGachaRenditionSwipeIcon;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionSwipeIcon }) => {
      this.mGachaRenditionSwipeIcon = mGachaRenditionSwipeIcon;
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
