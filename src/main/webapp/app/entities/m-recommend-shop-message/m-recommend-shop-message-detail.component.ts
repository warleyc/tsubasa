import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMRecommendShopMessage } from 'app/shared/model/m-recommend-shop-message.model';

@Component({
  selector: 'jhi-m-recommend-shop-message-detail',
  templateUrl: './m-recommend-shop-message-detail.component.html'
})
export class MRecommendShopMessageDetailComponent implements OnInit {
  mRecommendShopMessage: IMRecommendShopMessage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRecommendShopMessage }) => {
      this.mRecommendShopMessage = mRecommendShopMessage;
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
