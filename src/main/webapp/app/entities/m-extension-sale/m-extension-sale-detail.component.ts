import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMExtensionSale } from 'app/shared/model/m-extension-sale.model';

@Component({
  selector: 'jhi-m-extension-sale-detail',
  templateUrl: './m-extension-sale-detail.component.html'
})
export class MExtensionSaleDetailComponent implements OnInit {
  mExtensionSale: IMExtensionSale;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mExtensionSale }) => {
      this.mExtensionSale = mExtensionSale;
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
