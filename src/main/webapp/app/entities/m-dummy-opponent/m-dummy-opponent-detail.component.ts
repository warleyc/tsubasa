import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDummyOpponent } from 'app/shared/model/m-dummy-opponent.model';

@Component({
  selector: 'jhi-m-dummy-opponent-detail',
  templateUrl: './m-dummy-opponent-detail.component.html'
})
export class MDummyOpponentDetailComponent implements OnInit {
  mDummyOpponent: IMDummyOpponent;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDummyOpponent }) => {
      this.mDummyOpponent = mDummyOpponent;
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
