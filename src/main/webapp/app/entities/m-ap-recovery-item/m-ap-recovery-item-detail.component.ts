import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMApRecoveryItem } from 'app/shared/model/m-ap-recovery-item.model';

@Component({
  selector: 'jhi-m-ap-recovery-item-detail',
  templateUrl: './m-ap-recovery-item-detail.component.html'
})
export class MApRecoveryItemDetailComponent implements OnInit {
  mApRecoveryItem: IMApRecoveryItem;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mApRecoveryItem }) => {
      this.mApRecoveryItem = mApRecoveryItem;
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
