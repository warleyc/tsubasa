import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCutSeqGroup } from 'app/shared/model/m-cut-seq-group.model';

@Component({
  selector: 'jhi-m-cut-seq-group-detail',
  templateUrl: './m-cut-seq-group-detail.component.html'
})
export class MCutSeqGroupDetailComponent implements OnInit {
  mCutSeqGroup: IMCutSeqGroup;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCutSeqGroup }) => {
      this.mCutSeqGroup = mCutSeqGroup;
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
