import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGachaRenditionTrajectoryCutIn } from 'app/shared/model/m-gacha-rendition-trajectory-cut-in.model';

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-cut-in-detail',
  templateUrl: './m-gacha-rendition-trajectory-cut-in-detail.component.html'
})
export class MGachaRenditionTrajectoryCutInDetailComponent implements OnInit {
  mGachaRenditionTrajectoryCutIn: IMGachaRenditionTrajectoryCutIn;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionTrajectoryCutIn }) => {
      this.mGachaRenditionTrajectoryCutIn = mGachaRenditionTrajectoryCutIn;
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
