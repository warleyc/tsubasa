import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestWorld } from 'app/shared/model/m-quest-world.model';
import { MQuestWorldService } from './m-quest-world.service';

@Component({
  selector: 'jhi-m-quest-world-delete-dialog',
  templateUrl: './m-quest-world-delete-dialog.component.html'
})
export class MQuestWorldDeleteDialogComponent {
  mQuestWorld: IMQuestWorld;

  constructor(
    protected mQuestWorldService: MQuestWorldService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestWorldService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestWorldListModification',
        content: 'Deleted an mQuestWorld'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-world-delete-popup',
  template: ''
})
export class MQuestWorldDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestWorld }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestWorldDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mQuestWorld = mQuestWorld;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-world', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-world', { outlets: { popup: null } }]);
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
