import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMActionSkillHolderCardContent } from 'app/shared/model/m-action-skill-holder-card-content.model';

@Component({
  selector: 'jhi-m-action-skill-holder-card-content-detail',
  templateUrl: './m-action-skill-holder-card-content-detail.component.html'
})
export class MActionSkillHolderCardContentDetailComponent implements OnInit {
  mActionSkillHolderCardContent: IMActionSkillHolderCardContent;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mActionSkillHolderCardContent }) => {
      this.mActionSkillHolderCardContent = mActionSkillHolderCardContent;
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
