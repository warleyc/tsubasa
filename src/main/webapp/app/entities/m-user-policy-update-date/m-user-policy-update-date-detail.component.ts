import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMUserPolicyUpdateDate } from 'app/shared/model/m-user-policy-update-date.model';

@Component({
  selector: 'jhi-m-user-policy-update-date-detail',
  templateUrl: './m-user-policy-update-date-detail.component.html'
})
export class MUserPolicyUpdateDateDetailComponent implements OnInit {
  mUserPolicyUpdateDate: IMUserPolicyUpdateDate;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mUserPolicyUpdateDate }) => {
      this.mUserPolicyUpdateDate = mUserPolicyUpdateDate;
    });
  }

  previousState() {
    window.history.back();
  }
}
