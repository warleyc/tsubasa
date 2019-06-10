import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMDetachActionSkillCost } from 'app/shared/model/m-detach-action-skill-cost.model';

@Component({
  selector: 'jhi-m-detach-action-skill-cost-detail',
  templateUrl: './m-detach-action-skill-cost-detail.component.html'
})
export class MDetachActionSkillCostDetailComponent implements OnInit {
  mDetachActionSkillCost: IMDetachActionSkillCost;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDetachActionSkillCost }) => {
      this.mDetachActionSkillCost = mDetachActionSkillCost;
    });
  }

  previousState() {
    window.history.back();
  }
}
