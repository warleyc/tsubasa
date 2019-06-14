import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMTips } from 'app/shared/model/m-tips.model';

@Component({
  selector: 'jhi-m-tips-detail',
  templateUrl: './m-tips-detail.component.html'
})
export class MTipsDetailComponent implements OnInit {
  mTips: IMTips;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTips }) => {
      this.mTips = mTips;
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
