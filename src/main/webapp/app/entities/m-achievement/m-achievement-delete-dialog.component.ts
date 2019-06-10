import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMAchievement } from 'app/shared/model/m-achievement.model';
import { MAchievementService } from './m-achievement.service';

@Component({
  selector: 'jhi-m-achievement-delete-dialog',
  templateUrl: './m-achievement-delete-dialog.component.html'
})
export class MAchievementDeleteDialogComponent {
  mAchievement: IMAchievement;

  constructor(
    protected mAchievementService: MAchievementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mAchievementService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mAchievementListModification',
        content: 'Deleted an mAchievement'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-achievement-delete-popup',
  template: ''
})
export class MAchievementDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAchievement }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MAchievementDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mAchievement = mAchievement;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-achievement', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-achievement', { outlets: { popup: null } }]);
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
