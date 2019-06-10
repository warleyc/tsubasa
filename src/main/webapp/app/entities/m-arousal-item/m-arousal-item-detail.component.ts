import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMArousalItem } from 'app/shared/model/m-arousal-item.model';

@Component({
  selector: 'jhi-m-arousal-item-detail',
  templateUrl: './m-arousal-item-detail.component.html'
})
export class MArousalItemDetailComponent implements OnInit {
  mArousalItem: IMArousalItem;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mArousalItem }) => {
      this.mArousalItem = mArousalItem;
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
