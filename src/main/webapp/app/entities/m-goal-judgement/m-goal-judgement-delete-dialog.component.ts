import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGoalJudgement } from 'app/shared/model/m-goal-judgement.model';
import { MGoalJudgementService } from './m-goal-judgement.service';

@Component({
  selector: 'jhi-m-goal-judgement-delete-dialog',
  templateUrl: './m-goal-judgement-delete-dialog.component.html'
})
export class MGoalJudgementDeleteDialogComponent {
  mGoalJudgement: IMGoalJudgement;

  constructor(
    protected mGoalJudgementService: MGoalJudgementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGoalJudgementService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGoalJudgementListModification',
        content: 'Deleted an mGoalJudgement'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-goal-judgement-delete-popup',
  template: ''
})
export class MGoalJudgementDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGoalJudgement }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGoalJudgementDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mGoalJudgement = mGoalJudgement;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-goal-judgement', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-goal-judgement', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
