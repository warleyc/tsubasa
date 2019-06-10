import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCheatCaution } from 'app/shared/model/m-cheat-caution.model';

@Component({
  selector: 'jhi-m-cheat-caution-detail',
  templateUrl: './m-cheat-caution-detail.component.html'
})
export class MCheatCautionDetailComponent implements OnInit {
  mCheatCaution: IMCheatCaution;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCheatCaution }) => {
      this.mCheatCaution = mCheatCaution;
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
