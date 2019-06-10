import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDictionaryFr } from 'app/shared/model/m-dictionary-fr.model';

@Component({
  selector: 'jhi-m-dictionary-fr-detail',
  templateUrl: './m-dictionary-fr-detail.component.html'
})
export class MDictionaryFrDetailComponent implements OnInit {
  mDictionaryFr: IMDictionaryFr;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryFr }) => {
      this.mDictionaryFr = mDictionaryFr;
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
