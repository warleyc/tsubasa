import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMActionSkillHolderCardCt } from 'app/shared/model/m-action-skill-holder-card-ct.model';

@Component({
  selector: 'jhi-m-action-skill-holder-card-ct-detail',
  templateUrl: './m-action-skill-holder-card-ct-detail.component.html'
})
export class MActionSkillHolderCardCtDetailComponent implements OnInit {
  mActionSkillHolderCardCt: IMActionSkillHolderCardCt;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mActionSkillHolderCardCt }) => {
      this.mActionSkillHolderCardCt = mActionSkillHolderCardCt;
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
