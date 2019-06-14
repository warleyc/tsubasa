import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMMarathonBoostItem } from 'app/shared/model/m-marathon-boost-item.model';

@Component({
  selector: 'jhi-m-marathon-boost-item-detail',
  templateUrl: './m-marathon-boost-item-detail.component.html'
})
export class MMarathonBoostItemDetailComponent implements OnInit {
  mMarathonBoostItem: IMMarathonBoostItem;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonBoostItem }) => {
      this.mMarathonBoostItem = mMarathonBoostItem;
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
